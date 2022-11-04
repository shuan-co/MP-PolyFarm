package Assets.WorldBuilder.DaySystem;

import Assets.WorldBuilder.Farm;
import Assets.WorldBuilder.Tiles.Tile;

// Day System Class, contains methods that will govern actions after each start, end or next day 
public final class DaySystem {

    // New Day method will update all attributes of all tiles and seeds given a condition
    public static void newDay(){
        // Access all Tiles in the Tiles Array
        for (int currentTileIndex = 0; currentTileIndex < 50; currentTileIndex++){
            // Create currentTileSelected variable to prevent redundancy of code as we will use this multiple times
            Tile currentTileSelected = Farm.getLandTiles()[currentTileIndex];

            /*
                Check if tile has seed, seed harvest time is greater than or equal to 0
                Simply checks if seed and tile is still healthy for updating values for harvesting
            */
            if (currentTileSelected.getContainsSeed() && 
                currentTileSelected.getSeedPlanted().getHarvestTime() >= 0){
                
                // Update current seed's harvest time due to next day
                currentTileSelected.getSeedPlanted()
                                   .updateHarvestTime();

                // Check if seed is ready for harvest
                if (currentTileSelected.getSeedPlanted().getHarvestTime() == 0){
                    // Check if seed recieved necessary water before harvest day
                    if (!(currentTileSelected.getWaterLevel() >= 
                          currentTileSelected.getSeedPlanted().getWaterNeeds()[0])){
                          // Update tile and seed state [Withered]
                          currentTileSelected.updateIsWithered();
                          currentTileSelected.updateTileState();
                          currentTileSelected.getSeedPlanted()
                                             .updateHarvestProduce();
                    }
                    /* 
                        Generate Randomized Produces from possible bounds, 
                        if enough water was given to the plant during its growth period.
                        [Harvest Day]
                    */ 
                    else 
                        currentTileSelected.getSeedPlanted()
                                           .getHarvestProduce();
                }
                // Check if Crop was not harvested on harvest day [Crop Withers]
                if (currentTileSelected.getSeedPlanted().getHarvestTime() == -1 && 
                    !currentTileSelected.getIsWithered()){
                    // Update tile and seed state [Withered]
                    currentTileSelected.updateIsWithered();
                    currentTileSelected.updateTileState();
                    currentTileSelected.getSeedPlanted()
                                       .updateHarvestProduce();
                }
            }
        }
    }
}
