package breeders;

import critters.genes.Gene;
import critters.genes.GeneFactory;
import critters.geneticcritter.GeneticCritter;
import critters.geneticcritter.GeneticCritterFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by JKyte on 7/11/2016.
 */
public class CritterBreederTest {

    /**
     * Vision currently starts as 2, so it can increment/decrement as necessary
     */
    @Test
    public void testRandomizeGene(){
        Gene vision = GeneFactory.createVisionGene();

        String visionSequence = vision.sequence();

        Gene mutatedVision = CritterBreeder.randomizeGene(vision);

        Assert.assertNotEquals(visionSequence, mutatedVision.sequence());
    }

    @Test
    public void testBreedByMutation_OneOption(){
        GeneticCritter critter = GeneticCritterFactory.createSimpleCritter();

        String originalSequence = critter.sequence();

        String mutatedSequence = CritterBreeder.breedCritterByMutation(critter, 1).sequence();

        Assert.assertNotEquals(originalSequence, mutatedSequence);
    }

    @Test
    public void testBreedByMutation_AllOptions(){
        GeneticCritter critter = GeneticCritterFactory.createSimpleCritter();

        String originalSequence = critter.sequence();

        String mutatedSequence = CritterBreeder.breedCritterByMutation(critter, 1, 1).sequence();

        Assert.assertNotEquals(originalSequence, mutatedSequence);
    }

    @Test
    public void testBreedByMutation_100Times(){
        GeneticCritter critter = GeneticCritterFactory.createSimpleCritter();

        String originalSequence = critter.sequence();

        String mutatedSequence;

        for( int ii = 0; ii < 100; ii++ ) {
            mutatedSequence = CritterBreeder.breedCritterByMutation(critter, 1, 1).sequence();
            Assert.assertNotEquals(originalSequence, mutatedSequence);
        }
    }
}
