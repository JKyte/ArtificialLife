package critters.geonome;

import critters.genes.Gene;
import critters.genes.GeneFactory;

import java.util.Set;

/**
 * Created by JKyte on 5/10/2016.
 */
public class GenomeFactory {

    /**
     * 0. Init new Genome
     * 1. Remove leading, trailing [brackets]
     * 2. Chunk into sub-sequences
     * 3. Create genes from sub-sequence and add to genome
     * 4. return full Genome
     */
    public static Genome createGenomeFromSequence(String rawSequence){
        Genome genome = new Genome();
        Gene gene;

        rawSequence = rawSequence.replaceAll("\\]", "").replaceAll("\\[", "");
        String[] subsequences = rawSequence.split(":");
        for( String subsequence : subsequences ){
            gene = GeneFactory.createGeneFromSequence(subsequence);
            genome.put(gene.getName(), gene);
        }
        return genome;
    }

    public static Genome createRandomizedGenome(Genome genome){
        Genome copy = new Genome();
        Gene gene;
        Set<String> keys = genome.getKeys();
        for( String key : keys ){
            gene = GeneFactory.randomizeGeneStartValue(genome.getGene(key));
            copy.put(gene.getName(), gene);
        }
        return copy;
    }

    public static Genome createSimpleGenome(){
        Genome genome = new Genome();

        Gene speed = GeneFactory.createSpeedGene();
        Gene vision = GeneFactory.createVisionGene();

        genome.put(speed.getName(), speed);
        genome.put( vision.getName(), vision );

        return genome;
    }

    public static Genome createComplexGenome(){
        Genome genome = new Genome();

        Gene speed = GeneFactory.createSpeedGene();
        Gene vision = GeneFactory.createVisionGene();
        Gene hitpoints = GeneFactory.createHitPointGene();
        Gene energy = GeneFactory.createVitalityGene();

        genome.put( speed.getName(), speed );
        genome.put( vision.getName(), vision );
        genome.put( hitpoints.getName(), hitpoints );
        genome.put( energy.getName(), energy );

        return genome;
    }
}
