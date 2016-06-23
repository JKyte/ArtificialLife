package critters.geonome;

import critters.genes.Gene;

import java.util.TreeSet;

/**
 * Created by JKyte on 6/21/2016.
 */
public class GenomeHelper {

    public static String getCurrentValuesString(Genome genome){
        StringBuilder sb = new StringBuilder();

        Gene gene;
        TreeSet<String> sortedKeys = new TreeSet<String>(genome.getKeys());
        for( String sortedKey : sortedKeys ){
            gene = genome.getGene(sortedKey);
            sb.append("[");
            sb.append(gene.getName()).append(".");
            sb.append(gene.getCurrentValue()).append("/");
            sb.append(gene.getStartValue());
            sb.append("]");
        }

        return sb.toString().replaceAll("\\]\\[", ":");
    }
}
