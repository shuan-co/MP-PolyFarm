package Assets.Player.Farmer.Tools.ToolsChildren.Harvestor;

import Assets.Player.Player;
import Assets.Player.Farmer.Tools.ToolsTemplate;
import Assets.WorldBuilder.Farm;
import javafx.scene.Group;

public class Harvestor extends ToolsTemplate{

    public Harvestor(Player owner) {
        super(owner);
    }

    @Override
    public void applyAction(int currentTileIndex){
        if (!Farm.getLandTiles()[currentTileIndex].getContainsSeed())
            return;
        if (Farm.getLandTiles()[currentTileIndex].getSeedPlanted().getHarvestTime() == 0 && 
            !Farm.getLandTiles()[currentTileIndex].getIsWithered()){
            float harvestTotal = Farm.getLandTiles()[currentTileIndex].getSeedPlanted().getHarvestProduce() * 
                                (Farm.getLandTiles()[currentTileIndex].getSeedPlanted().getBasePrice().getValue() +
                                0);
            int waterLevel = 0;
            if (Farm.getLandTiles()[currentTileIndex].getWaterLevel() >= Farm.getLandTiles()[currentTileIndex].getSeedPlanted().getWaterNeeds()[1])
                waterLevel = Farm.getLandTiles()[currentTileIndex].getSeedPlanted().getWaterNeeds()[1];
            else
                waterLevel = Farm.getLandTiles()[currentTileIndex].getWaterLevel();

            float waterBonus = harvestTotal * 0.2f * (waterLevel - 1);
            float fertilizerBonus = harvestTotal * 0.5f * 0;
            float finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;

            super.getOwner().getCoins().updateValue(finalHarvestPrice);
            super.getOwner().getExperience().updateValue(Farm.getLandTiles()[currentTileIndex].getSeedPlanted().getExperience().getValue());
            
            // Reset Tile
            Farm.getLandTiles()[currentTileIndex].updateIsPlowed();
            Farm.getLandTiles()[currentTileIndex].resetWaterLevel();
            Farm.getLandTiles()[currentTileIndex].updateContainsSeed();
            Farm.getLandTiles()[currentTileIndex].updateTileState();
            Farm.getSeedsPlanted()[currentTileIndex] = null;
            Farm.getSeedsPlantedTextures().getChildren().set(currentTileIndex, new Group());
        }
    }
    
}
