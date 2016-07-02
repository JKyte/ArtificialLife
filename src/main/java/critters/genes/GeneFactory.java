package critters.genes;

import java.util.Random;

/**
 * Created by JKyte on 5/10/2016.
 */
public class GeneFactory {

    /*
        This is the most critical factory method, creating life from life
     */
    public static Gene createGeneFromSequence( String sequence ){
        Gene gene = new Gene();
        String[] elements = sequence.split("\\.");
        gene.setName(elements[0]);
        gene.setMutateMin(Integer.parseInt(elements[1]));
        gene.setMutateMax(Integer.parseInt(elements[2]));
        gene.setMutateStepSize(Integer.parseInt(elements[3]));
        gene.setMutateStartValue(Integer.parseInt(elements[4]));    //  Current value not sequenced, copy start value.
        gene.setVariableDelta(Integer.parseInt(elements[5]));
        gene.setStartValue(gene.getMutateStartValue());
        gene.setCurrentValue(gene.getMutateStartValue());
        return gene;
    }

    /**
     *  They say variety is the spice of life...
     * @param gene -- the gene to randomize
     * @returns a copy of the Gene
     */
    public static Gene randomizeGeneStartValue( Gene gene ){
        Gene copy = new Gene(gene);
        Random rand = new Random();
        int newValue;
        if( rand.nextInt(2) == 0 ){

            //  If zero then increase the starting value
            newValue = (gene.getMutateStartValue()+gene.getVariableDelta());

            copy.setMutateStartValue(newValue);
            copy.setStartValue(newValue);
            copy.setCurrentValue(newValue);

        }else{

            //  Else decrease the starting value
            newValue = (gene.getMutateStartValue()-gene.getVariableDelta());

            copy.setMutateStartValue(newValue);
            copy.setStartValue(newValue);
            copy.setCurrentValue(newValue);
        }
        return copy;
    }

    /*
        Speed and Vision are needed for the most basic of simulations
     */

    /**
     * Create a speed gene with a default value of 1, max value of 5
     * @return
     */
    public static Gene createSpeedGene(){
        Gene gene = new Gene();
        gene.setName(Gene.SPEED);
        gene.setMutateMin(1);
        gene.setMutateMax(5);
        gene.setMutateStepSize(1);
        gene.setMutateStartValue(1);
        gene.setVariableDelta(1);
        gene.setStartValue(gene.getMutateStartValue());
        gene.setCurrentValue(gene.getMutateStartValue());
        return gene;
    }

    /**
     * Create a vision gene with a default value of 1, max value of 5
     * @return
     */
    public static Gene createVisionGene(){
        Gene gene = new Gene();
        gene.setName(GeneType.VISION);
        gene.setMutateMin(1);
        gene.setMutateMax(5);
        gene.setMutateStepSize(1);
        gene.setMutateStartValue(2);
        gene.setVariableDelta(1);
        gene.setStartValue(gene.getMutateStartValue());
        gene.setCurrentValue(gene.getMutateStartValue());
        return gene;
    }

    /*
        HitPoints and Energy are needed for survival simulations
     */

    /**
     * Create a HIT_POINTS gene with default value of 5, max value of 25
     * @return
     */
    public static Gene createHitPointGene(){
        Gene gene = new Gene();
        gene.setName(GeneType.HIT_POINTS);
        gene.setMutateMin(1);
        gene.setMutateMax(25);
        gene.setMutateStepSize(1);
        gene.setMutateStartValue(5);
        gene.setVariableDelta(1);
        gene.setStartValue(gene.getMutateStartValue());
        gene.setCurrentValue(gene.getMutateStartValue());
        return gene;
    }

    /**
     * Create a vitality gene with a default value of 15, max value of 30
     * @return
     */
    public static Gene createVitalityGene(){
        Gene gene = new Gene();
        gene.setName(GeneType.VITALITY);
        gene.setMutateMin(1);
        gene.setMutateMax(30);
        gene.setMutateStepSize(1);
        gene.setMutateStartValue(15);
        gene.setVariableDelta(1);
        gene.setStartValue(gene.getMutateStartValue());
        gene.setCurrentValue(gene.getMutateStartValue());
        return gene;
    }

    /**
     * Create a strength gene with a default value of 1, max value of 10
     * @return
     */
    public static Gene createStrengthGene(){
        Gene gene = new Gene();
        gene.setName(GeneType.STRENGTH);
        gene.setMutateMin(1);
        gene.setMutateMax(10);
        gene.setMutateStepSize(1);
        gene.setMutateStartValue(1);
        gene.setVariableDelta(1);
        gene.setStartValue(gene.getMutateStartValue());
        gene.setCurrentValue(gene.getMutateStartValue());
        return gene;
    }

    /*
        Future genes include but are not limited to...
        1. Strength (for fighting)
        2. Agility (for dodging)
        3. Toughness (for armor)
        4. Claws (for piercing dmg)
        5. Fur (for vitality/shield)
        6. Mutation Rate
        7. Reproductive Method (asexual, sexual)
        8. Jump
        9. Fly/Soar
        10. Food preference (Herbivore, Omnivore, Carvinore)
        11. Neural brains, if those start working
     */
}
