package critters.geneticcritter;

import critters.geonome.Genome;
import utils.Coord;

import java.util.UUID;

/**
 * Created by JKyte on 5/15/2016.
 */
public class GeneticCritter extends Genome {

    //  Critter's world location in X,Y coordinates
    private Coord location;

    private UUID uuid;  //  Will need to sequence this also...

    private int lifeLength;

    private boolean alive;

    public GeneticCritter(Genome genome){
        this.critterGenes = genome.getGenes();
        uuid = UUID.randomUUID();
        lifeLength = 0;
        alive = true;
    }



    public Coord getLocation() {
        return location;
    }

    public void setLocation(Coord location) {
        this.location = location;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getLifeLength() {
        return lifeLength;
    }

    public void setLifeLength(int lifeLength) {
        this.lifeLength = lifeLength;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

}
