package Assets.Player.Farmer.Tools;

import Assets.Player.Player;
import Assets.Player.Items.Experience.Experience;
import Assets.WorldBuilder.Seeds.Seed;
import Assets.WorldBuilder.Tiles.Tile;
import javafx.scene.Node;

public abstract class ToolsTemplate {
    private Player owner;
    private Experience experienceGain = new Experience(0.5f);
    // private Coins costOfUsage = new Coins(0);

    public ToolsTemplate(Player owner){
        this.owner = owner;
    }

    // Getters & Setters
    public Tile getCurrentTile(){
        return owner.getCurrentTile();
    }
    public Node getTileTexture(){
        return owner.getCurrentTileTexture();
    }
    public Seed getSeed(){
        return owner.getCurrentTile().getSeedPlanted();
    }
    public Player getOwner(){
        return owner;
    }
    public Experience getExperience(){
        return experienceGain;
    }


    public void applyAction(){

    }
}
