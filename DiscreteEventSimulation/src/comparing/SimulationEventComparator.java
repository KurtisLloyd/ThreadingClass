package comparing;

import java.util.Comparator;

import interfaces.SimulationEvent;

public class SimulationEventComparator implements Comparator<SimulationEvent>{

	@Override
	public int compare(SimulationEvent eventOne, SimulationEvent eventTwo) {
		int comparableNumber = 0;
		
		int eventOneTime = eventOne.getEventTime();
		int eventTwoTime = eventTwo.getEventTime();
		
		if(eventOneTime < eventTwoTime){
			comparableNumber = -1;
		}
		else if(eventOneTime > eventTwoTime){
			comparableNumber = 1;
		}
		
		return comparableNumber;
	}

}
