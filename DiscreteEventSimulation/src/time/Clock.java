package time;

import exceptions.InPastException;
import interfaces.ClockListener;

public class Clock {

	private int currentTime;
	private ClockListener listener;
	private int statsTime;

	public Clock(ClockListener listener) {
		this.listener = listener;
		this.statsTime = 0;
	}
	
	public Clock() {
		this.statsTime = 0;
	}

	public synchronized void advanceTime(int time) throws InPastException{
		if(time >= currentTime){
			this.currentTime = time;
			if(listener!=null){
				listener.onTimeAdvance(currentTime);
			}
		}else{
			throw new InPastException();
		}
	}

	public synchronized int getCurrentTime() {
		return currentTime;
	}

	public boolean aheadOfStatsTime(){
		return currentTime >= statsTime;
	}
	
	public synchronized int getStatsTime() {
		return statsTime;
	}
	
	public synchronized void setStatsTime(int statsTime) {
		this.statsTime = statsTime;
	}
}
