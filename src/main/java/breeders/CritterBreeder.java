package breeders;

import com.google.common.base.Preconditions;
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

    public static Genome breedCritterByMutation(Genome genome, int mutationRate){
        return breedCritterByMutation(genome, mutationRate, DEFAULT_NEW_GENE_RATE_BOUND);
    }

    public static Genome breedCritterByMutation(Genome genome, int mutationRate, int newGeneRate){
        HashMap<String,Gene> childGenes = genome.getGenes();

        Random rand = new Random();

        boolean mutateExistingGene = (0 == rand.nextInt(mutationRate));

        boolean createNewGene = (0 == rand.nextInt(newGeneRate));


        if( mutateExistingGene ){
            //  Pick a random gene
            String[] keysArray = childGenes.keySet().toArray(new String[childGenes.size()]);
            int index = rand.nextInt(keysArray.length);
            String keyToMutate = keysArray[index];

            Gene geneToMutate = childGenes.remove(keyToMutate);

            //  Mutate gene
            geneToMutate = randomizeGene(geneToMutate);

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
     * TODO -- expand to change the variable delta itself.
     * @param gene
     * @return
     */
    public static Gene randomizeGene(Gene gene){
        Gene newGene = Gene.deepCopyGene(gene);

        long zeroToDecrement = System.currentTimeMillis() % 2;

        if( zeroToDecrement == 0 ){
//            System.out.println("Decrement.");
            decrementByVariableDelta(newGene);
        }else{
//            System.out.println("Increment.");
            incrementByVariableDelta(newGene);
        }

//        System.out.println("ORIG: " + gene.sequence());
//        System.out.println(" NEW: " + newGene.sequence());


        //  In the event that the wrong operation is done, do the opposite to ensure change
        if( gene.sequence().equals(newGene.sequence()) ){

            if( zeroToDecrement == 0 ){
                incrementByVariableDelta(newGene);
            }else{
                decrementByVariableDelta(newGene);
            }
        }

        return newGene;
    }

    public static void incrementByVariableDelta(Gene gene){
        gene.setStartValue(gene.getCurrentValue() + gene.getVariableDelta());
        gene.setMutateStartValue( gene.getCurrentValue()+gene.getVariableDelta());
    }

    public static void decrementByVariableDelta(Gene gene){
        gene.setStartValue(gene.getCurrentValue() - gene.getVariableDelta());
        gene.setMutateStartValue(gene.getCurrentValue() - gene.getVariableDelta());
    }
}
