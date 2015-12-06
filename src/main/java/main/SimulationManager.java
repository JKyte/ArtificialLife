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
		sim.runSimulation(20, 100);
	}
	
	public void runFastSimulation(){
		sim = new Simulation();
		sim.setupWorld(15, 15, 0.3, 1);
		sim.runSimulation(7000, 10);
	}
	
	public void runTestSimOneCreature(){
		sim = new Simulation();
		sim.setupWorld(8, 8, 0.2, 1);
		sim.runSimulation(2, 500);
	}
}
