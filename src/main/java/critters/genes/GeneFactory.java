package critters.genes;

import java.util.Random;

/**
 * Created by JKyte on 5/10/2016.
 */
public class GeneFactory {

    /*
        This is the most critical factory method, creating life from life
        //  TODO -- will need to find a way to getGene the gene name from a cache or lookup
     */
    public static BaseGene createGeneFromSequence( String sequence ){
        String[] elements = sequence.split("\\.");

        //  TODO -- do stuff based on number of elements
        BaseGene gene = new BaseGene();
        gene.setName(elements[0]);
        gene.setGeneStartValue(Integer.parseInt(elements[1]));
        gene.setGeneCurrentValue(Integer.parseInt(elements[1]));
        gene.setGeneMinValue(Integer.parseInt(elements[2]));
        gene.setGeneMaxValue(Integer.parseInt(elements[3]));
        gene.setGeneMutateStepSize(Integer.parseInt(elements[4]));
        return gene;
    }

    /*
        This is also nice, creating a variety of life
     */
    public static BaseGene randomizeGeneStartValue( BaseGene gene ){
        BaseGene copy = new BaseGene(gene);
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

    public static BaseGene createSpeedGene(){
        BaseGene gene = new BaseGene();
        gene.setName(BaseGene.SPD);
        gene.setGeneStartValue(1);
        gene.setGeneCurrentValue(1);
        gene.setGeneMinValue(1);
        gene.setGeneMaxValue(5);
        gene.setGeneMutateStepSize(1);
        return gene;
    }

    public static BaseGene createVisionGene(){
        BaseGene gene = new BaseGene();
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

    public static BaseGene createHitPointGene(){
        BaseGene gene = new BaseGene();
        gene.setName(GeneType.HP);
        gene.setGeneStartValue(5);
        gene.setGeneCurrentValue(5);
        gene.setGeneMinValue(1);
        gene.setGeneMaxValue(25);
        gene.setGeneMutateStepSize(1);
        return gene;
    }

    public static BaseGene createVitalityGene(){
        BaseGene gene = new BaseGene();
        gene.setName(GeneType.VIT);
        gene.setGeneStartValue(15);
        gene.setGeneCurrentValue(15);
        gene.setGeneMinValue(10);
        gene.setGeneMaxValue(30);
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
     */
}
