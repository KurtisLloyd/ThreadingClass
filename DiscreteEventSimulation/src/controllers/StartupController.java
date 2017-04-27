package controllers;

public class StartupController {
	
	private final int SIMULATION_TIME = 100000;
	private SimulationEngine engine;
	
	public StartupController() {
		this.engine = new SimulationEngine();
	}
	public void start() {
		engine.simulate(SIMULATION_TIME);
	}
}
