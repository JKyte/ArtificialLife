package critters.geonome;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by JKyte on 5/10/2016.
 */
public class GenomeFactoryTest {

    @Test
    public void testCreateGeonomeFactoryFromSequence(){
        Genome genome = GenomeFactory.createSimpleGenome();
        String geoSeq = genome.sequence();

        Genome copy = GenomeFactory.createGenomeFromSequence(geoSeq);

        Assert.assertEquals(geoSeq, copy.sequence());
    }

    @Test
    public void testGeneFactoryRandomizedCreate(){
        Genome complexGenome = GenomeFactory.createComplexGenome();
        Genome randomGenome = GenomeFactory.createRandomizedGenome(complexGenome);

//        System.out.println("Normal: " + complexGenome.sequenceGeonome());
//        System.out.println("Random: " + randomGenome.sequenceGeonome());

        Assert.assertFalse(complexGenome.sequence().equals(randomGenome.sequence()));
    }
}
