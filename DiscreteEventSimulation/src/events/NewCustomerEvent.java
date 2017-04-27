package events;

import controllers.Scheduler;
import interfaces.SimulationEvent;
import interfaces.SimulationEventListener;
import models.BarberShop;
import models.Customer;
import time.Clock;
import time.TimeLine;
import tools.NewCustomerEventGenerator;

public class NewCustomerEvent implements SimulationEvent{

	private final String NAME = "New Customer ";
	private SimulationEventListener listener;
	private int eventTime;
	private Customer customer;
	private TimeLine timeLine;
	private Clock clock;
	private BarberShop shop;
	private Scheduler scheduler;

	public NewCustomerEvent(int time, BarberShop shop, TimeLine timeline, Clock clock, Scheduler scheduler) {
		this.eventTime = time;
		customer = new Customer(shop);
		this.timeLine = timeline;
		this.clock = clock;
		this.shop = shop;
		this.scheduler = scheduler;
	}


	@Override
	public void run() {
		act();
	}

	@Override
	public void act() {
		NewCustomerEvent me = this;
		customer.setArriveTime(clock.getCurrentTime());
		createNewAquireEvent();
		NewCustomerEventGenerator gen = new NewCustomerEventGenerator(clock, shop, scheduler);
		SimulationEvent event = gen.generateNewCustomer();
		timeLine.scheduleFutureEvent(event);
		timeLine.getPresent().remove(me);
		listener.onCompletedAct(me);
	}

	private void createNewAquireEvent(){
		synchronized(timeLine){
			AcquireResourceEvent acquire = new AcquireResourceEvent(eventTime, scheduler, customer);
			timeLine.scheduleFutureEvent(acquire);
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
