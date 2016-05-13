package critters.genes;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

/**
 * Created by JKyte on 5/10/2016.
 */
public class GeneFactoryTest {

    @Test
    public void testGeneFactorySequencing(){
        String expectedSequence = "SPD.1.1.5.1";
        BaseGene speed = GeneFactory.createSpeedGene();
        Assert.assertEquals(expectedSequence, speed.sequenceGene());
//        System.out.println(speed.sequenceGene());
    }

    @Test
    public void testGeneFactoryRandomize() {
        BaseGene speed = GeneFactory.createSpeedGene();
//        System.out.println(speed.sequenceGene());

        HashSet<String> sequencedGenes = new HashSet<String>();
        for (int ii = 0; ii < 100; ii++) {
            speed = GeneFactory.randomizeGeneStartValue(speed);
            sequencedGenes.add(speed.sequenceGene());
//            System.out.println(speed.sequenceGene());
        }

        //  Assert that the randomizedGenes generated more than 1 unique sequence
        Assert.assertTrue( sequencedGenes.size() > 1 );

    }

    @Test
    public void testGeneFactoryCreateFromSequence(){
        String rawSequence = "HP.5.1.25.1";
        BaseGene gene = GeneFactory.createGeneFromSequence(rawSequence);

        String sequence = gene.sequenceGene();
        Assert.assertEquals(rawSequence, sequence);
    }

}
