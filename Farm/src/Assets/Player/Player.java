package Assets.Player;

import Assets.Player.Controller.Controller;
import Assets.Player.Farmer.Hotbar.InfoBar;
import Assets.Player.Farmer.Tools.ToolsTemplate;
import Assets.Player.Farmer.Tools.ToolsChildren.Harvestor.Harvestor;
import Assets.Player.Farmer.Tools.ToolsChildren.Plow.Plow;
import Assets.Player.Farmer.Tools.ToolsChildren.WateringCan.WateringCan;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public final class Player extends Controller {
    // Coins & Experience
    private Coins coins = new Coins(100.0f);
    private Experience exp = new Experience(0.0f);

    // Hotbar & InfoBar Selection
    private int currentHotBar = 0;
    private FXMLLoader infoBar = new FXMLLoader(getClass().getResource("./Farmer/Hotbar/infoBar.fxml"));

    // Tools
    private ToolsTemplate[] tools = {new Plow(this), new WateringCan(this), null, new Harvestor(this), null};

    // Tile Selection
    private int currentTileIndex = 0,
                previousTileIndex = 49;

    private Tile currentTileSelected = Farm.getLandTiles()[currentTileIndex],
                 previousTileSelected = Farm.getLandTiles()[previousTileIndex];

    private Node currentTileTexture = Farm.getLandTilesTextures().getChildren().get(currentTileIndex),
                 previousTileTexture = Farm.getLandTilesTextures().getChildren().get(previousTileIndex);

    
    // Key Input Delay
    private Delay delay = new Delay();

    // Plant Seeds Method
    public void plantSeed(){
        if (currentTileSelected.getIsPlowed() && !currentTileSelected.getContainsSeed() &&
            coins.getValue() >= new Turnip().getBasePrice().getValue()){
            double tileCoordinateX = currentTileTexture.getTranslateX();
            double tileCoordinateY = currentTileTexture.getTranslateY();
            double tileCoordinateZ = currentTileTexture.getTranslateZ();

            Farm.getSeedsPlanted()[currentTileIndex] = new Turnip();
            Farm.getSeedsPlanted()[currentTileIndex].setModelTextures(getClass().getResource("../WorldBuilder/Seeds/Seeds/Turnip/Models/turnip.obj"));
            Farm.getLandTiles()[currentTileIndex].setSeedPlanted(Farm.getSeedsPlanted()[currentTileIndex]);
            Farm.getLandTiles()[currentTileIndex].updateContainsSeed();

            Farm.getSeedsPlantedTextures().getChildren().set(currentTileIndex, SystemUtility.loadModel(Farm.getSeedsPlanted()[currentTileIndex].getModelTextures()));
            Farm.getSeedsPlantedTextures().getChildren().get(currentTileIndex).setTranslateX(tileCoordinateX);
            Farm.getSeedsPlantedTextures().getChildren().get(currentTileIndex).setTranslateY(tileCoordinateY-50);
            Farm.getSeedsPlantedTextures().getChildren().get(currentTileIndex).setTranslateZ(tileCoordinateZ);
            coins.updateValue(-Farm.getSeedsPlanted()[currentTileIndex].getSeedCost().getValue());
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


    public Parent getInfoBar() throws IOException{
        return infoBar.load();
    }

    public void updateInfoBar(){
        InfoBar infoBarController = (InfoBar) infoBar.getController();
        infoBarController.update(this);
    }

    // Update Tile Selection
    private void updateSelectedTile(){
        currentTileSelected = Farm.getLandTiles()[currentTileIndex];
        previousTileSelected = Farm.getLandTiles()[previousTileIndex];
        currentTileTexture = Farm.getLandTilesTextures().getChildren().get(currentTileIndex);
        previousTileTexture = Farm.getLandTilesTextures().getChildren().get(previousTileIndex);
    }

    //


    // Animations
    private void playAnimations(){
        Tile.selectTile(currentTileTexture, previousTileTexture);
        Seed.selectSeedPlanted(currentTileIndex, previousTileIndex);
    }

    // Event Methods [KeyBoard]
    @Override
    public void getKeyInput(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if (delay.isPaused()){
                    previousTileIndex = currentTileIndex;
                    switch(event.getCode()){
                        case W:
                            if (Arrays.binarySearch(new int[]{4,9,14,19,24,29,34,39,44,49}, currentTileIndex) > -1)
                                currentTileIndex -= 4;
                            else 
                                currentTileIndex++;
                            break;
                        case S:
                            if (Arrays.binarySearch(new int[]{0,5,10,15,20,25,30,35,40,45}, currentTileIndex) > -1)
                                currentTileIndex += 4;
                            else 
                                currentTileIndex--;
                            break;
                        case A: 
                            if (currentTileIndex >= 45 && currentTileIndex <= 49)
                                currentTileIndex -= 45;
                            else
                                currentTileIndex += 5;
                            break;
                        case D:
                            if (currentTileIndex >= 0 && currentTileIndex <= 4)
                                currentTileIndex += 45;
                            else
                                currentTileIndex -= 5;
                            break;
                        default:
                            break;
                    }
                    updateSelectedTile();
                    playAnimations();
                }
                switch(event.getCode()){
                    case SPACE:
                        if ((float) Farm.getLandTilesTextures().getChildren().get(currentTileIndex).getTranslateY() <= - 200){
                            if (currentHotBar == 2)
                                plantSeed();
                            else if (currentHotBar == 4)
                                DaySystem.newDay();
                            else
                                tools[currentHotBar].applyAction(currentTileIndex);
                        }
                        break;
                    case DIGIT1:
                        currentHotBar = 0;
                        break;
                    case DIGIT2:
                        currentHotBar = 1;
                        break;
                    case DIGIT3:
                        currentHotBar = 2;
                        break;
                    case DIGIT4:
                        currentHotBar = 3;
                        break;
                    case DIGIT5:
                        currentHotBar = 4;
                        break;
                    default:
                        break;
                }
                updateInfoBar();
            }
        }); 
    }
}
