package critters.geonome;

import org.junit.Test;

/**
 * Created by JKyte on 5/10/2016.
 */
public class GenomeTest {

    @Test
    public void testGenomePrint(){
        Genome genome = GenomeFactory.createSimpleGenome();
        genome.toString();
    }

    @Test
    public void testComplexGenomePrint(){
        Genome genome = GenomeFactory.createComplexGenome();
        genome.sequence();
    }
}
