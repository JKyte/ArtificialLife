package critters.geonome;

import critters.genes.Gene;
import critters.genes.GeneType;
import critters.genes.Sequenceable;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by JKyte on 5/10/2016.
 */
public class Genome implements Sequenceable {

    protected HashMap<String, Gene> critterGenes;

    public Genome(){
        this.critterGenes = new HashMap<String, Gene>();
    }

    public Genome(HashMap<String, Gene> critterGenes){
        this.critterGenes = critterGenes;
    }

    public String sequence() {

        TreeSet<String> sortedKeys = new TreeSet<String>(critterGenes.keySet());

        StringBuilder sb = new StringBuilder();
        for( String sortedKey : sortedKeys ){
            sb.append("[");
            sb.append(critterGenes.get(sortedKey).sequence());
            sb.append("]");
        }

        return sb.toString().replaceAll("\\]\\[", ":");
    }

    public HashMap<String, Gene> getGenes(){
        return critterGenes;
    }

    public Set<String> getKeys(){
        return critterGenes.keySet();
    }

    public boolean hasGene( GeneType key ){
        return critterGenes.containsKey(key);
    }

    public int getLength(){
        return critterGenes.size();
    }

    public Gene getGene(String key){
        return critterGenes.get(key);
    }

    public void updateGeneValue(String key, int updateValue){
        if( critterGenes.get(key) == null ){
            return;
        }
        updateValue += getGeneValue(key);
        critterGenes.get(key).setCurrentValue(updateValue);
    }

    public void decrementGeneValue( String key, int decrementValue ){
        if( critterGenes.get(key) == null ){
            return;
        }
        System.out.println("Before: " + key + " " + critterGenes.get(key).getCurrentValue());
        critterGenes.get(key).decrementCurrentValue(decrementValue);
        System.out.println("After: " + key + " " + critterGenes.get(key).getCurrentValue());
    }

    public int getGeneValue(String key){
        return critterGenes.get(key).getCurrentValue();
    }

    public boolean put( String key, Gene value ){
        return ( null == critterGenes.put(key, value) );
    }
}
