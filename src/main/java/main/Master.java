package main;

import sim.SimulationManager;

public class Master {
	
	public static void main(String[] args){
		smallWorldOneCritter();
	}

	private static void smallWorldOneCritter() {
		SimulationManager sm = new SimulationManager();
		//	sm.runDefaultSimulation();
		//	sm.runTestSimOneCreature();
		//	sm.runFastSimulationLargeMap();
		//	sm.runFastSimulationSmallMap();
		sm.runHeadlessSimulation();
	}
	
}
