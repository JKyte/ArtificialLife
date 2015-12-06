package main;

public class Master {
	
	public static void main(String[] args){
		smallWorldOneCritter();
	}

	private static void smallWorldOneCritter() {
		SimulationManager sm = new SimulationManager();
		sm.runDefaultSimulation();
	}
	
}
