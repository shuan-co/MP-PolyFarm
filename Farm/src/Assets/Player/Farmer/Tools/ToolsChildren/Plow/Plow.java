package Assets.Player.Farmer.Tools.ToolsChildren.Plow;

import Assets.Player.Player;
import Assets.Player.Farmer.Tools.ToolsTemplate;

// Child of ToolsTemplate, purpose to update the state of a tile to plowed under a certain condition
public final class Plow extends ToolsTemplate{

    // Access parent constructor to set tool owner
    public Plow(Player owner) {
        super(owner);
    }

    // Overriden method of parent to do a different action when called, in this case update tile state to plowed
    @Override
    public void applyAction(){
        // Check if the conditions to use the tool is met
        if (!super.getCurrentTile().getIsPlowed() &&
            !super.getCurrentTile().getContainsSeed() &&
            !super.getCurrentTile().getIsWithered()){

            // Update tile texture and attribute to plowed
            super.getCurrentTile()
                 .updateIsPlowed();

            super.getCurrentTile()
                 .updateTileState();
            
            // Update user experience for using the tool
            super.getOwner()
                 .getExperience()
                 .updateValue(super.getExperience().getValue());
        }
    }
}
