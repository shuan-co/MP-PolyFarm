package Assets.Player.Farmer.Tools.ToolsChildren.Plow;

import Assets.Player.Player;
import Assets.Player.Farmer.Tools.ToolsTemplate;


public class Plow extends ToolsTemplate{
    public Plow(Player owner) {
        super(owner);
    }

    @Override
    public void applyAction(){
        if (!super.getCurrentTile().getIsPlowed()){
            super.getCurrentTile().updateIsPlowed();
            super.getCurrentTile().updateTileState();
            super.getOwner().getExperience().updateValue(super.getExperience().getValue());
        }
    }
}
