package events;

import controllers.Scheduler;
import interfaces.SimulationEvent;
import interfaces.SimulationEventListener;
import models.BarberShop;
import models.Customer;
import stats.SimulationStats;

public class ReleaseResourceEvent implements SimulationEvent{

	private final int TIME_IT_TAKES = 15;
	private final String NAME = "Release Customer ";
	private SimulationEventListener listener;
	private int eventTime;
	private Customer customer;
	private Scheduler scheduler;

	public ReleaseResourceEvent(Scheduler scheduler, Customer customer, int lockedTime) {
		this.eventTime = lockedTime + TIME_IT_TAKES;
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
		BarberShop shop = customer.getShop();
		if(shop.getBarber().isLockedByMe(customer)){
			shop.getBarber().releaseResource(customer);
			if(!shop.getBarber().isLockedByMe(customer)){
				scheduler.getTimeLine().getCurrent().remove(me);
				customer.setEndTime(eventTime);
				if(scheduler.getClock().aheadOfStatsTime()){
					SimulationStats.registerTotalCustomerTime(customer.getTotalTime());
					listener.onCompletedAct(me);
				}
			}else{
				listener.onIncompleteAct(me);
			}
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
