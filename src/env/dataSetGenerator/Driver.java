package dataSetGenerator;

import java.time.LocalTime;

public class Driver {
	private String name;
	private LocalTime arrivalTime;
	private LocalTime parkingTime;
	private String parkingSpot;
	private Double timeToPark;
	private Double salesValue;
	private String saleType;
	private LocalTime departureTime;
	private Double spendedTime;
	private int messageSentToBuy;
	private int messageSentToSell;
	private int broadcastSentToBuy;
	private int broadcastSentToSell;
	private double targetLocationX;
	private double targetLocationY;
	private double parkedLocationX;
	private double parkedLocationY;
	private int maxValue;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public LocalTime getParkingTime() {
		return parkingTime;
	}
	public void setParkingTime(LocalTime parkingTime) {
		this.parkingTime = parkingTime;
	}
	public String getParkingSpot() {
		return parkingSpot;
	}
	public void setParkingSpot(String parkingSpot) {
		this.parkingSpot = parkingSpot;
	}
	public Double getTimeToPark() {
		return timeToPark;
	}
	public void setTimeToPark(Double timeToPark) {
		this.timeToPark = timeToPark;
	}
	public Double getSalesValue() {
		return salesValue;
	}
	public void setSalesValue(Double salesValue) {
		this.salesValue = salesValue;
	}
	public Double getSpendedTime() {
		return spendedTime;
	}
	public void setSpendedTime(Double spendedTime) {
		this.spendedTime = spendedTime;
	}
	public LocalTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	public int getMessageSentToBuy() {
		return messageSentToBuy;
	}
	public void setMessageSentToBuy(int messageSentToBuy) {
		this.messageSentToBuy = messageSentToBuy;
	}
	public int getMessageSentToSell() {
		return messageSentToSell;
	}
	public void setMessageSentToSell(int messageSentToSell) {
		this.messageSentToSell = messageSentToSell;
	}
	public int getBroadcastSentToBuy() {
		return broadcastSentToBuy;
	}
	public void setBroadcastSentToBuy(int broadcastSentToBuy) {
		this.broadcastSentToBuy = broadcastSentToBuy;
	}
	public int getBroadcastSentToSell() {
		return broadcastSentToSell;
	}
	public void setBroadcastSentToSell(int broadcastSentToSell) {
		this.broadcastSentToSell = broadcastSentToSell;
	}
	public double getTargetLocationX() {
		return targetLocationX;
	}
	public void setTargetLocationX(double targetLocationX) {
		this.targetLocationX = targetLocationX;
	}
	public double getTargetLocationY() {
		return targetLocationY;
	}
	public void setTargetLocationY(double targetLocationY) {
		this.targetLocationY = targetLocationY;
	}
	public double getParkedLocationX() {
		return parkedLocationX;
	}
	public void setParkedLocationX(double parkedLocationX) {
		this.parkedLocationX = parkedLocationX;
	}
	public double getParkedLocationY() {
		return parkedLocationY;
	}
	public void setParkedLocationY(double parkedLocationY) {
		this.parkedLocationY = parkedLocationY;
	}
	public String getSaleType() {
		return saleType;
	}
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	public int getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}


}
