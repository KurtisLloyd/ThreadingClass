package interfaces;

public interface SimulationEventListener {

	public void onCompletedAct(SimulationEvent event);
	public void onIncompleteAct(SimulationEvent event);
	
}
