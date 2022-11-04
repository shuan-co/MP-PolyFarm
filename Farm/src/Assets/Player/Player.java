package Assets.Player;

import Assets.Player.Controller.Controller;
import Assets.Player.Farmer.Tools.ToolsTemplate;
import Assets.Player.Farmer.Tools.ToolsChildren.Harvestor.Harvestor;
import Assets.Player.Farmer.Tools.ToolsChildren.Plow.Plow;
import Assets.Player.Farmer.Tools.ToolsChildren.WateringCan.WateringCan;
import Assets.Player.Farmer.UserInterface.UserInterface;
import Assets.Player.Items.Coins.Coins;
import Assets.Player.Items.Experience.Experience;
import Assets.Utilities.Delay;
import Assets.Utilities.SystemUtility;
import Assets.WorldBuilder.Farm;
import Assets.WorldBuilder.DaySystem.DaySystem;
import Assets.WorldBuilder.Seeds.Seed;
import Assets.WorldBuilder.Seeds.Seeds.Turnip.Turnip;
import Assets.WorldBuilder.Tiles.Tile;

import java.io.IOException;
import java.util.Arrays;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;


/*
    Child class of Controller, 
    class where key actions of player are recorded such as tile / seed selected,
    current hotBar selected and etc, along with attributes pertaining to player 
    such as coins and experiences.
 */ 
public final class Player extends Controller {
    // Attributes [Coins & Experience]
    private Coins coins = new Coins(100.0f);
    private Experience exp = new Experience(0.0f);

    // Attributes [User Interface]
    private int currentHotBar = 0;
    private UserInterface userInterface = new UserInterface(this);

    // Attributes [Tools], each index is another tool which contains a different apply action
    private ToolsTemplate[] tools = {new Plow(this), 
                                    new WateringCan(this), 
                                    null, 
                                    new Harvestor(this), 
                                    null};

    // Attributes [Tile Selection]
    private int currentTileIndex = 0,
                previousTileIndex = 49;

    private Tile currentTileSelected = Farm.getLandTiles()[currentTileIndex];

    private Node currentTileTexture = Farm.getLandTilesTextures()
                                          .getChildren()
                                          .get(currentTileIndex),
                                          
                 previousTileTexture = Farm.getLandTilesTextures()
                                           .getChildren()
                                           .get(previousTileIndex);
    
    private Group[] farmTextures = Farm.getFarmTextures();

    // Constructor, get parent constructor to assign camera coordinates, update GUI to default selected tile
    // IOException needed for FXMLLoader
    public Player(int[] coordinates) throws IOException {
        super(coordinates);
        userInterface.updateUserInterface();
    }

    // Updates current tile to contain a seed, creates a new seed and seed texture
    public void plantSeed(){
        // Variable to reduce redundancy later when assigning new seed coordinates
        Node currentSeedSelected;

        // Check if conditions to plant a seed are met
        if (currentTileSelected.getIsPlowed() && 
            !currentTileSelected.getContainsSeed() &&
            coins.getValue() >= new Turnip().getBasePrice().getValue()){
            
            // Get previous tile coordinates
            double tileCoordinateX = currentTileTexture.getTranslateX();
            double tileCoordinateY = currentTileTexture.getTranslateY();
            double tileCoordinateZ = currentTileTexture.getTranslateZ();
            
            // Create new seed & seed texture on current tile selected
            Farm.getSeedsPlanted()[currentTileIndex] = new Turnip();
            Farm.getSeedsPlanted()[currentTileIndex]
                .setModelTextures(getClass().getResource("../WorldBuilder/Seeds/Seeds/Turnip/Models/turnip.obj"));
            
            // Assign new seed to current tile selected & update attribute
            currentTileSelected.setSeedPlanted(Farm.getSeedsPlanted()[currentTileIndex]);
            currentTileSelected.updateContainsSeed();
            
            // Assign new seed texture to Group seedPlantedTextures
            Farm.getSeedsPlantedTextures()
                .getChildren()
                .set(currentTileIndex, SystemUtility.loadModel(currentTileSelected.getSeedPlanted()
                                                                                  .getModelTextures()));
            
            // Set seed coordinates relative to current tile selected
            currentSeedSelected = Farm.getSeedsPlantedTextures()
                                      .getChildren()
                                      .get(currentTileIndex);

            currentSeedSelected.setTranslateX(tileCoordinateX);
            currentSeedSelected.setTranslateY(tileCoordinateY-50);
            currentSeedSelected.setTranslateZ(tileCoordinateZ);
            
            // Update user coins for use of seed, essentially user bought the seed to be planted
            coins.updateValue(-Farm.getSeedsPlanted()[currentTileIndex]
                 .getSeedCost()
                 .getValue());
        }
    }

