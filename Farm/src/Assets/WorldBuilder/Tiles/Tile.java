package Assets.WorldBuilder.Tiles;
import java.util.Random;

import Assets.Utilities.SystemUtility;
import Assets.WorldBuilder.Farm;
import Assets.WorldBuilder.Seeds.Seed;

import java.net.URL;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;

public final class Tile {
    private boolean isPlowed = false,
                    containsSeed = false,
                    isWithered = false;
    
    private int waterLevel = 0;
    
    private URL modelTextures;
    private int tileNumber;

    private Seed seedPlanted;

    // Static Variables
    private static int totalTileCount = 0;

    // Constructor
    public Tile(){
        Random rand = new Random();
        modelTextures = getClass().getResource("./Models/grass"+ rand.nextInt(1,11) +".obj");

        tileNumber = totalTileCount;
        totalTileCount++;
    }

    // Getters & Setters [Tile Attributes]
    public void updateWaterLevel(){
        waterLevel += 1;
    }
    public int getWaterLevel(){
        return waterLevel;
    }
    public void resetWaterLevel(){
        waterLevel = 0;
    }

    public void updateIsPlowed(){
        isPlowed = !isPlowed;
    }
    public boolean getIsPlowed(){
        return isPlowed;
    }

    public void updateContainsSeed(){
        containsSeed = !containsSeed;
    }
    public boolean getContainsSeed(){
        return containsSeed;
    }

    public void updateIsWithered(){
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

    // Tile States
    public void updateTileState(){
        //Texture Update
        double previousXCoordinate = Farm.getLandTilesTextures().getChildren().get(tileNumber).getTranslateX(),
        previousYCoordinate = Farm.getLandTilesTextures().getChildren().get(tileNumber).getTranslateY(),
        previousZCoordinate = Farm.getLandTilesTextures().getChildren().get(tileNumber).getTranslateZ();
        if (isWithered){
            String[] fileNameModifier = this.getModelTextures().toString().split("grassP");
            this.setModelTextures(getClass().getResource("./Models/grassW" + fileNameModifier[1]));
            Farm.getLandTilesTextures().getChildren().set(tileNumber, SystemUtility.loadModel(this.getModelTextures()));
        }
        else if (isPlowed){
            
            String[] fileNameModifier = this.getModelTextures().toString().split("grass");
            this.setModelTextures(getClass().getResource("./Models/grassP" + fileNameModifier[1]));
            Farm.getLandTilesTextures().getChildren().set(tileNumber, SystemUtility.loadModel(this.getModelTextures()));
        }
        else if (!isPlowed){
            String[] fileNameModifier = this.getModelTextures().toString().split("grassP");
            this.setModelTextures(getClass().getResource("./Models/grass" + fileNameModifier[1]));
            Farm.getLandTilesTextures().getChildren().set(tileNumber, SystemUtility.loadModel(this.getModelTextures()));
        }
        Farm.getLandTilesTextures().getChildren().get(tileNumber).setTranslateX(previousXCoordinate);
        Farm.getLandTilesTextures().getChildren().get(tileNumber).setTranslateY(previousYCoordinate);
        Farm.getLandTilesTextures().getChildren().get(tileNumber).setTranslateZ(previousZCoordinate);
    }

    // Animations  
    public static void selectTile(Node currentTile, Node previousTile){
        AnimationTimer timer = new AnimationTimer() {
            double speed = 1;
            @Override
            public void handle(long now) {
                currentTile.setTranslateY(currentTile.getTranslateY() + speed * speed * (3.0 - 2.0 * speed));
                previousTile.setTranslateY(previousTile.getTranslateY() - speed * speed * (3.0 - 2.0 * speed));
                if (currentTile.getTranslateY() <= -200 && previousTile.getTranslateY() >= 0){
                    previousTile.setTranslateY(0);
                    currentTile.setTranslateY(-200);
                    this.stop();
                }
                speed += 0.02;
            }
            
        };
        timer.start();
    }
}
