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
		//	This simulation config was used to stress test movement
		sim = new StandardSimulation();
		sim.setupWorld(15, 15, 0.1, 10);
		sim.runSimulation(20, 250);
	}
	
	public void runFastSimulationLargeMap(){
		sim = new StandardSimulation();
		sim.setupWorld(60, 60, 0.4, 45);
		sim.runSimulation(70000, 1);
	}
	
	public void runFastSimulationSmallMap(){
		sim = new StandardSimulation();
		sim.setupWorld(10, 10, 0.4, 15);
		sim.runSimulation(1000, 1);
	}
	
	public void runTestSimOneCreature(){
		sim = new StandardSimulation();
		sim.setupWorld(8, 8, 0.2, 1);
		sim.runSimulation(1, 500);
	}
	
	public void runHeadlessSimulation(){
		HeadlessSimulation headless = new HeadlessSimulation();
		headless.setupWorld(5000, 5000, 0.4, 1000);
		headless.runSimulation(35);
	}
}
