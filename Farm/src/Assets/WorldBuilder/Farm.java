package Assets.WorldBuilder;

import Assets.WorldBuilder.Seeds.Seed;
import Assets.WorldBuilder.Tiles.Tile;
import javafx.scene.Group;

public final class Farm {
    private static Tile[] landTiles = new Tile[50];
    private static Group landTilesTextures = new Group();

    private static Seed[] seedsPlanted = new Seed[50];
    private static Group seedsPlantedTextures = new Group();

    private static Group farmGroup = new Group();

    public static void initFarm(){
        farmGroup.getChildren().addAll(landTilesTextures, seedsPlantedTextures);
    }

    // Getters and Setters
    public static Tile[] getLandTiles(){
        return landTiles;
    }
    public static void setLandTiles(Tile[] landTiles){
        Farm.landTiles = landTiles;
    }

    public static Group getLandTilesTextures(){
        return landTilesTextures;
    }
    public static void setLandTilesTextures(Group landTilesTextures){
        Farm.landTilesTextures = landTilesTextures;
    }

    public static Seed[] getSeedsPlanted(){
        return seedsPlanted;
    }
    public static void setSeedsPlanted(Seed[] seedsPlanted){
        Farm.seedsPlanted = seedsPlanted;
    }

    public static Group getSeedsPlantedTextures(){
        return seedsPlantedTextures;
    }
    public static void setSeedsPlantedTextures(Group seedsPlantedTextures){
        Farm.seedsPlantedTextures = seedsPlantedTextures;
    }

    public static Group getFarm(){
        return farmGroup;
    }

}
