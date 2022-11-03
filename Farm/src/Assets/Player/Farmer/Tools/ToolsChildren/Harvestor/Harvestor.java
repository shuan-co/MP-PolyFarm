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
    public void applyAction(){
        if (!super.getCurrentTile().getContainsSeed())
            return;
        if (super.getCurrentTile().getSeedPlanted().getHarvestTime() == 0 && 
            !super.getCurrentTile().getIsWithered()){
            float harvestTotal = super.getCurrentTile().getSeedPlanted().getHarvestProduce() * 
                                (super.getCurrentTile().getSeedPlanted().getBasePrice().getValue() + 0);
            int waterLevel = 0;
            if (super.getCurrentTile().getWaterLevel() >= super.getCurrentTile().getSeedPlanted().getWaterNeeds()[1])
                waterLevel = super.getCurrentTile().getSeedPlanted().getWaterNeeds()[1];
            else
                waterLevel = super.getCurrentTile().getWaterLevel();

            float waterBonus = harvestTotal * 0.2f * (waterLevel - 1);
            float fertilizerBonus = harvestTotal * 0.5f * 0;
            float finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;

            super.getOwner().getCoins().updateValue(finalHarvestPrice);
            super.getOwner().getExperience().updateValue(super.getCurrentTile().getSeedPlanted().getExperience().getValue());
            
            // Reset Tile
            super.getCurrentTile().updateIsPlowed();
            super.getCurrentTile().resetWaterLevel();
            super.getCurrentTile().updateContainsSeed();
            super.getCurrentTile().updateTileState();
            Farm.getSeedsPlanted()[super.getOwner().getCurrentTileIndex()] = null;
            Farm.getSeedsPlantedTextures().getChildren().set(super.getOwner().getCurrentTileIndex(), new Group());
        }
    }
    
}
