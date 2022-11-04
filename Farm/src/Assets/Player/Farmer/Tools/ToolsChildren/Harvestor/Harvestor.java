package Assets.Player.Farmer.Tools.ToolsChildren.Harvestor;

import Assets.Player.Player;
import Assets.Player.Farmer.Tools.ToolsTemplate;
import Assets.WorldBuilder.Farm;
import javafx.scene.Group;

// Child of ToolsTemplate, purpose is to harvest the produce of the seed from the tile given a condition
public final class Harvestor extends ToolsTemplate{

    // Access parent constructor to set "tool" owner
    public Harvestor(Player owner) {
        super(owner);
    }

    /*
        Overriden method of parent to do a different action when called, 
        in this case harvest the seed produce from the tile given a condition
    */ 
    @Override
    public void applyAction(){
        // Check if tile contains seed, otherwise ignore all lines of code after; prevent null calls
        if (!super.getCurrentTile().getContainsSeed())
            return;
        // Check if seed can be harvest
        if (super.getCurrentTile().getSeedPlanted().getHarvestTime() == 0 && 
            !super.getCurrentTile().getIsWithered()){

            // Variables for computation of coins earned
            int waterLevel = 0;
            float waterBonus,
                  fertilizerBonus,
                  finalHarvestPrice,
                  harvestTotal = super.getCurrentTile()
                                      .getSeedPlanted()
                                      .getHarvestProduce() * 
                                (super.getCurrentTile()
                                      .getSeedPlanted()
                                      .getBasePrice()
                                      .getValue() + 0);
            /*
                Check the amount of waterLevel to be used in computation for the waterBonus,
                if it exceeds the bound of the water needs of the plant use the max bound, if not
                use current waterLevel of the tile
             */
            if (super.getCurrentTile().getWaterLevel() >= 
                super.getCurrentTile().getSeedPlanted().getWaterNeeds()[1])
                waterLevel = super.getCurrentTile()
                                  .getSeedPlanted()
                                  .getWaterNeeds()[1];
            else
                waterLevel = super.getCurrentTile()
                                  .getWaterLevel();
            
            // Compute for coins earned
            waterBonus = harvestTotal * 0.2f * (waterLevel - 1);
            fertilizerBonus = harvestTotal * 0.5f * 0;
            finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;
            

            // Update user coins & experience
            super.getOwner()
                 .getCoins()
                 .updateValue(finalHarvestPrice);

            super.getOwner()
                 .getExperience()
                 .updateValue(super.getCurrentTile()
                                   .getSeedPlanted()
                                   .getExperience()
                                   .getValue());
            
            // Reset Tile Attributes and Textures, Remove Seed Planted and set to null
            super.getCurrentTile()
                 .updateIsPlowed();

            super.getCurrentTile()
                 .resetWaterLevel();

            super.getCurrentTile()
                 .updateContainsSeed();

            super.getCurrentTile()
                 .updateTileState();

            Farm.getSeedsPlanted()[super.getOwner()
                                        .getCurrentTileIndex()] = null;

            Farm.getSeedsPlantedTextures()
                .getChildren()
                .set(super.getOwner()
                          .getCurrentTileIndex(), new Group());
        }
    }
    
}
