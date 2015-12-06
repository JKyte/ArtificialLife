package population;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import critters.SimpleCritter;
import world.Simulation;

public class PopulationTest {

	@Test
	public void testInit(){
		Simulation sim = new Simulation();
		Population pop = new Population(1, sim);
		
		Assert.assertEquals(1, pop.getPopSize());
	}
	
	@Test
	public void testGeneratePopulation(){
		Simulation sim = new Simulation();
		Population pop = new Population(1, sim);
		pop.setDefaultPopulationStats();
		pop.generatePopulation();
		
		HashMap<Integer, SimpleCritter> population = pop.getPopMap();
		Assert.assertEquals(1, population.size());
		
		int key = population.keySet().iterator().next();
		SimpleCritter crit = population.get(key);
		
		Assert.assertEquals(0, crit.getGenerationID());
		Assert.assertEquals(0, crit.getCritterID());
		Assert.assertEquals(1, crit.getSpeed());
		Assert.assertEquals(1, crit.getVision());
		Assert.assertEquals(5, crit.getCurHealth());
		Assert.assertEquals(10, crit.getCurEnergy());
		Assert.assertEquals(1, crit.getFood().length);
		Assert.assertEquals(1, crit.getFood()[0]);
	}
	
	@Test
	public void testPlacePopulation(){
		Simulation sim = new Simulation();
		sim.initializeWorldMap(1, 1);
		
		Population pop = new Population(1, sim);
		pop.setDefaultPopulationStats();
		pop.generatePopulation();
		pop.placePopulation();

		Assert.assertEquals(2, sim.worldMap.getGrid(0, 0));
	}
	
}
