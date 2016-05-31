package critters.geneticcritter;

import critters.geonome.GenomeFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by JKyte on 5/26/2016.
 */
public class GeneticCritterFactoryTest {

    @Test
    public void testSimpleCritterCreate(){
        GeneticCritter critter = GeneticCritterFactory.createSimpleCritter();

        //  Assert critter-specific
        Assert.assertNotNull(critter.getUuid());
        Assert.assertEquals(0, critter.getLifeLength());
        Assert.assertNull(critter.getLocation());

        //  Assert genome-specific
        Assert.assertEquals(critter.sequence(), GenomeFactory.createSimpleGenome().sequence());


    }
}
