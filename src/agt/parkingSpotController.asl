/* Initial beliefs and rules */
owner(false).
occupied(false).

/* Initial goals */
!startParkingSpot.

/* Plans */

//Buyer
+!startParkingSpot <-
	.my_name(Me);
	.print("Iniciando Parking Spot", Me);
	initializeParkingSpot(Me).
/* Plans */

+!askStatus[source(AG)]: owner(Owner) & location(LocationX, LocationY) <- 
	.send(AG, achieve, answerStatusParkingSpot(owner(Owner), location(LocationX, LocationY))).
	
+!askAverageSaleValue[source(AG)]: averageSaleValue(M) <-	
	.send(AG, tell, averageSaleValue(M)).

//Inicial
+!park[source(AG)]: occupied(Occupied) <-
	-+occupied(true);
	-+owner(AG);
	.print(AG, " ocupando vaga").
	
//Depois que a vaga já foi vendida uma vez	
+!park(salesValue(SalesValue))[source(AG)]: occupied(Occupied) & averageSaleValue(AverageSaleValue)<-
	-+occupied(true);
	-+owner(AG);
	.print(AG, " ocupando vaga");
	-+averageSaleValue((AverageSaleValue + SalesValue) / 2).
	
+!freeParkingSpot[source(AG)]: occupied(Occupied) & owner(Owner) <-
	-+occupied(false);
	-+owner(false);
	.print("Agente ", AG, " desistiu de vender a vaga.").
	
+!leaveParkingSpot[source(AG)]: occupied(Occupied) <-
	-+occupied(false).
	
+!generateOffer(locationParkedSpot(X, Y)) <-
	.wait(0).
	

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
