package Assets.Player.Farmer.Tools.ToolsChildren.WateringCan;

import Assets.Player.Player;
import Assets.Player.Farmer.Tools.ToolsTemplate;

public class WateringCan extends ToolsTemplate{
    public WateringCan(Player owner) {
        super(owner);
    }

    @Override
    public void applyAction(){
        if (super.getCurrentTile().getIsPlowed() && 
            !super.getCurrentTile().getIsWithered() &&
            super.getCurrentTile().getContainsSeed()){
            super.getCurrentTile().updateWaterLevel();
            if (super.getCurrentTile().getWaterLevel() <= super.getSeed().getWaterNeeds()[1])
                super.getOwner().getExperience().updateValue(super.getExperience().getValue());
        }

    }
}
