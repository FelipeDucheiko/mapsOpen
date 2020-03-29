/* Initial beliefs and rules */
parkedSpot(null).
locationParkedSpot(0, 0).
offer(0).
salesValue(0).
parked(false).
arrived(false).
averageSaleValue(0).
offerList([]).
startNegotiation(true).
messageSentNumber(0).
broadcastSentNumber(0).

/* Initial goals */
!arriveParking.

/* Plans */

//Buyer
+!arriveParking: timeToArrive(TimeToArrive) & broadcastSentNumber(N) & targetLocation(X,Y) & maxValue(MaxValue) <-
	adoptRole(buyer)[artifact_id(parkingLot_org)];
	.my_name(Me);
	startDriver(Me);
	.print("Aguardando para entrar - ", TimeToArrive);
	.wait(TimeToArrive);
	-+arrived(true);
	arrivalTime(Me, X, Y, MaxValue);
	.print("Entrando");
	-+broadcastSentNumber(N+1);
	.broadcast(achieve, askStatus). //ask status to parking spots

//Buyer	
+!answerStatusParkingSpot(owner(Owner), location(LocationX, LocationY))[source(ParkingSpot)]: parked(Parked) & targetLocation(X,Y) & maxDistance(MaxDistance) <-
	if (Owner = false &  Parked = false & math.sqrt(((LocationX-X)*(LocationX-X)) + ((LocationY-Y)*(LocationY-Y))) < (MaxDistance*10)){
		!park(parkedSpot(ParkingSpot), locationParkedSpot(LocationX, LocationY));
	}.
	
//Seller	
+!leaveParking: parkedSpot(ParkedSpot) & messageSentNumber(N) <-
	.my_name(Me); 
	departureTime(Me);
	.print("Saindo da vaga ", ParkedSpot);	
	.send(ParkedSpot, achieve, leaveParkingSpot);
	-+messageSentNumber(N+1);
	leaveParkedSpot(Me);
	leaveSpotSumo(Me, ParkedSpot);
	!startNegotiation.
	
//Seller	
+!startNegotiation: offerList(OfferList) & locationParkedSpot(LocationParkedSpotX, LocationParkedSpotY) & parkedSpot(ParkedSpot) & messageSentNumber(N) & broadcastSentNumber(B) & startNegotiation(StartNegotiationBoolean) <- 
	if(.empty(OfferList)){
		
		if(B > 5){
			.send(ParkedSpot, achieve, freeParkingSpot);
			.my_name(Me);
			freePark(Me, ParkedSpot); //interface
			sell(Me, N, B, "Gave up");
			.print("Desistiu de vender a vaga");
			.fail_goal(startNegotiation);
		}
		
		-+broadcastSentNumber(B+1);
		-+startNegotiation(true);
		.broadcast(achieve, offerSpot(locationParkedSpot(LocationParkedSpotX, LocationParkedSpotY)));
		.print("Enviando broadcast");	
	} else{
		if(StartNegotiationBoolean = true){
			-+startNegotiation(false);
			.nth(0, OfferList, Driver);
			.nth(1, OfferList, Offer);
			!analyzeOffer(driver(Driver), offer(Offer));
		}
	}
	.wait(1000)
	!startNegotiation;.

	
//Buyer
+!offerSpot(locationParkedSpot(LocationParkedSpotX, LocationParkedSpotY))[source(AG)]: messageSentNumber(N) & maxValue(MaxValue) & maxDistance(MaxDistance) & targetLocation(TargetLocationX, TargetLocationY) & parked(Parked) & arrived(Arrived)<-
	if (Arrived = true){	
		if (Parked = false){
			.print("Oferta de vaga recebida de ",  AG, ", iniciando negociação"); 
			generateOffer(MaxValue, MaxDistance, LocationParkedSpotX, LocationParkedSpotY, TargetLocationX, TargetLocationY, Offer);
			.send(AG, achieve, receiveOffer(offer(Offer)));
			-+messageSentNumber(N+1);
			-+offer(Offer);
			.print("Proposta ", Offer, " enviada para o agente ", AG)}
		else{
			.print("Oferta de vaga recebida de ", AG, " ignorada, já tenho vaga.")	
		}
	}.

//Seller
+!receiveOffer(offer(Offer))[source(AG)]: offerList(OfferList) <-
	.print("Recebi a oferta ", Offer, " do agente ", AG);
	
	.concat(OfferList, [AG, Offer], X);
	-+offerList(X).
	
	
//Seller
+!analyzeOffer(driver(Driver), offer(Offer)): parkedSpot(ParkedSpot) & locationParkedSpot(X, Y) & messageSentNumber(N) & broadcastSentNumber(B) <-
	
	?averageSaleValue(M);
	-+averageSaleValue(M);

	.print("Negociando com: ", Driver);

	if (Offer >= M){
		.print("Negociação encerrada, vaga vendida para o agente ", Driver);
		.my_name(Me);
		sell(Me, N+1, B, "Sell");
		.send(Driver, achieve, park(parkedSpot(ParkedSpot), locationParkedSpot(X, Y), salesValue(Offer)));
		-+messageSentNumber(N+1);
	}
	if (Offer < M/2){
		.print("Negociação encerrada com o agente ", Driver, ", sem trato");
		!giveUp;
	}
	if ((Offer < M) & (Offer >= M/2)){
		.print("Gerar contra oferta!!!!");
		!generateCounterOffer(source(Driver));
	}.
	
