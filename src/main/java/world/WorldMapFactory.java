package world;

/**
 * Created by JKyte on 6/2/2016.
 */
public class WorldMapFactory {

    private final static double DEFAULT_FOOD_PERC = 0.25;

    public static WorldMap createStandardMapForTest(){
        WorldMap worldMap = new WorldMap(10);
        worldMap.placeFoodByPerc(DEFAULT_FOOD_PERC);
        return worldMap;
    }
}
