package sim;

import org.junit.Assert;
import org.junit.Test;

public class BaseSimulationTest {

	@Test
	public void testEmptyInit(){
		BaseSimulation sim = new BaseSimulation();
		
		Assert.assertNull(sim.pop);
		Assert.assertNull(sim.worldMap);
	}

	@Test
	public void testInitializeWorldMap(){
		BaseSimulation sim = new BaseSimulation();
		sim.initializeWorldMap(4, 5);
		
		Assert.assertEquals(4, sim.worldMap.mapWidth);
		Assert.assertEquals(5, sim.worldMap.mapHeight);
	}
	
	@Test
	public void testPlaceFoodByNum(){
		BaseSimulation sim = new BaseSimulation();
		
		sim.initializeWorldMap(1,1);

		sim.placeFoodByNum(0);
		Assert.assertEquals(0, sim.worldMap.getGrid(0, 0));
		
		sim.placeFoodByNum(1);
		Assert.assertEquals(1, sim.worldMap.getGrid(0, 0));
	}
	
	@Test
	public void testPlaceFoodByPerc(){
		BaseSimulation sim = new BaseSimulation();
		
		sim.initializeWorldMap(1,1);
		Assert.assertEquals(0, sim.worldMap.getGrid(0, 0));
		
		sim.placeFoodByPerc(1.0);
		Assert.assertEquals(1, sim.worldMap.getGrid(0, 0));
	}
	
	@Test
	public void testInitializePopulation(){
		BaseSimulation sim = new BaseSimulation();
		sim.initializeWorldMap(1,1);

		sim.initializePopulation(1);
		Assert.assertEquals(2, sim.worldMap.getGrid(0, 0));
	}
	
	@Test
	public void testSetupWorld(){
		BaseSimulation sim = new BaseSimulation();
		sim.setupWorld(1, 1, 0.0, 1);
		Assert.assertEquals(2, sim.worldMap.getGrid(0, 0));
	}
}
