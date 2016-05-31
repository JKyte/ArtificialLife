package critters.geneticcritter;

import critters.geonome.Genome;
import critters.geonome.GenomeFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by JKyte on 5/17/2016.
 */
public class GeneticCritterTest {

    @Test
    public void testSameGenomeDifferentUUIDs(){
        Genome genome = GenomeFactory.createSimpleGenome();
        GeneticCritter firstCritter = new GeneticCritter(genome);
        GeneticCritter secondCritter = new GeneticCritter(genome);

//        System.out.println("First : " + firstCritter.getUuid());
//        System.out.println("Second: " + secondCritter.getUuid());

        Assert.assertEquals(firstCritter.sequence(), secondCritter.sequence());
        Assert.assertNotEquals(firstCritter.getUuid(), secondCritter.getUuid());
    }

}
