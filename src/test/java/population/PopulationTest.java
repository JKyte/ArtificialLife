package population;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import sim.Simulation;
import critters.SimpleCritter;

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
	
	@Test
	public void testEndOfSimStats(){
		Simulation sim = new Simulation();
		Population pop = new Population(3, sim);
		pop.setDefaultPopulationStats();
		pop.generatePopulation();
		
		SimpleCritter ded = new SimpleCritter();
		ded.setAlive(false);
		pop.getPopMap().put(Integer.MAX_VALUE, ded);
		
		StringBuilder expected = new StringBuilder();
		expected.append("Alive: 3\nDead: 1\nTotal: 4\n");
		expected.append("Best  Fitness: 5 -- 5:10:[1]:1:1\n");
		expected.append("Worst Fitness: 0 -- 0:0:[-1]:0:0");
		
		Assert.assertEquals(expected.toString(), pop.endOfSimStats() );
	}
}
