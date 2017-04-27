package models;

public class Customer extends Mover{

	private BarberShop shop;
	private boolean withBarber;
	private int arriveTime;
	private int endTime;
	private int totalTime;

	public Customer(BarberShop shop){
		super();
		this.shop = shop;
		withBarber = false;
		this.arriveTime = 0;
		this.endTime = 0;
		this.totalTime = 0;
	}
	
	public synchronized boolean isWithBarber() {
		return withBarber;
	}
	
	public synchronized BarberShop getShop() {
		return shop;
	}
	
	public synchronized void setArriveTime(int arriveTime) {
		this.arriveTime = arriveTime;
	}
	
	public synchronized void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	public synchronized int getTotalTime() {
		totalTime = endTime - arriveTime;
		return totalTime;
	}
	
}
