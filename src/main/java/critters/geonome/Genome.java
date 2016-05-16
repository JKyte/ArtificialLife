package critters.geonome;

import critters.genes.BaseGene;
import critters.genes.GeneType;
import critters.genes.Sequenceable;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by JKyte on 5/10/2016.
 */
public class Genome implements Sequenceable {

    private HashMap<String, BaseGene> critterGenes;

    public Genome(){
        this.critterGenes = new HashMap<String, BaseGene>();
    }

    public Genome(HashMap<String, BaseGene> critterGenes){
        this.critterGenes = critterGenes;
    }

    public String sequence() {
        Set<String> keys = critterGenes.keySet();
        TreeSet<String> sortedKeys = new TreeSet<String>();
        for( String key : keys ){
            sortedKeys.add(key);
        }

        StringBuilder sb = new StringBuilder();
        for( String sortedKey : sortedKeys ){
            sb.append("[");
            sb.append(critterGenes.get(sortedKey).sequence());
            sb.append("]");
        }

        return sb.toString().replaceAll("\\]\\[", ":");
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

    public BaseGene getGene(String key){
        return critterGenes.get(key);
    }

    public boolean put( String key, BaseGene value ){
        return ( null == critterGenes.put(key, value) );
    }
}
