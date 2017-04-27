package models;

public class BarberShop {

	private Resource barber;
	
	public BarberShop() {
		barber = new Barber();
	}
	
	public synchronized boolean tryAndGetBarber(Mover customer){
		boolean gotTheBarber = false;
		boolean canGetBarber = barber.canGetResource();
		if(canGetBarber){
			barber.lockResource(customer);
			gotTheBarber = barber.isLockedByMe(customer);
		}
		return gotTheBarber;
	}
	
	public synchronized boolean barberIsBusy(){
		return barber.isBusy();
	}
	
	public synchronized Resource getBarber() {
		return barber;
	}
	
}
