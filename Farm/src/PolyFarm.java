import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import Assets.Player.Player;
import Assets.Utilities.GameUtility;
import Assets.WorldBuilder.Farm;

public class PolyFarm extends Application{

    // Game Stage; Where main game interface is presented
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Check if current platform is 3D Supported
        boolean is3DSupported = Platform.isSupported(ConditionalFeature.SCENE3D);
        if (!is3DSupported){
            System.out.println("ERROR: JAVAFX 3D NOT SUPPORTED ON THIS PLATFORM");
            return;
        }

        // Test
        Farm.setLandTiles(GameUtility.generateTiles());
        Farm.setLandTilesTextures(GameUtility.loadTileGroup());
        Farm.setSeedsPlantedTextures(GameUtility.generateSeedTextures());

        Player player = new Player();
        int[] coordinates = {-2000,-1200, 800};
        player.createPlayerCamera(coordinates);

        // 2D UI
        AnchorPane globalRoot = new AnchorPane();
        globalRoot.getChildren().add(player.getInfoBar());
        Scene worldScene = new Scene(globalRoot, 1920, 1080, true);
        
        //3D UI
        Farm.initFarm();
        SubScene sub = new SubScene(Farm.getFarm(), 1920, 1080, true, SceneAntialiasing.BALANCED);
        globalRoot.getChildren().add(sub);
        
        player.updateInfoBar();

        // Primary Stage Layout / Application itself
        sub.setCamera(player.getPlayerCamera());
        primaryStage.setMaximized(true);
        primaryStage.setScene(worldScene);
        primaryStage.setTitle("PolyFarm");
        primaryStage.show();
        player.getKeyInput(worldScene);
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
