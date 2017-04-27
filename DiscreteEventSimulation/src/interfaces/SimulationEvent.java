package interfaces;

public interface SimulationEvent extends Runnable{

	public void act();
	public void registerListener(SimulationEventListener listener);
	public int getEventTime();
	
}
