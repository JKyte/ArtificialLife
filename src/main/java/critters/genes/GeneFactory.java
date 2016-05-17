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
        gene.setGeneStartValue(Integer.parseInt(elements[1]));
        gene.setGeneCurrentValue(Integer.parseInt(elements[1]));    //  Current value not sequenced, copy start value.
        gene.setGeneMinValue(Integer.parseInt(elements[2]));
        gene.setGeneMaxValue(Integer.parseInt(elements[3]));
        gene.setGeneMutateStepSize(Integer.parseInt(elements[4]));
        return gene;
    }

    /*
        They say variety is the spice of life...
     */
    public static Gene randomizeGeneStartValue( Gene gene ){
        Gene copy = new Gene(gene);
        Random rand = new Random();
        int newValue;
        if( rand.nextInt(2) == 0 ){

            //  If zero then increase the starting value
            //  TODO -- need to ensure we don't go above max/min
            newValue = (gene.getGeneStartValue()+gene.getGeneMutateStepSize());

            if( newValue <= copy.getGeneMaxValue() ){
                copy.setGeneStartValue( newValue );
                copy.setGeneCurrentValue( newValue );
            }
        }else{

            //  Else decrease the starting value
            newValue = (gene.getGeneStartValue()-gene.getGeneMutateStepSize());
            if( newValue >= copy.getGeneMinValue() ){
                copy.setGeneStartValue( newValue );
                copy.setGeneCurrentValue( newValue );
            }
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
        gene.setName(Gene.SPD);
        gene.setGeneStartValue(1);
        gene.setGeneCurrentValue(1);
        gene.setGeneMinValue(1);
        gene.setGeneMaxValue(5);
        gene.setGeneMutateStepSize(1);
        return gene;
    }

    /**
     * Create a vision gene with a default value of 1, max value of 5
     * @return
     */
    public static Gene createVisionGene(){
        Gene gene = new Gene();
        gene.setName(GeneType.VIS);
        gene.setGeneStartValue(1);
        gene.setGeneCurrentValue(1);
        gene.setGeneMinValue(1);
        gene.setGeneMaxValue(5);
        gene.setGeneMutateStepSize(1);
        return gene;
    }

    /*
        HitPoints and Energy are needed for survival simulations
     */

    /**
     * Create a HP gene with default value of 5, max value of 25
     * @return
     */
    public static Gene createHitPointGene(){
        Gene gene = new Gene();
        gene.setName(GeneType.HP);
        gene.setGeneStartValue(5);
        gene.setGeneCurrentValue(5);
        gene.setGeneMinValue(1);
        gene.setGeneMaxValue(25);
        gene.setGeneMutateStepSize(1);
        return gene;
    }

    /**
     * Create a vitality gene with a default value of 15, max value of 30
     * @return
     */
    public static Gene createVitalityGene(){
        Gene gene = new Gene();
        gene.setName(GeneType.VIT);
        gene.setGeneStartValue(15);
        gene.setGeneCurrentValue(15);
        gene.setGeneMinValue(1);
        gene.setGeneMaxValue(30);
        gene.setGeneMutateStepSize(1);
        return gene;
    }

    /**
     * Create a strength gene with a default value of 1, max value of 10
     * @return
     */
    public static Gene createStrengthGene(){
        Gene gene = new Gene();
        gene.setName(GeneType.STR);
        gene.setGeneStartValue(1);
        gene.setGeneCurrentValue(1);
        gene.setGeneMinValue(1);
        gene.setGeneMaxValue(10);
        gene.setGeneMutateStepSize(1);
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
