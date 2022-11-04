package Assets.Player.Farmer.Tools.ToolsChildren.WateringCan;

import Assets.Player.Player;
import Assets.Player.Farmer.Tools.ToolsTemplate;

// Child of ToolsTemplate, purpose to update water levels of a tile given a condition
public final class WateringCan extends ToolsTemplate{
    
    // Access parent constructor to set tool owner
    public WateringCan(Player owner) {
        super(owner);
    }

    // Overriden method of parent to do a different action when called, in this case update water level
    @Override
    public void applyAction(){
        // Check if the conditions to use the tool is met
        if (super.getCurrentTile().getIsPlowed() && 
            !super.getCurrentTile().getIsWithered() &&
            super.getCurrentTile().getContainsSeed()){

            // Update tile water level
            super.getCurrentTile()
                 .updateWaterLevel();
                 
            /* 
                As there is no consequence to using the watering tool, exploitations of experience gain might occur,
                therefore add condition, can only gain experience if water level is less than or equal to the amount of water needed by
                the seed
            */ 
            if (super.getCurrentTile().getWaterLevel() <= super.getSeed().getWaterNeeds()[1])
                super.getOwner()
                     .getExperience()
                     .updateValue(super.getExperience().getValue());
        }

    }
}
