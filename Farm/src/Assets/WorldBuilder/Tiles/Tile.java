package Assets.WorldBuilder.Tiles;
import java.util.Random;

import Assets.Utilities.SystemUtility;
import Assets.WorldBuilder.Farm;
import Assets.WorldBuilder.Seeds.Seed;

import java.net.URL;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;

// Class for what methods and attributes a Tile must contain
public final class Tile {
    // Attributes
    private boolean isPlowed = false,
                    containsSeed = false,
                    isWithered = false;
    
    private int waterLevel = 0;
    private URL modelTextures;
    private Seed seedPlanted;

    // Tile counter tracket
    private static int totalTileCount = 0;
    private int tileNumber;

    /*
        Constructor, randomize which tile texture to use for tile, 
        track which tile number current tile object is, and increment total tile count for the class
    */
    public Tile(){
        Random rand = new Random();
        modelTextures = getClass().getResource("./Models/grass"+ rand.nextInt(1,11) +".obj");

        tileNumber = totalTileCount;
        totalTileCount++;
    }

    // Getters & Upders & Setters [Tile Attributes]
    public void updateWaterLevel(){
        // increment water level by 1
        waterLevel += 1;
    }
    public int getWaterLevel(){
        return waterLevel;
    }
    public void resetWaterLevel(){
        // set water level to 0
        waterLevel = 0;
    }
    public void updateIsPlowed(){
        // reverse boolean value of isPlowed
        isPlowed = !isPlowed;
    }
    public boolean getIsPlowed(){
        return isPlowed;
    }
    public void updateContainsSeed(){
        // reverse boolean value of containsSeed
        containsSeed = !containsSeed;
    }
    public boolean getContainsSeed(){
        return containsSeed;
    }
    public void updateIsWithered(){
        // reverse boolean value of isWithered
        isWithered = !isWithered;
    }
    public boolean getIsWithered(){
        return isWithered;
    }
    public int getTileNumber(){
        return tileNumber;
    }
    public void setSeedPlanted(Seed seedPlanted){
        this.seedPlanted = seedPlanted;
    }
    public Seed getSeedPlanted(){
        return seedPlanted;
    }

    // Getters & Setters [Texture Attributes]
    public void setModelTextures(URL modelTextures){
        this.modelTextures = modelTextures;
    }
    public URL getModelTextures(){
        return modelTextures;
    }

    // This function keeps track of which texture to use for each tile based on its state
    public void updateTileState(){
        // Get all previous coordinates to avoid super long lines of code
        double previousXCoordinate = Farm.getLandTilesTextures()
                                         .getChildren()
                                         .get(tileNumber)
                                         .getTranslateX(),

               previousYCoordinate = Farm.getLandTilesTextures()
                                         .getChildren().get(tileNumber)
                                         .getTranslateY(),

               previousZCoordinate = Farm.getLandTilesTextures()
                                         .getChildren()
                                         .get(tileNumber)
                                         .getTranslateZ();

        if (isWithered){
            // Update, load and set texture [Withered] 
            String[] fileNameModifier = this.getModelTextures()
                                            .toString()
                                            .split("grassP");

            this.setModelTextures(getClass().getResource("./Models/grassW" + fileNameModifier[1]));

            Farm.getLandTilesTextures()
                .getChildren()
                .set(tileNumber, SystemUtility.loadModel(this.getModelTextures()));
        }
        else if (isPlowed){
            // Update, load and set texture [Plowed] 
            String[] fileNameModifier = this.getModelTextures()
                                            .toString()
                                            .split("grass");

            this.setModelTextures(getClass().getResource("./Models/grassP" + fileNameModifier[1]));

            Farm.getLandTilesTextures()
                .getChildren()
                .set(tileNumber, SystemUtility.loadModel(this.getModelTextures()));
        }
        else if (!isPlowed){
            // Update, load and set texture [Unplowed] 
            String[] fileNameModifier = this.getModelTextures()
                                            .toString()
                                            .split("grassP");

            this.setModelTextures(getClass().getResource("./Models/grass" + fileNameModifier[1]));

            Farm.getLandTilesTextures()
                .getChildren()
                .set(tileNumber, SystemUtility.loadModel(this.getModelTextures()));
        }

        // Update new tile coordinates of new model texture loaded from previous tile texture model
        Farm.getLandTilesTextures()
            .getChildren()
            .get(tileNumber)
            .setTranslateX(previousXCoordinate);

        Farm.getLandTilesTextures()
            .getChildren()
            .get(tileNumber)
            .setTranslateY(previousYCoordinate);

        Farm.getLandTilesTextures()
            .getChildren()
            .get(tileNumber)
            .setTranslateZ(previousZCoordinate);
    }

    // Animations Method [To highlight selection and selection of Tile]  
    public static void selectTile(Node currentTile, Node previousTile){
        AnimationTimer timer = new AnimationTimer() {
            double speed = 1;
            @Override
            public void handle(long now) {
                /*
                    speed * speed * (3.0 - 2.0 * speed), is a math formula to simulate ease in and ease out animations
                    will move currentTile and previousTile's position by a certain distance increasing and decreasing
                    for every frame of execution
                */ 
                currentTile.setTranslateY(currentTile.getTranslateY() + speed * speed * (3.0 - 2.0 * speed));
                previousTile.setTranslateY(previousTile.getTranslateY() - speed * speed * (3.0 - 2.0 * speed));
                
                // if both currentTile and previousTile reaches their destination
                if (currentTile.getTranslateY() <= -200 && previousTile.getTranslateY() >= 0){
                    /*
                        Most case, the coordinates of currentTile and previousTile will overshoot their target
                        location by a little, as such during the last frame of execution we simply teleport
                        them to where they exactly should be, not noticable due to the small difference and
                        time frame
                    */
                    previousTile.setTranslateY(0);
                    currentTile.setTranslateY(-200);
                    // end animation
                    this.stop();
                }
                // increase speed to create momentum in animation
                speed += 0.02;
            }
            
        };
        // start animation
        timer.start();
    }
}
