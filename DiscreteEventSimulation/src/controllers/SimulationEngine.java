package controllers;

import interfaces.ClockListener;
import models.BarberShop;
import stats.SimulationStats;
import time.Clock;
import tools.NewCustomerEventGenerator;

public class SimulationEngine implements ClockListener {

	private Clock clock;
	private Scheduler scheduler;
	private BarberShop shop;
	private Integer time;
	private Boolean forever;
	private SimulationStats stats;

	public SimulationEngine() {
		this.clock = new Clock(this);
		this.shop = new BarberShop();
		this.scheduler = new Scheduler(clock,shop);
		this.forever = false;
		this.time = 0;
		clock.setStatsTime(0);
		this.stats = new SimulationStats();
	}

	public void simulate(int time){
		this.time = time;
		forever = this.time == 0;
		NewCustomerEventGenerator gen = new NewCustomerEventGenerator(clock, shop, scheduler);
		scheduler.scheduleFutureEvent(gen.generateNewCustomer());
		scheduler.start();
	}

	public void simulate(){
		forever = time == 0;
		NewCustomerEventGenerator gen = new NewCustomerEventGenerator(clock, shop, scheduler);
		scheduler.scheduleFutureEvent(gen.generateNewCustomer());
		scheduler.start();
	}

	@Override
	public synchronized void onTimeAdvance(int currentTime) {
		if(currentTime >= time && !forever){
			scheduler.stop();
			System.out.println("Average Wait Time: " + (stats.averageWaitTime()));
		}
	}
}
