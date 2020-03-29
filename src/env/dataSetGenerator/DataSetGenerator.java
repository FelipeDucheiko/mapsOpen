package dataSetGenerator;

import java.io.*;
import cartago.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class DataSetGenerator extends Artifact {
	String fileName = "D:\\Data\\Documents\\EclipseProjects\\mapsOpen\\DataSet\\";
	FileWriter fileWriter;
	BufferedWriter bufferedWriter;
	PrintWriter out;
	Map<String,Driver> drivers = new HashMap<String,Driver>();
	
	public DataSetGenerator() {
		header();
	}
	 
	void init(int initialValue) {
		defineObsProperty("count", initialValue);		
	}

	@OPERATION
	void inc() {
		ObsProperty prop = getObsProperty("count");
		prop.updateValue(prop.intValue()+1);
		signal("tick");
	}
	
	@OPERATION
	void header() {
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
		
	    fileName += LocalDateTime.now().format(timeFormatter) + ".csv";
		System.out.println(fileName);
		try {	
            fileWriter = new FileWriter(fileName, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            out = new PrintWriter(bufferedWriter);
            out.println("AG;ArrivalTime;ParkingTime;ParkingSpot;TimeToPark;SalesValue;SaleType;DepartureTime;SpendedTime;targetLocationX;targetLocationY;parkedLocationX;parkedLocationY;msgToBuy;broadcastToBuy;msgToSell;broadcastToSell;totalMsg;totalBroadcast;maxValue");
            out.close();
		} catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
            ex.printStackTrace();
        }
	}
	
	@OPERATION
	void arrivalTime(String driver, Object targetLocationX, Object targetLocationY, Object maxValue) {
		LocalTime arrivalTime = LocalTime.now();
		
		Driver d = new Driver();
		d.setName(driver);
		d.setArrivalTime(arrivalTime);
		d.setTargetLocationX(Double.parseDouble(targetLocationX.toString()));
		d.setTargetLocationY(Double.parseDouble(targetLocationY.toString()));
		d.setMaxValue(Integer.parseInt(maxValue.toString()));
		drivers.put(driver, d);
	}
	
	@OPERATION
	void parkingTime(String driver, String parkingSpot, Object salesValue, Object msgToBuy, Object broadcastToBuy, Object parkedLocationX, Object parkedLocationY) {
		LocalTime parkingTime = LocalTime.now();		
		
		Driver d = drivers.get(driver);
		d.setParkingTime(parkingTime);
		d.setParkingSpot(parkingSpot);
		d.setSalesValue(Double.parseDouble(salesValue.toString()));
		d.setMessageSentToBuy(Integer.parseInt(msgToBuy.toString()));
		d.setBroadcastSentToBuy(Integer.parseInt(broadcastToBuy.toString()));
		d.setParkedLocationX(Double.parseDouble(parkedLocationX.toString()));
		d.setParkedLocationY(Double.parseDouble(parkedLocationY.toString()));
		
		double timeToPark = ChronoUnit.SECONDS.between(d.getArrivalTime(), d.getParkingTime());;
		d.setTimeToPark(timeToPark);
	}
	
	@OPERATION
	void departureTime(String driver) {
		LocalTime departureTime = LocalTime.now();		
		
		Driver d = drivers.get(driver);
		d.setDepartureTime(departureTime);
		double spendedTime = ChronoUnit.SECONDS.between(d.getParkingTime(), d.getDepartureTime());
		d.setSpendedTime(spendedTime);
	}
	
	@OPERATION
	void sell(String driver, Object msgToSell, Object broadcastToSell, String saleType) {		
		Driver d = drivers.get(driver);
		
		d.setMessageSentToSell(Integer.parseInt(msgToSell.toString()) - d.getMessageSentToBuy());
		d.setBroadcastSentToSell(Integer.parseInt(broadcastToSell.toString()) - d.getBroadcastSentToBuy());
		d.setSaleType(saleType);
		
		print(d);
	}
	
	@OPERATION
	void print(Driver d) {
		try {	
			DateTimeFormatter timeFormatter2 = DateTimeFormatter.ofPattern("H:mm:ss");
            fileWriter = new FileWriter(fileName, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            out = new PrintWriter(bufferedWriter);
            out.println(d.getName() + ";" + d.getArrivalTime().format(timeFormatter2) + ";" + d.getParkingTime().format(timeFormatter2) + ";" + d.getParkingSpot() + ";" + d.getTimeToPark() + ";" + d.getSalesValue() + ";"  + d.getSaleType() + ";" + d.getDepartureTime().format(timeFormatter2) + ";" + d.getSpendedTime() + ";" + d.getTargetLocationX() + ";" + d.getTargetLocationY() + ";" + d.getParkedLocationX() + ";" + d.getParkedLocationY() + ";" + d.getMessageSentToBuy() + ";" + d.getBroadcastSentToBuy() + ";" + d.getMessageSentToSell() + ";" + d.getBroadcastSentToSell() + ";" + (d.getMessageSentToSell() + d.getMessageSentToBuy()) + ";" + (d.getBroadcastSentToSell() + d.getBroadcastSentToBuy()) + ";" + d.getMaxValue());
            out.close();
		} catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
            ex.printStackTrace();
        }
		
	}
	
}

