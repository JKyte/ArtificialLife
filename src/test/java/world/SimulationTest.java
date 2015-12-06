package world;

import org.junit.Assert;
import org.junit.Test;

public class SimulationTest {
	
	@Test
	public void testEmptyInit(){
		Simulation sim = new Simulation();
		
		Assert.assertNull(sim.pop);
		Assert.assertNull(sim.worldMap);
	}

	@Test
	public void testInitializeWorldMap(){
		Simulation sim = new Simulation();
		sim.initializeWorldMap(4, 5);
		
		Assert.assertEquals(4, sim.worldMap.mapWidth);
		Assert.assertEquals(5, sim.worldMap.mapHeight);
	}
	
	@Test
	public void testPlaceFoodByPerc(){
		Simulation sim = new Simulation();
		sim.initializeWorldMap(2,2);

		sim.placeFoodByPerc(0.0);
		Assert.assertEquals(0, sim.worldMap.getGrid(0, 0));
		Assert.assertEquals(0, sim.worldMap.getGrid(1, 1));
		Assert.assertEquals(0, sim.worldMap.getGrid(1, 0));
		Assert.assertEquals(0, sim.worldMap.getGrid(1, 1));
		
		sim.placeFoodByPerc(1.0);
		Assert.assertEquals(1, sim.worldMap.getGrid(0, 0));
		Assert.assertEquals(1, sim.worldMap.getGrid(0, 1));
		Assert.assertEquals(1, sim.worldMap.getGrid(1, 0));
		Assert.assertEquals(1, sim.worldMap.getGrid(1, 1));
	}
	
	@Test
	public void testPlaceFoodByNum(){
		Simulation sim = new Simulation();
		
		sim.initializeWorldMap(1,1);

		sim.placeFoodByNum(0);
		Assert.assertEquals(0, sim.worldMap.getGrid(0, 0));
		
		sim.placeFoodByNum(1);
		Assert.assertEquals(1, sim.worldMap.getGrid(0, 0));
	}
	
	@Test
	public void testInitializePopulation(){
		Simulation sim = new Simulation();
		sim.initializeWorldMap(1,1);

		sim.initializePopulation(1);
		Assert.assertEquals(2, sim.worldMap.getGrid(0, 0));
	}
	
	@Test
	public void testSetupWorld(){
		Simulation sim = new Simulation();
		sim.setupWorld(1, 1, 0.0, 1);
		Assert.assertEquals(2, sim.worldMap.getGrid(0, 0));
	}
}
