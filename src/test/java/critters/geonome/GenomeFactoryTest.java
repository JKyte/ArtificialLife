package critters.geonome;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by JKyte on 5/10/2016.
 */
public class GenomeFactoryTest {

    @Test
    public void testCreateGeonomeFactoryFromSequence(){
        Genome genome = GeonomeFactory.createSimpleGeonome();
        String geoSeq = genome.sequenceGeonome();

//        System.out.println("> " + geoSeq);
        Genome copy = GeonomeFactory.createGeonomeFromSequence(geoSeq);
//        System.out.println("> " + copy.sequenceGeonome());

        Assert.assertEquals(geoSeq, copy.sequenceGeonome());
    }

    @Test
    public void testGeneFactoryRandomizedCreate(){
        Genome complexGenome = GeonomeFactory.createComplexGeonome();
        Genome randomGenome = GeonomeFactory.createRandomizedGeonome(complexGenome);

//        System.out.println("Normal: " + complexGenome.sequenceGeonome());
//        System.out.println("Random: " + randomGenome.sequenceGeonome());

        Assert.assertFalse(complexGenome.sequenceGeonome().equals(randomGenome.sequenceGeonome()));
    }
}
