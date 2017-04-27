package tools;

import java.util.Random;

import controllers.Scheduler;
import events.NewCustomerEvent;
import models.BarberShop;
import time.Clock;
import time.TimeLine;

public class NewCustomerEventGenerator {

	private final double MAX_NUMBER = 1;
	private final int MEAN_TIME = 20;
	private Random random;
	private Clock clock;
	private BarberShop shop;
	private TimeLine timeLine;
	private Scheduler scheduler;
	
	public NewCustomerEventGenerator(Clock clock, BarberShop shop, Scheduler scheduler) {
		random = new Random();
		this.clock = clock;
		this.shop = shop;
		this.timeLine = scheduler.getTimeLine();
		this.scheduler = scheduler;
	}
	
	public NewCustomerEvent generateNewCustomer(){
		int eventTime = (int) generateRandomTime();
		eventTime += clock.getCurrentTime();
		return new NewCustomerEvent(eventTime, shop, timeLine, clock, scheduler);
	}
	
	
	private double generateRandomTime(){
		Double actualTime = 0.0;
		actualTime = -Math.log(MAX_NUMBER- random.nextDouble() -.1) * MEAN_TIME;
		if(actualTime.isNaN()|| actualTime < 0){
			actualTime = generateRandomTime();
		}
		return actualTime;
	}
}
