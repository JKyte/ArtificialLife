package critters.geonome;

import critters.genes.BaseGene;
import critters.genes.GeneFactory;

import java.util.Set;

/**
 * Created by JKyte on 5/10/2016.
 */
public class GeonomeFactory {

    /**
     * 0. Init new Genome
     * 1. Remove leading, trailing [brackets]
     * 2. Chunk into sub-sequences
     * 3. Create genes from sub-sequence and add to geonome
     * 4. return full Genome
     */
    public static Genome createGeonomeFromSequence(String rawSequence){
        Genome genome = new Genome();
        BaseGene gene;

        rawSequence = rawSequence.replaceAll("\\]", "").replaceAll("\\[", "");
        String[] subsequences = rawSequence.split(":");
        for( String subsequence : subsequences ){
            gene = GeneFactory.createGeneFromSequence(subsequence);
            genome.put(gene.getShortName(), gene);
        }
        return genome;
    }

    public static Genome createRandomizedGeonome( Genome genome){
        Genome copy = new Genome();
        BaseGene gene;
        Set<String> keys = genome.getKeys();
        for( String key : keys ){
            gene = GeneFactory.randomizeGeneStartValue(genome.get(key));
            copy.put(gene.getGeneName(), gene);
        }
        return copy;
    }

    public static Genome createSimpleGeonome(){
        Genome genome = new Genome();

        BaseGene speed = GeneFactory.createSpeedGene();
        BaseGene vision = GeneFactory.createVisionGene();

        genome.put(speed.getGeneName(), speed);
        genome.put( vision.getGeneName(), vision );

        return genome;
    }

    public static Genome createComplexGeonome(){
        Genome genome = new Genome();

        BaseGene speed = GeneFactory.createSpeedGene();
        BaseGene vision = GeneFactory.createVisionGene();
        BaseGene hitpoints = GeneFactory.createHitPointGene();
        BaseGene energy = GeneFactory.createEnergyGene();

        genome.put( speed.getGeneName(), speed );
        genome.put( vision.getGeneName(), vision );
        genome.put( hitpoints.getGeneName(), hitpoints );
        genome.put( energy.getGeneName(), energy );

        return genome;
    }
}
