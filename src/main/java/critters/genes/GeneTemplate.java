package critters.genes;

/**
 * Created by JKyte on 5/10/2016.
 */
public interface GeneTemplate {

    String sequenceGene();  //  Maybe make this an "interface sequencable" ?

    boolean mutate();
}
