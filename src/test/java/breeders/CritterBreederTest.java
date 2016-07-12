package breeders;

import critters.genes.Gene;
import critters.genes.GeneFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by JKyte on 7/11/2016.
 */
public class CritterBreederTest {

    CritterBreeder breeder = new CritterBreeder();

    /**
     * Vision currently starts as 2, so it can increment/decrement as necessary
     */
    @Test
    public void testRandomizeGene(){
        Gene vision = GeneFactory.createVisionGene();

        String visionSequence = vision.sequence();

        Gene mutatedVision = breeder.randomizeGene(vision);

        Assert.assertNotEquals(visionSequence, mutatedVision.sequence());
    }
}
