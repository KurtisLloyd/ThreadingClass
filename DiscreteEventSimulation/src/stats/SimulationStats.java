package stats;

import java.util.ArrayList;
import java.util.List;

public class SimulationStats {

	private float averageWaitTime = 0;
	
	private static List<Integer> waitTimes;
	private static List<Integer> totalCustomerTimes;
	
	public SimulationStats() {
		waitTimes = new ArrayList<>();
		totalCustomerTimes = new ArrayList<>();
	}
	
	public synchronized static void registerWaitTime(int time){
		if(waitTimes != null && time > 0){
			waitTimes.add(time);
//			System.out.println(time);
		}
	}  
	
	public synchronized static void registerTotalCustomerTime(int time){
		totalCustomerTimes.add(time);
	}
	
	public synchronized String averageWaitTime(){
		Float sum = 0f;
		
		for (Integer integer : totalCustomerTimes) {
			sum+=integer;
		}
		Float average = sum/waitTimes.size();
		return average.toString();
	}
}
