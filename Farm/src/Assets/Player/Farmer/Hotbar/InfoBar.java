package Assets.Player.Farmer.Hotbar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Assets.Player.Player;
import Assets.WorldBuilder.Farm;
import Assets.WorldBuilder.Seeds.Seed;
import Assets.WorldBuilder.Tiles.Tile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class InfoBar implements Initializable{

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

    private static FXMLLoader loader = new FXMLLoader(InfoBar.class.getResource("infoBar.fxml"));

    

    public static FXMLLoader getInfoBar() throws IOException {
        return loader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void update(Player player){
        Tile currentTileSelected = Farm.getLandTiles()[player.getCurrentTileIndex()];
        Seed currentSeedSelected = Farm.getSeedsPlanted()[player.getCurrentTileIndex()];
        coins.setText(String.format("%.2f", player.getCoins().getValue()));
        exp.setText(String.format("%.2f", player.getExperience().getValue()));

        if (currentTileSelected.getIsWithered())
            state.setText("Withered");
        else if (currentTileSelected.getIsPlowed())
            state.setText("Plowed");
        else 
            state.setText("Unplowed");
        
        if (currentSeedSelected == null){
            seed.setText("None");
            harvest.setText("");
            seedEXP.setText("");
            waterNeeds.setText("");
            fertilizerNeeds.setText("");
            harvestProduce.setText("");
        }
        else {
            seed.setText(currentSeedSelected.getSeedType().name());

            if (currentSeedSelected.getHarvestTime() > 0)
                harvestProduce.setText(String.valueOf(currentSeedSelected.getProductsProduced()[0]) + "-" +
                                       String.valueOf(currentSeedSelected.getProductsProduced()[1]));

            if (currentSeedSelected.getHarvestTime() == -1 || 
                currentTileSelected.getIsWithered()){
                harvest.setText("Cannot Harvest");
                harvestProduce.setText(String.valueOf(currentSeedSelected.updateHarvestProduce()));

            }
            else if (currentSeedSelected.getHarvestTime() == 0){
                harvest.setText("Harvest Day");
                harvestProduce.setText(String.valueOf(currentSeedSelected.getHarvestProduce()));
            }
            else
                harvest.setText(String.valueOf(currentSeedSelected.getHarvestTime()) + " " + "Days Left");
            seedEXP.setText(String.valueOf(Farm.getSeedsPlanted()[player.getCurrentTileIndex()].getExperience().getValue()));
            
            waterNeeds.setText(String.valueOf(currentSeedSelected.getWaterNeeds()[0]) + "-" +
                               String.valueOf(currentSeedSelected.getWaterNeeds()[1]));
            fertilizerNeeds.setText(String.valueOf(currentSeedSelected.getFertilizerNeeds()[0]) + "-" + 
                                    String.valueOf(currentSeedSelected.getFertilizerNeeds()[1]));
        }

        waterLevel.setText(String.valueOf(currentTileSelected.getWaterLevel()));

    }

}
