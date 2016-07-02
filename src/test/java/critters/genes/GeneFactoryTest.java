package critters.genes;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

/**
 * Created by JKyte on 5/10/2016.
 */
public class GeneFactoryTest {

    @Test
    public void testCreateSpeedGene(){
        String expectedSequence = "SPD.1.5.1.1.1";
        Gene speed = GeneFactory.createSpeedGene();
        Assert.assertEquals(expectedSequence, speed.sequence());
    }

    @Test
    public void testCreateVisionGene(){
        String expectedSequence = "VIS.1.5.1.2.1";
        Gene speed = GeneFactory.createVisionGene();
        Assert.assertEquals(expectedSequence, speed.sequence());
    }

    @Test
    public void testCreateHitPointGene(){
        String expectedSequence = "HP.1.25.1.5.1";
        Gene speed = GeneFactory.createHitPointGene();
        Assert.assertEquals(expectedSequence, speed.sequence());
    }

    @Test
    public void testCreateVitalityGene(){
        String expectedSequence = "VIT.1.30.1.15.1";
        Gene speed = GeneFactory.createVitalityGene();
        Assert.assertEquals(expectedSequence, speed.sequence());
    }

    @Test
    public void testCreateStrengthGene(){
        String expectedSequence = "STR.1.10.1.1.1";
        Gene speed = GeneFactory.createStrengthGene();
        Assert.assertEquals(expectedSequence, speed.sequence());
    }

    @Test
    public void testGeneFactoryRandomize() {
        Gene speed = GeneFactory.createSpeedGene();
//        System.out.println(speed.sequenceGene());

        HashSet<String> sequencedGenes = new HashSet<String>();
        for (int ii = 0; ii < 100; ii++) {
            speed = GeneFactory.randomizeGeneStartValue(speed);
            sequencedGenes.add(speed.sequence());
//            System.out.println(speed.sequence());
        }

        //  Assert that the randomizedGenes generated more than 1 unique sequence
        Assert.assertTrue( sequencedGenes.size() > 1 );

    }

    @Test
    public void testGeneFactoryCreateFromSequence(){
        String rawSequence = "HP.1.25.5.1.1";
        Gene gene = GeneFactory.createGeneFromSequence(rawSequence);

        String sequence = gene.sequence();
        Assert.assertEquals(rawSequence, sequence);
    }

}
