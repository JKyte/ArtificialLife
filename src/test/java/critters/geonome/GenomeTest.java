package critters.geonome;

import org.junit.Test;

/**
 * Created by JKyte on 5/10/2016.
 */
public class GenomeTest {

    @Test
    public void testGeonomePrint(){
        Genome genome = GeonomeFactory.createSimpleGeonome();
        genome.toString();
    }

    @Test
    public void testComplexGeonomePrint(){
        Genome genome = GeonomeFactory.createComplexGeonome();
        genome.sequenceGeonome();
    }
}
