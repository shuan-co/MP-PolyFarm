package Assets.WorldBuilder.DaySystem;

import Assets.WorldBuilder.Farm;
import Assets.WorldBuilder.Tiles.Tile;

public final class DaySystem {
    public static void newDay(){
        for (int currentTileIndex = 0; currentTileIndex < 50; currentTileIndex++){
            Tile currentTileSelected = Farm.getLandTiles()[currentTileIndex];
            // Reduce Harvest Time
            if (currentTileSelected.getContainsSeed() && currentTileSelected.getSeedPlanted().getHarvestTime() >= 0){
                Farm.getLandTiles()[currentTileIndex].getSeedPlanted().updateHarvestTime();
                // Check if Crop had enough water and fertilizer for harvesting
                if (currentTileSelected.getSeedPlanted().getHarvestTime() == 0){
                    if (!(currentTileSelected.getWaterLevel() >= currentTileSelected.getSeedPlanted().getWaterNeeds()[0])){
                          currentTileSelected.updateIsWithered();
                          currentTileSelected.updateTileState();
                          currentTileSelected.getSeedPlanted().updateHarvestProduce();
                    }
                    else {
                        // Generate Randomized Produces from bounds, if enough water was given to the plant during its growth period
                        currentTileSelected.getSeedPlanted().getHarvestProduce();
                    }
                }
                // Check if Crop was not harvested on harvest day [Crop Withers]
                if (currentTileSelected.getSeedPlanted().getHarvestTime() == -1 && !currentTileSelected.getIsWithered()){
                    currentTileSelected.updateIsWithered();
                    currentTileSelected.updateTileState();
                    currentTileSelected.getSeedPlanted().updateHarvestProduce();
                }
            }
            if (Farm.getLandTiles()[currentTileIndex].getIsPlowed()){

            }
        }
    }
}