+!giveUp: offerList(OfferList) <-
	.nth(0, OfferList, D);
	.nth(1, OfferList, O);
	.print("DRIVER E OFFER RETIRADOS DA LISTA: ", D, ", ", O);
	.delete(0, OfferList, L);
	.delete(0, L, L1);
	-+offerList(L1);
	if (not .empty(L1)){
		.nth(0, L1, Driver);
		.nth(1, L1, Offer);
		!analyzeOffer(driver(Driver), offer(Offer));
	}.
	
//Seller
+!generateCounterOffer(source(AG)): parkedSpot(ParkedSpot) & averageSaleValue(M) & locationParkedSpot(X, Y) & messageSentNumber(N)<-
	.send(AG, achieve, ultimatum(counterOffer(M), parkedSpot(ParkedSpot), locationParkedSpot(X, Y)));
	-+messageSentNumber(N+1);
	.print("Contra Oferta ", M, " enviada para o agente ", AG).
	
//Buyer 
+!park(parkedSpot(ParkedSpot), locationParkedSpot(X, Y))[source(AG)]: timeToSpend(TimeToSpend) & parked(Parked) & messageSentNumber(N) & broadcastSentNumber(M) <- 
	if(Parked = false){
		.send(ParkedSpot, achieve, park);
		-+parked(true);
		-+locationParkedSpot(X, Y);
		-+parkedSpot(ParkedSpot);
		.send(ParkedSpot, achieve, askAverageSaleValue); //Utilizado na hora de vender a vaga
		-+messageSentNumber(N+2);
		.my_name(Me);
		park(Me, ParkedSpot); //interface gráfica
		allocateSpotSumo(Me, ParkedSpot, 100);
		parkingTime(Me, ParkedSpot, 6, N+2, M, X, Y);
		.print("Estacionando na vaga ", ParkedSpot);
		.wait(TimeToSpend);		
		adoptRole(seller)[artifact_id(parkingLot_org)];
		!leaveParking
	} else {
		.send(ParkedSpot, achieve, freeParkingSpot);
	}.

//Buyer
+!park(parkedSpot(ParkedSpot), locationParkedSpot(X, Y), salesValue(SalesValue))[source(AG)]: timeToSpend(TimeToSpend) & parked(Parked) & messageSentNumber(N) & broadcastSentNumber(M)<- 
	if(Parked = false){
		.send(ParkedSpot, achieve, park(salesValue(SalesValue)));
		-+parked(true);
		-+locationParkedSpot(X, Y);
		-+parkedSpot(ParkedSpot);
		.send(ParkedSpot, achieve, askAverageSaleValue); //Utilizado na hora de vender a vaga
		-+messageSentNumber(N+2);
		.my_name(Me);
		park(Me, ParkedSpot); //interface gráfica
		allocateSpotSumo(Me, ParkedSpot, 100);
		parkingTime(Me, ParkedSpot, SalesValue, N+2, M, X, Y);
		.print("Estacionando na vaga ", ParkedSpot);
		.wait(TimeToSpend);		
		adoptRole(seller)[artifact_id(parkingLot_org)];
		!leaveParking
	} else {
		.send(ParkedSpot, achieve, freeParkingSpot);
	}.
	
	
//seller
+!counterOfferAccepted[source(AG)]: messageSentNumber(N) & broadcastSentNumber(B) <-
	.my_name(Me);
	sell(Me, N+1, B, "Sell ultimatum").

//Buyer
 +!ultimatum(counterOffer(CounterOffer), parkedSpot(ParkedSpot), locationParkedSpot(X, Y))[source(AG)]: offerList(OfferList) & rangeAccording(RangeAccording) & offer(Offer) & messageSentNumber(N)<-
 	.print("Contra oferta ", CounterOffer, " recebida do agente ", AG);
 	
	 if ((CounterOffer - Offer) <= (Offer*RangeAccording)){
 		.print("Contra proposta aceita do agente ", AG);
 		.send(AG, achieve, counterOfferAccepted);
 		!park(parkedSpot(ParkedSpot), locationParkedSpot(X, Y), salesValue(CounterOffer));
 	} else {
 		.print("Contra proposta rejeitada, sem trato. Encerrada negociação com o agente ", AG);
 		.send(AG, achieve, giveUp); 
 		-+messageSentNumber(N+1);
 	}. 
 	
//Evitar warning
 +!askStatus[source(AG)] <- 
	.wait(0).
	
{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }
{ include("$jacamoJar/templates/org-obedient.asl") }