package population;

import critters.geneticcritter.GeneticCritter;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

/**
 * Created by JKyte on 5/23/2016.
 */
public class GeneticPopulation {

    private HashMap<UUID,GeneticCritter> popMap;

    public GeneticPopulation(){
        popMap = new HashMap<UUID, GeneticCritter>();
    }

    public void addCritter(GeneticCritter critter){
        popMap.put(critter.getUuid(), critter);
    }

    public GeneticCritter getCritter(UUID uuid){
        return popMap.get(uuid);
    }

    public void replaceCritter(GeneticCritter critter){
        popMap.replace(critter.getUuid(), critter);
    }

    public int getPopulationSize(){
        return popMap.size();
    }

    public Set<UUID> keySet(){
        return popMap.keySet();
    }

    public void dumpFullPopulationSequences(){
        for( UUID uuidKey : popMap.keySet() ){
            System.out.println( uuidKey + " : " + popMap.get(uuidKey).sequence() );
        }
    }
}
