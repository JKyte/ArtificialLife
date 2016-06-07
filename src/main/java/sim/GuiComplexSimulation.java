package sim;

import world.GUI;

/**
 * Created by JKyte on 6/6/2016.
 */
public class GuiComplexSimulation extends ComplexSimulation {

    private GUI gui;

    private int DELAY_IN_MILLIS = 1000;

    public GuiComplexSimulation(){

    }

    public GuiComplexSimulation( int delayInMillis ){
        this.DELAY_IN_MILLIS = delayInMillis;
    }

    @Override
    public void runSimulation(){
        createAndDisplayFixedGUI();
        super.runSimulation();
        updateGUI("Turn: " + currentTurn);
    }

    @Override
    public void simulateTurn() {
        try {
            Thread.sleep(DELAY_IN_MILLIS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updateGUI("Turn: " + currentTurn);
        super.simulateTurn();
    }

    public void createAndDisplayDynamicGUI(){
        gui = new GUI(worldMap.mapWidth, worldMap.mapHeight);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void createAndDisplayFixedGUI(){
        gui = new GUI();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateGUI(){
        gui.clearAndSetText(worldMap.getMapForGUI());
    }

    public void updateGUI( String stats ){
        System.out.println("Update GUI.");
        gui.clearAndSetText(worldMap.getMapForGUI(stats));
    }

}
