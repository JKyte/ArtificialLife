package critters.geneticcritter;

import critters.geonome.Genome;
import critters.geonome.GenomeFactory;

/**
 * Created by JKyte on 5/25/2016.
 */
public class GeneticCritterFactory {

    /**
     * @returns a GeneticCritter based on the SimpleGenome
     */
    public static GeneticCritter createSimpleCritter(){
        Genome simpleGenome = GenomeFactory.createSimpleGenome();
        return new GeneticCritter(simpleGenome);
    }

    public static GeneticCritter createComplexCritter(){
        Genome complexGenome = GenomeFactory.createComplexGenome();
        return new GeneticCritter(complexGenome);
    }

    public static GeneticCritter createRandomizedCritter(){
        Genome complexGenome = GenomeFactory.createComplexGenome();
        complexGenome = GenomeFactory.createRandomizedGenome(complexGenome);
        return new GeneticCritter(complexGenome);
    }
}
