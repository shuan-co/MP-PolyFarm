package Assets.Player.Farmer.Tools;

import Assets.Player.Player;
import Assets.Player.Items.Coins.Coins;
import Assets.Player.Items.Experience.Experience;
import Assets.WorldBuilder.Tiles.Tile;

public abstract class ToolsTemplate {
    private Player owner;
    private Experience experienceGain = new Experience(0.5f);
    private Coins costOfUsage = new Coins(0);
    private Tile currentTileSelected;

    public ToolsTemplate(Player owner){
        this.owner = owner;
    }

    // Getters & Setters
    public void selectTile(Tile currentTileSelected){
        this.currentTileSelected = currentTileSelected;
    }
    public Tile getTileSelected(){
        return currentTileSelected;
    }
    public Player getOwner(){
        return owner;
    }


    public void applyAction(int currentTileSelected){

    }
}
