package population;

import org.junit.Test;

/**
 * Created by JKyte on 5/25/2016.
 */
public class GeneticPopulationTest {

    @Test
    public void testDefaultCreate(){
        GeneticPopulation population = GeneticPopulationFactory.createDefaultGeneticPopulation();
        population.dumpFullPopulationSequences();
    }
}
