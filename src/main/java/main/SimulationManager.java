package main;

import world.Simulation;

/**
 * 
 * @author JKyte
 * 
 * This class will direct higher level functions of the simulation via the World class, primarily
 * functions regarding population population fitness, brain training for Neural Networks, etc.
 * 
 * A number of default configurations shall exist.
 *
 */
public class SimulationManager {

	Simulation sim;
	
	public SimulationManager(){
		
	}
	
	public void runDefaultSimulation(){
		//	This simulation config was used to stress test movement
		sim = new Simulation();
		sim.setupWorld(15, 15, 0.1, 10);
		sim.runSimulation(20, 250);
	}
	
	public void runFastSimulationLargeMap(){
		sim = new Simulation();
		sim.setupWorld(60, 60, 0.4, 45);
		sim.runSimulation(70000, 1);
	}
	
	public void runFastSimulationSmallMap(){
		sim = new Simulation();
		sim.setupWorld(10, 10, 0.4, 15);
		sim.runSimulation(1000, 1);
	}
	
	public void runTestSimOneCreature(){
		sim = new Simulation();
		sim.setupWorld(8, 8, 0.2, 1);
		sim.runSimulation(1, 500);
	}
}
