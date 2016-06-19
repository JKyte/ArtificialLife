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
        Assert.assertEquals(GeneType.SPEED, gene.getName());
        Assert.assertEquals(1, gene.getMutateMin());
        Assert.assertEquals(5, gene.getMutateMax());
        Assert.assertEquals(1, gene.getMutateStepSize());
        Assert.assertEquals(1, gene.getStartValue());
        Assert.assertEquals(1, gene.getVariableDelta());
        Assert.assertEquals(1, gene.getStartValue());
        Assert.assertEquals(1, gene.getCurrentValue());

        //  Iterate through setCurrentValue() options
        gene.setCurrentValue(-3);
        Assert.assertEquals(1, gene.getCurrentValue());

        gene.setCurrentValue(600);
        Assert.assertEquals(1, gene.getCurrentValue());

        gene.setCurrentValue(3);
        Assert.assertEquals(3, gene.getCurrentValue());

        //  Iterate through setStartValue() options
        gene.setStartValue(-3);
        Assert.assertEquals(1, gene.getStartValue());

        gene.setStartValue(600);
        Assert.assertEquals(1, gene.getStartValue());

        gene.setStartValue(3);
        Assert.assertEquals(3, gene.getStartValue());
    }
}
