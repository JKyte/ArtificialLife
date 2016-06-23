package population;

import critters.geneticcritter.GeneticCritter;
import critters.geonome.GenomeHelper;

import java.util.UUID;

/**
 * Created by JKyte on 6/21/2016.
 *
 * This class will aid in the calculation of statistics about a given population
 */
public class PopulationHelper {

    public static void printPopulationStats(GeneticPopulation population){
        System.out.println("Printing gene sequences");
        for( UUID uuid : population.keySet() ){
            System.out.println( uuid + "\t" + population.getCritter(uuid).sequence());
        }

        System.out.println("Printing gene sequences");
        GeneticCritter critter;
        for( UUID uuid : population.keySet() ){
            critter = population.getCritter(uuid);
            System.out.println( uuid + "\t" + GenomeHelper.getCurrentValuesString(critter));
        }
    }
}