    // Getters & Setters
    public Coins getCoins(){
        return coins;
    }
    public Experience getExperience(){
        return exp;
    }
    public int getCurrentTileIndex(){
        return currentTileIndex;
    }
    public Tile getCurrentTile(){
        return currentTileSelected;
    }
    public Node getCurrentTileTexture(){
        return currentTileTexture;
    }
    public int getCurrentHotbar(){
        return currentHotBar;
    }
    public Group getUserInterface(){
        return userInterface.getUserInterface();
    }
    public Group[] getPlayerFarm(){
        return farmTextures;
    }

    // Update current Tile, Tile Texture & previous Tile texture selected
    private void updateSelectedTile(){
        currentTileSelected = Farm.getLandTiles()[currentTileIndex];

        currentTileTexture = Farm.getLandTilesTextures()
                                 .getChildren()
                                 .get(currentTileIndex);

        previousTileTexture = Farm.getLandTilesTextures()
                                  .getChildren()
                                  .get(previousTileIndex);
    }

    // Update currentTileIndex based on key Inputs
    private void moveUp(){
        /*
            Check if currentTileIndex can be found within the array, 
            array determines boundaries for which we loop back to the other side of the grid
         */ 
        if (Arrays.binarySearch(new int[]{4,9,14,19,24,29,34,39,44,49}, currentTileIndex) > -1)
            currentTileIndex -= 4;
        else 
            currentTileIndex++;
        // currentTileIndex updates based on conditions
    }
    private void moveDown(){
        /*
            Check if currentTileIndex can be found within the array, 
            array determines boundaries for which we loop back to the other side of the grid
         */ 
        if (Arrays.binarySearch(new int[]{0,5,10,15,20,25,30,35,40,45}, currentTileIndex) > -1)
            currentTileIndex += 4;
        else 
            currentTileIndex--;
        // currentTileIndex updates based on conditions
    }
    private void moveLeft(){
        // Check if currentTileIndex is between the range of boundaries given
        if (currentTileIndex >= 45 && currentTileIndex <= 49)
            currentTileIndex -= 45;
        else
            currentTileIndex += 5;
        // currentTileIndex updates based on conditions
    }
    private void moveRight(){
        // Check if currentTileIndex is between the range of boundaries given
        if (currentTileIndex >= 0 && currentTileIndex <= 4)
            currentTileIndex += 45;
        else
            currentTileIndex -= 5;
        // currentTileIndex updates based on conditions
    }


    // Animations, method that contains all animations to be played
    private void playAnimations(){
        Tile.selectTile(currentTileTexture, previousTileTexture);

        Seed.selectSeedPlanted(Farm.getSeedsPlantedTextures()
                                   .getChildren()
                                   .get(currentTileIndex), 

                               Farm.getSeedsPlantedTextures()
                                   .getChildren()
                                   .get(previousTileIndex));
    }

    // Method to detect events which are key inputs
    @Override
    public void getKeyInput(Scene scene) {
        // Read key inputs over current scene overlay
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            // event parameter used for getting KeyCode player pressed
            @Override
            public void handle(KeyEvent event) {
                /*
                    Check if a certain amount of seconds had passed
                    Explanation can be found in the Delay class
                */
                if (Delay.isPaused()){
                    // Get previous Tile Index
                    previousTileIndex = currentTileIndex;

                    // Check what key user had pressed and update currentTileIndex based on key press
                    switch(event.getCode()){
                        case W: moveUp(); break;
                        case S: moveDown(); break;
                        case A: moveLeft(); break;
                        case D: moveRight(); break;
                        default: break;
                    }
                    // Update selectedTile & play animations after key press
                    updateSelectedTile();
                    playAnimations();
                }
                /*
                    Check what key user had pressed and update current tool / action selected
                    or perform a certain action given the current tool / action selected
                */
                switch(event.getCode()){
                    case SPACE:
                        // Before executing action check if animation had ended first
                        if ((float) currentTileTexture.getTranslateY() <= - 200){
                            // Check what action to execute
                            if (currentHotBar == 2)
                                plantSeed();
                            else if (currentHotBar == 4)
                                DaySystem.newDay();
                            else
                                tools[currentHotBar].applyAction();
                        }
                        break;
                    // Update which tool / action the player wants to select
                    case DIGIT1: currentHotBar = 0; break;
                    case DIGIT2: currentHotBar = 1; break;
                    case DIGIT3: currentHotBar = 2; break;
                    case DIGIT4: currentHotBar = 3; break;
                    case DIGIT5: currentHotBar = 4; break;
                    default: break;
                }
                // After all key events update current GUI information
                userInterface.updateUserInterface();
            }
        }); 
    }
}
