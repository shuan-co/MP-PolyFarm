package Assets.Player.Farmer.Tools;

import Assets.Player.Player;
import Assets.Player.Items.Experience.Experience;
import Assets.WorldBuilder.Seeds.Seed;
import Assets.WorldBuilder.Tiles.Tile;
import javafx.scene.Node;

// Class template for all tools, default attributes and methods all tools must have
// Mainly used to classify each tools as a datatype ToolsTemplate to assign them to a single array
public abstract class ToolsTemplate {
    // Attributes
    private Player owner; // Set the owner or user of tool
    private Experience experienceGain = new Experience(0.5f); // Default Experience given when used
    // private Coins costOfUsage = new Coins(0); // Default cost of tools for usage

    // Constructor, sets the owner of the tool
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
        return owner.getCurrentTile()
                    .getSeedPlanted();
    }
    public Player getOwner(){
        return owner;
    }
    public Experience getExperience(){
        return experienceGain;
    }

    /*
        Simply a method that will be overriden, usage is to generalize i.e. an array of classes that inherited ToolsTemplate
        will be able to perform applyAction when accessed:
        ToolsTemplate[] tools = {new tool1};
        tool1.applyaction();
    */ 
    public void applyAction(){
        // EMPTY
    }
}
