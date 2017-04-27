package controllers;

import java.util.List;

import events.AcquireResourceEvent;
import exceptions.InPastException;
import interfaces.SimulationEvent;
import interfaces.SimulationEventListener;
import interfaces.TimeLineListener;
import models.BarberShop;
import stats.SimulationStats;
import time.Clock;
import time.TimeLine;

public class Scheduler implements SimulationEventListener, TimeLineListener{

	private boolean isRunning = false;
	private boolean completedAct = false;
	private Integer totalCount = 0;
	private int incompleteCount = 0;
	private TimeLine timeLine;
	private Clock clock;
	private BarberShop shop;
	private List<SimulationEvent> currentClone;

	public Scheduler(Clock clock, BarberShop shop) {
		timeLine = new TimeLine(this);
		this.clock = clock;
		this.shop = shop;
	}

	public void start(){
		Scheduler me = this;
		if(!isRunning){
			new Thread(new Runnable(){

				@Override
				public void run() {
					isRunning = true;
					while(isRunning){
						tryAndRunCurrent(me);
					}
				}

				}).start();
			}
		}

		public synchronized void stop() {
			isRunning = false;
		}

		public void scheduleFutureEvent(SimulationEvent event){
			synchronized(timeLine){
				timeLine.scheduleFutureEvent(event);
			}
		}

		private void tryAndRunCurrent(Scheduler me){
			List<SimulationEvent> current = null;
			synchronized(timeLine){
				current = timeLine.getCurrentClone();
			}
			this.currentClone = current;
			if(current.isEmpty()){
				pullFromFuture();
			}else{
				synchronized(current){
					//System.out.println(current);
					for (SimulationEvent simulationEvent : current) {
						simulationEvent.registerListener(me);
						Thread thread = new Thread(simulationEvent);
						thread.start();
						synchronized(me){
							try {
								me.wait();
								thread.join();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
				checkForReRun();
			}
		}

		private void pullFromFuture(){
			SimulationEvent event = null;
			synchronized(timeLine){
				event = timeLine.getNextFutureEvent();
			}
			try {
				clock.advanceTime(event.getEventTime());
			} catch (InPastException e) {
				e.printStackTrace();
			}
			timeLine.scheduleCurrentEvent(event);
		}

		@Override
		public synchronized void onCompletedAct(SimulationEvent event) {
			completedAct = true;
			incrementTotal();
			synchronized (this) {
				this.notifyAll();
			}
		}

		@Override
		public synchronized void onIncompleteAct(SimulationEvent event) {
			incrementIncomplete();
			incrementTotal();
			synchronized (this) {
				this.notifyAll();
			}
		}

		private void incrementIncomplete(){
			incompleteCount++;
		}

		private void incrementTotal(){
			synchronized(totalCount){
				totalCount++;
			}
		}

		private void checkForReRun(){
			int currentCloneSize = 0;
			synchronized(currentClone){
				currentCloneSize = currentClone.size();
			}
			if(incompleteCount == currentCloneSize){
				pullFromFuture();
				resetCount();
			}
			else if(totalCount == currentCloneSize && totalCount != 0){
				if(completedAct){
					resetCount();
				}
			}
			else if(timeLine.getCurrent().isEmpty()){
				resetCount();
			}
			else if(timeLine.moreToDo()){
				resetCount();
			}
		}

		private void resetCount(){
			synchronized(totalCount){
				totalCount = 0;
			}
			incompleteCount = 0;
		}

		public Clock getClock() {
			return clock;
		}

		public TimeLine getTimeLine() {
			return timeLine;
		}

		@Override
		public void onFutureEventSchedule() {
		}

		@Override
		public void onCurrentEventSchedule() {

		}
	}
