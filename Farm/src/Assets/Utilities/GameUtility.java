package Assets.Utilities;

import Assets.WorldBuilder.Farm;
import Assets.WorldBuilder.Tiles.Tile;
import javafx.scene.Group;

// Static Utilty Class, which focuses on generation of seeds, tiles and etc.
public final class GameUtility {

    // Creates an array of Tiles object and returns it
    public static Tile[] generateTiles(){
        Tile[] tileGroup = new Tile[50];

        // Add new Tile to a main Tile Array
        for (int i = 0; i < tileGroup.length; i++)
            tileGroup[i] = new Tile();

        return tileGroup;
    }

    /*
        Creates a group which contains the corresponding seed texture for each corresponding seed and its tile
        seed textures added are empty Groups which will be instantiated later
    */ 
    public static Group generateSeedTextures(){
        Group seedGroup = new Group();

        // Add empty group to seed texture group
        for (int i = 0; i < 50; i++)
            seedGroup.getChildren()
                     .add(new Group());

        return seedGroup;
    }

    // Creates a group which contains corresponding tile textures for each tile and returns it.
    public static Group loadTileGroup(){
        
        // Add groups to a main group for which tile textures are stored in
        Group tileGroupTextures = new Group();
        Group currentLoadedTexture;
    
        int y = 0;
        int x = 0;
        for (int i = 0; i < Farm.getLandTiles().length; i++){
            // Load 3D .obj
            currentLoadedTexture = SystemUtility.loadModel(Farm.getLandTiles()[i]
                                                               .getModelTextures());

            // Update Coordinates of 3D .obj
            currentLoadedTexture.setTranslateX(x);
            currentLoadedTexture.setTranslateZ(y);

            // Add to group
            tileGroupTextures.getChildren()
                             .add(currentLoadedTexture);
            
            // Update Coordinates for each new tile texture in the group
            x += 170;

            // move coordinate for every 5 tiles to create another column
            if ((i+1) % 5 == 0){
                x = 0;
                y += 170;
            }
        }

        // Default selected tile texture
        tileGroupTextures.getChildren()
                         .get(0)
                         .setTranslateY(-200);

        return tileGroupTextures;
    }
}
