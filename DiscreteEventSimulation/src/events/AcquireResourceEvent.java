package events;

import controllers.Scheduler;
import interfaces.SimulationEvent;
import interfaces.SimulationEventListener;
import models.BarberShop;
import models.Customer;
import stats.SimulationStats;
import time.TimeLine;

public class AcquireResourceEvent implements SimulationEvent{

	private final String NAME = "Acquire ";
	private SimulationEventListener listener;
	private int eventTime;
	private Customer customer;
	private Scheduler scheduler;
	private int waitTime = 0;
	private int startTime = 0;
	private int endTime = 0;

	public AcquireResourceEvent(int time, Scheduler scheduler, Customer customer) {
		this.eventTime = time;
		this.scheduler = scheduler;
		this.customer = customer;
	}


	@Override
	public void run(){
		act();
	}

	@Override
	public void act() {
		SimulationEvent me = this;
		TimeLine timeline = scheduler.getTimeLine();
		BarberShop shop = customer.getShop();
		startTime = eventTime;
		shop.tryAndGetBarber(customer);
		if(shop.getBarber().isLockedByMe(customer)){
			ReleaseResourceEvent release = new ReleaseResourceEvent(scheduler, customer, scheduler.getClock().getCurrentTime());
			timeline.scheduleFutureEvent(release);
			timeline.getPresent().remove(me);
			endTime = scheduler.getClock().getCurrentTime();
			waitTime = endTime - startTime;
			SimulationStats.registerWaitTime(waitTime);
			listener.onCompletedAct(me);
		}
		else{
			listener.onIncompleteAct(me);
		}

	}

	@Override
	public void registerListener(SimulationEventListener listener) {
		this.listener = listener;
	}

	@Override
	public int getEventTime() {
		return eventTime;
	}

	@Override
	public String toString() {
		return NAME + eventTime;
	}
}
