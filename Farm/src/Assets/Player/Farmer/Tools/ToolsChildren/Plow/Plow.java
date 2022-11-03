package Assets.Player.Farmer.Tools.ToolsChildren.Plow;

import Assets.Player.Player;
import Assets.Player.Farmer.Tools.ToolsTemplate;
import Assets.WorldBuilder.Farm;


public class Plow extends ToolsTemplate{
    public Plow(Player owner) {
        super(owner);
    }

    @Override
    public void applyAction(int currentTileSelected){
        super.selectTile(Farm.getLandTiles()[currentTileSelected]);
        if (!super.getTileSelected().getIsPlowed()){
            super.getTileSelected().updateIsPlowed();
            super.getTileSelected().updateTileState();
            super.getOwner().getExperience().updateValue(0.5f);
        }
    }
}
