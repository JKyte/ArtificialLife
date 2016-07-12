package breeders;

import critters.genes.Gene;
import critters.geonome.Genome;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by JKyte on 7/11/2016.
 */
public class CritterBreeder {

    private static final int DEFAULT_MUTATION_RATE_BOUND = 100;    //  1 in 100 chance
    private static final int DEFAULT_NEW_GENE_RATE_BOUND = 1000;   //  1 in 1000 chance


    public static Genome breedCritterByMutation(Genome genome){
        return breedCritterByMutation(genome, DEFAULT_MUTATION_RATE_BOUND);
    }

    public static Genome breedCritterByMutation(Genome genome, double mutationRate){
        return breedCritterByMutation(genome, mutationRate, DEFAULT_NEW_GENE_RATE_BOUND);
    }

    public static Genome breedCritterByMutation(Genome genome, double mutationRate, double newGeneRate){
        HashMap<String,Gene> childGenes = genome.getGenes();

        Random rand = new Random();

        boolean mutateExistingGene = (1 == rand.nextInt(DEFAULT_MUTATION_RATE_BOUND));

        boolean createNewGene = (1 == rand.nextInt(DEFAULT_NEW_GENE_RATE_BOUND));


        if( mutateExistingGene ){
            //  Pick a random gene
            String[] keysArray = childGenes.keySet().toArray(new String[childGenes.size()]);
            int index = rand.nextInt(keysArray.length);
            String keyToMutate = keysArray[index];

            Gene geneToMutate = childGenes.remove(keyToMutate);

            //  Mutate gene

            childGenes.put(keyToMutate, geneToMutate);
        }

        if( createNewGene ){
            //  Unhandled future case
        }

        return new Genome(childGenes);
    }

    /**
     * Currently this method only changes the Gene's startValue and currentValue.
     *
     * TODO -- expand to change the variable delta.
     * @param gene
     * @return
     */
    public Gene randomizeGene(Gene gene){
        Gene newGene = new Gene(gene);

        long zeroToDecrement = System.currentTimeMillis() % 2;

        if( zeroToDecrement == 0 ){
            newGene.setStartValue(newGene.getCurrentValue() - newGene.getVariableDelta());
            newGene.setMutateStartValue(newGene.getCurrentValue() - newGene.getVariableDelta());
        }else{
            newGene.setStartValue( newGene.getCurrentValue()+newGene.getVariableDelta());
            newGene.setMutateStartValue( newGene.getCurrentValue()+newGene.getVariableDelta());
        }

//        System.out.println("ORIG: " + gene.sequence());
//        System.out.println(" NEW: " + newGene.sequence());

        return newGene;
    }
}
