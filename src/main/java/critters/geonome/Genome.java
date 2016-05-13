package critters.geonome;

import critters.genes.BaseGene;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by JKyte on 5/10/2016.
 */
public class Genome {

    private HashMap<String, BaseGene> critterGenes;

    public Genome(){
        this.critterGenes = new HashMap<String, BaseGene>();
    }

    public Genome(HashMap<String, BaseGene> critterGenes){
        this.critterGenes = critterGenes;
    }

    public String sequenceGeonome(){
        Set<String> keys = critterGenes.keySet();
        TreeSet<String> sortedKeys = new TreeSet<String>();
        for( String key : keys ){
            sortedKeys.add(key);
        }

        StringBuilder sb = new StringBuilder();
        for( String sortedKey : sortedKeys ){
            sb.append("[");
            sb.append(critterGenes.get(sortedKey).sequenceGene());
            sb.append("]");
        }

        return sb.toString().replaceAll("\\]\\[", ":");
    }

    public Set<String> getKeys(){
        return critterGenes.keySet();
    }

    public boolean hasGene( String key ){
        return critterGenes.containsKey(key);
    }

    public int getGeonomeLength(){
        return critterGenes.size();
    }

    public BaseGene get( String key ){
        return critterGenes.get(key);
    }

    public boolean put( String key, BaseGene value ){
        return ( null == critterGenes.put(key, value) );
    }

    @Override
    public String toString(){
        Set<String> keys = critterGenes.keySet();
        TreeSet<String> sortedKeys = new TreeSet<String>();
        for( String key : keys ){
            sortedKeys.add(key);
        }

//        for( String sortedKey : sortedKeys ){
//            System.out.println("Sorted Key: " + sortedKey);
//        }
        return null;    //  TODO for now
    }
}
