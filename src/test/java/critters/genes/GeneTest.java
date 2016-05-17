package critters.genes;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by JKyte on 5/16/2016.
 */
public class GeneTest {

    @Test
    public void testGeneMethods(){
        Gene gene = GeneFactory.createSpeedGene();

        //  Assert proper gene creation
        Assert.assertEquals(GeneType.SPD, gene.getName());
        Assert.assertEquals(1, gene.getGeneStartValue());
        Assert.assertEquals(1, gene.getGeneCurrentValue());
        Assert.assertEquals(1, gene.getGeneMinValue());
        Assert.assertEquals(5, gene.getGeneMaxValue());
        Assert.assertEquals(1, gene.getGeneMutateStepSize());

        //  Iterate through setCurrentValue() options
        gene.setGeneCurrentValue(-3);
        Assert.assertEquals(1, gene.getGeneCurrentValue());

        gene.setGeneCurrentValue(600);
        Assert.assertEquals(1, gene.getGeneCurrentValue());

        gene.setGeneCurrentValue(3);
        Assert.assertEquals(3, gene.getGeneCurrentValue());

        //  Iterate through setStartValue() options
        gene.setGeneStartValue(-3);
        Assert.assertEquals(1, gene.getGeneStartValue());

        gene.setGeneStartValue(600);
        Assert.assertEquals(1, gene.getGeneStartValue());

        gene.setGeneStartValue(3);
        Assert.assertEquals(3, gene.getGeneStartValue());
    }
}
