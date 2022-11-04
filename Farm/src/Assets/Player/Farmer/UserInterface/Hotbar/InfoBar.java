package Assets.Player.Farmer.UserInterface.Hotbar;

import java.io.IOException;

import Assets.Player.Player;
import Assets.WorldBuilder.Farm;
import Assets.WorldBuilder.Seeds.Seed;
import Assets.WorldBuilder.Tiles.Tile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;

// InfoBar User Interface Class, injects / loads FXML file to present GUI, Scene Builder was used
public final class InfoBar {

    //FXML attributes and id
    @FXML
    public Text coins;
    @FXML
    private Text exp;
    @FXML
    private Text state;
    @FXML
    private Text seed;
    @FXML
    private Text waterLevel;
    @FXML
    private Text harvest;
    @FXML
    private Text seedEXP;
    @FXML
    private Text waterNeeds;
    @FXML
    private Text fertilizerNeeds;
    @FXML
    private Text harvestProduce;

    // FXML Loader
    private FXMLLoader loader = new FXMLLoader(InfoBar.class
                                                      .getResource("./infoBar.fxml"));

    
    // Get FXML Loader
    // IOException handling needed for FXMLLoader
    public FXMLLoader getInfoBar() throws IOException {
        return loader;
    }

    // Update GUI information presented
    public void update(Player player){

        // Assign Current Tile & Seed to a variable to reduce redundancy due to repetead usage
        // Current Tile & Seed atttributes will be used for the information to be presented
        Tile currentTileSelected = player.getCurrentTile();
        Seed currentSeedSelected = player.getCurrentTile()
                                         .getSeedPlanted();

        // Update Current Information on GUI based on Tile & Seed Attributes
        coins.setText(String.format("%.2f", player.getCoins()
                                                         .getValue()));

        exp.setText(String.format("%.2f", player.getExperience()
                                                        .getValue()));

        // Update water level of current tile selected
        waterLevel.setText(String.valueOf(currentTileSelected.getWaterLevel()));

        // Check what state the Tile is in
        if (currentTileSelected.getIsWithered())
            state.setText("Withered");
        else if (currentTileSelected.getIsPlowed())
            state.setText("Plowed");
        else 
            state.setText("Unplowed");
        
        // Check if currentTile does not contains a Seed
        if (currentSeedSelected == null){
            // Default everything as none or empty
            seed.setText("None");
            harvest.setText("");
            seedEXP.setText("");
            waterNeeds.setText("");
            fertilizerNeeds.setText("");
            harvestProduce.setText("");
        }
        else {
            // Update Seed Name
            seed.setText(currentSeedSelected.getSeedType().name());

            // Update possible Seed experience gain
            seedEXP.setText(String.valueOf(currentSeedSelected.getExperience()
                                                              .getValue()));
            
            // Update water needs range
            waterNeeds.setText(String.valueOf(currentSeedSelected.getWaterNeeds()[0]) + "-" +
                               String.valueOf(currentSeedSelected.getWaterNeeds()[1]));
            
            // Update fertilizer needs range
            fertilizerNeeds.setText(String.valueOf(currentSeedSelected.getFertilizerNeeds()[0]) + "-" + 
                                    String.valueOf(currentSeedSelected.getFertilizerNeeds()[1]));

            // Check if seed cannot be harvested yet, then show possible seed amount produces
            if (currentSeedSelected.getHarvestTime() > 0)
                harvestProduce.setText(String.valueOf(currentSeedSelected.getProductsProduced()[0]) + "-" +
                                       String.valueOf(currentSeedSelected.getProductsProduced()[1]));

            // Check seed harvest state, if can harvest, withered and remaining days till harvest
            if (currentSeedSelected.getHarvestTime() == -1 || 
                currentTileSelected.getIsWithered()){
                // Update Seed Cannot be harvested due to date of harvesting had passed or tile & seed is withered
                harvest.setText("Cannot Harvest");
                harvestProduce.setText(String.valueOf(currentSeedSelected.updateHarvestProduce()));

            }
            else if (currentSeedSelected.getHarvestTime() == 0){
                // Update Seed can be harvested
                harvest.setText("Harvest Day");
                harvestProduce.setText(String.valueOf(currentSeedSelected.getHarvestProduce()));
            }
            else // Update remaining days before harvest day
                harvest.setText(String.valueOf(currentSeedSelected.getHarvestTime()) + " " + "Days Left");
        }

    }

}
