package sim;

import org.junit.Assert;
import org.junit.Test;

import sim.StandardSimulation;

public class StandardSimulationTest {
	
	@Test
	public void testEmptyInit(){
		StandardSimulation sim = new StandardSimulation();
		
		Assert.assertNull(sim.pop);
		Assert.assertNull(sim.worldMap);
	}

	@Test
	public void testInitializeWorldMap(){
		StandardSimulation sim = new StandardSimulation();
		sim.initializeWorldMap(4, 5);
		
		Assert.assertEquals(4, sim.worldMap.mapWidth);
		Assert.assertEquals(5, sim.worldMap.mapHeight);
	}
	
	@Test
	public void testPlaceFoodByNum(){
		StandardSimulation sim = new StandardSimulation();
		
		sim.initializeWorldMap(1,1);

		sim.placeFoodByNum(0);
		Assert.assertEquals(0, sim.worldMap.getGrid(0, 0));
		
		sim.placeFoodByNum(1);
		Assert.assertEquals(1, sim.worldMap.getGrid(0, 0));
	}
	
	@Test
	public void testInitializePopulation(){
		StandardSimulation sim = new StandardSimulation();
		sim.initializeWorldMap(1,1);

		sim.initializePopulation(1);
		Assert.assertEquals(2, sim.worldMap.getGrid(0, 0));
	}
	
	@Test
	public void testSetupWorld(){
		StandardSimulation sim = new StandardSimulation();
		sim.setupWorld(1, 1, 0.0, 1, -1);
		Assert.assertEquals(2, sim.worldMap.getGrid(0, 0));
	}
	
	@Test
	public void testSimulateTurn(){
		StandardSimulation sim = new StandardSimulation();
		sim.setupWorld(10, 10, 0.0, 1, -1);
		sim.simulateTurn(0);
		
		Assert.assertEquals(1, sim.worldMap.getCountForObjectType(2));
	}
}
