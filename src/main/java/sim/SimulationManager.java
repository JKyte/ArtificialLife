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

	StandardSimulation sim;
	
	public SimulationManager(){
		
	}
	
	public void runDefaultSimulation(){
		sim = new StandardSimulation(750);
		sim.setupWorld(15, 15, 0.1, 10);
		sim.runSimulation(20);
	}
	
	//	This configuration used to stress test a larger GUI
	public void runFastSimulationLargeMap(){
		sim = new StandardSimulation(20);
		sim.setupWorld(60, 60, 0.4, 45);
		sim.runSimulation(700);
	}
	
	//	This configuration used to stress test movement
	public void runFastSimulationSmallMap(){
		sim = new StandardSimulation(100);
		sim.setupWorld(10, 10, 0.4, 15);
		sim.runSimulation(1000);
	}

	public void runTestSimOneCreature(){
		sim = new StandardSimulation();
		sim.setupWorld(8, 8, 0.2, 1);
		sim.runSimulation(10);
	}
	
	public void runHeadlessSimulation(){
		HeadlessSimulation headless = new HeadlessSimulation();
		headless.setupWorld(100, 100, 0.5, 75, 1);
		//	headless.setupWorld(10000, 10000, 0.5, 75, 1);
		headless.runSimulation(350);
	}
}
