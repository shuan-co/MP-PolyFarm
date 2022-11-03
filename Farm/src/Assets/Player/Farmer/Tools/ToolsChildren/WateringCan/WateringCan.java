package Assets.Player.Farmer.Tools.ToolsChildren.WateringCan;

import Assets.Player.Player;
import Assets.Player.Farmer.Tools.ToolsTemplate;
import Assets.WorldBuilder.Farm;

public class WateringCan extends ToolsTemplate{
    public WateringCan(Player owner) {
        super(owner);
    }

    @Override
    public void applyAction(int currentTileSelected){
        if (Farm.getLandTiles()[currentTileSelected].getIsPlowed() && 
            !Farm.getLandTiles()[currentTileSelected].getIsWithered() &&
            Farm.getLandTiles()[currentTileSelected].getContainsSeed()){
            Farm.getLandTiles()[currentTileSelected].updateWaterLevel();
            if (Farm.getLandTiles()[currentTileSelected].getWaterLevel() <= Farm.getSeedsPlanted()[currentTileSelected].getWaterNeeds()[1])
                super.getOwner().getExperience().updateValue(0.5f);
        }

    }
}
