package Assets.Utilities;

import Assets.WorldBuilder.Farm;
import Assets.WorldBuilder.Tiles.Tile;
import javafx.scene.Group;

public final class GameUtility {
    public static Tile[] generateTiles(){
        Tile[] tileGroup = new Tile[50];
        for (int i = 0; i < tileGroup.length; i++)
            tileGroup[i] = new Tile();
        return tileGroup;
    }

    public static Group generateSeedTextures(){
        Group seedGroup = new Group();
        for (int i = 0; i < 50; i++)
            seedGroup.getChildren().add(new Group());
        return seedGroup;
    }


    public static Group loadTileGroup(){
        Group tileGroupTextures = new Group();
        Group currentLoadedTexture;
    
        int y = 0;
        int x = 0;
        for (int i = 0; i < Farm.getLandTiles().length; i++){
            currentLoadedTexture = SystemUtility.loadModel(Farm.getLandTiles()[i].getModelTextures());
            currentLoadedTexture.setTranslateX(x);
            currentLoadedTexture.setTranslateZ(y);

            tileGroupTextures.getChildren().add(currentLoadedTexture);
            x += 170;
            if ((i+1) % 5 == 0){
                x = 0;
                y += 170;
            }
        }
        tileGroupTextures.getChildren().get(0).setTranslateY(-200);
        return tileGroupTextures;
    }
}
