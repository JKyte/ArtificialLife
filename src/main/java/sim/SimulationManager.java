package sim;


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

	public SimulationManager(){
		
	}
	
	public void runDefaultSimulation(){
		StandardSimulation sim = new StandardSimulation(550);
		sim.setupWorld(15, 15, 0.1);
		sim.initializePopulation(10);
		sim.runSimulation(20);
	}
	
	//	This configuration used to stress test a larger GUI
	public void runFastSimulationLargeMap(){
		StandardSimulation sim = new StandardSimulation(20);
		sim.setupWorld(60, 60, 0.4);
		sim.initializePopulation(45);
		sim.runSimulation(700);
	}
	
	//	This configuration used to stress test movement
	public void runFastSimulationSmallMap(){
		StandardSimulation sim = new StandardSimulation(100);
		sim.setupWorld(10, 10, 0.4);
		sim.initializePopulation(15);
		sim.runSimulation(1000);
	}

	public void runTestSimOneCreature(){
		StandardSimulation sim = new StandardSimulation(250);
		sim.setupWorld(8, 8, 0.2);
		sim.initializePopulation(1);
		sim.runSimulation(10);
	}
	
	public void runHeadlessSimulation(){
		HeadlessSimulation headless = new HeadlessSimulation();
		headless.setupWorld(100, 100, 0.5, 1);
		headless.initializePopulation(75);
		headless.runSimulation(350);
	}
	
	public void runHeadlessSimulation_largeMap(){
		HeadlessSimulation headless = new HeadlessSimulation();
		headless.setupWorld(10000, 10000, 0.8, 1);
		headless.initializePopulation(75);
		headless.runSimulation(1000);
	}

	public void runHeadlessSimulation_largePop(){
		HeadlessSimulation headless = new HeadlessSimulation();
		headless.setupWorld(1000, 1000, 0.8, 5);
		headless.initializePopulation(2000);
		headless.runSimulation(100000);
	}
}
