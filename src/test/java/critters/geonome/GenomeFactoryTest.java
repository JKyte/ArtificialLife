package critters.geonome;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

/**
 * Created by JKyte on 5/10/2016.
 */
public class GenomeFactoryTest {

    @Test
    public void testCreateGenomeFactoryFromSequence(){
        Genome genome = GenomeFactory.createSimpleGenome();
        String geoSeq = genome.sequence();

        Genome copy = GenomeFactory.createGenomeFromSequence(geoSeq);

        Assert.assertEquals(geoSeq, copy.sequence());
    }

    @Test
    public void testGeneFactoryRandomizedCreate(){
        Genome complexGenome = GenomeFactory.createComplexGenome();
        HashSet<Genome> randomGenomes = new HashSet<Genome>();

        for( int ii = 0; ii < 50; ii++ ){
            randomGenomes.add(GenomeFactory.createRandomizedGenome(complexGenome));
        }

        Assert.assertFalse( randomGenomes.size() == 1 );
    }
}
