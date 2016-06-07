package main;

import sim.SimulationManager;

public class IntegrationTests {

	SimulationManager sm;
	
	public IntegrationTests(){
		sm = new SimulationManager();
	}
	
	public void runIntegrationTests(){
		sm.runDefaultSimulation();
		sm.runTestSimOneCreature();
		sm.runFastSimulationLargeMap();
		sm.runFastSimulationSmallMap();
		sm.runHeadlessSimulation();
		sm.runHeadlessSimulation_largeMap();
		sm.runHeadlessSimulation_largePop();

        sm.runComplexSimulation_test();
        sm.runComplexSimulation_testGUI();
		System.out.println("Integration tests: PASSED");
	}
	
	public static void main(String[] args){
		IntegrationTests tests = new IntegrationTests();
		tests.runIntegrationTests();
	}
}
