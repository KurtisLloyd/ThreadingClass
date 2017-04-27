package time;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import comparing.SimulationEventComparator;
import interfaces.SimulationEvent;
import interfaces.TimeLineListener;

public class TimeLine {

	private ArrayList<SimulationEvent> present;
	private PriorityQueue<SimulationEvent> future;
	private TimeLineListener listener;
	
	public TimeLine(TimeLineListener listener) {
		this.listener = listener;
		present = new ArrayList<>();
		future = new PriorityQueue<>(new SimulationEventComparator());
	}
	
	public synchronized void scheduleCurrentEvent(SimulationEvent event){
		present.add(event);
		listener.onCurrentEventSchedule();
	}
	
	public synchronized void scheduleFutureEvent(SimulationEvent event){
		future.add(event);
		listener.onFutureEventSchedule();
	}
	
	public synchronized List<SimulationEvent> getCurrent(){
		return this.present;
	}
	
	public synchronized List<SimulationEvent> getCurrentClone(){
		List<SimulationEvent> clone = new ArrayList<>();
		for (SimulationEvent simulationEvent : present) {
			clone.add(simulationEvent);
		}
		return clone;
	}
	
	public synchronized ArrayList<SimulationEvent> getPresent() {
		return present;
	}
	
	public synchronized PriorityQueue<SimulationEvent> getFuture() {
		return future;
	}
	
	public synchronized int getCurrentSize(){
		return present.size();
	}
	
	public synchronized boolean moreToDo(){
		return !future.isEmpty();
	}
	
	public synchronized SimulationEvent getNextFutureEvent(){
		return future.remove();
	}
	
}
