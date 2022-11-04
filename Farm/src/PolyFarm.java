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

// Main Class, where all screen presentation and main game loop occurs
public final class PolyFarm extends Application{

    // Game Stage; Where main game interface and loop occurs
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Check if current platform is 3D Supported
        boolean is3DSupported = Platform.isSupported(ConditionalFeature.SCENE3D);
        if (!is3DSupported){
            System.out.println("ERROR: JAVAFX 3D NOT SUPPORTED ON THIS PLATFORM");
            return;
        }

        // Initialize Farm
        Farm.setLandTiles(GameUtility.generateTiles());
        Farm.setLandTilesTextures(GameUtility.loadTileGroup());
        Farm.setSeedsPlantedTextures(GameUtility.generateSeedTextures());

        // Create new Player
        Player player = new Player(new int[]{-2000,-1200, 800});

        // 2D GUI
        AnchorPane globalRoot = new AnchorPane();
        globalRoot.getChildren().add(player.getUserInterface());
        Scene worldScene = new Scene(globalRoot, 1920, 1080, true);
        
        //3D GUI
        Farm.initFarm();
        SubScene sub = new SubScene(Farm.getFarm(), 1920, 1080, true, SceneAntialiasing.BALANCED);
        globalRoot.getChildren().add(sub);

        // Primary Stage Layout / Application itself
        sub.setCamera(player.getPlayerCamera());
        primaryStage.setMaximized(true);
        primaryStage.setScene(worldScene);
        primaryStage.setTitle("PolyFarm");
        primaryStage.show();

        // Get user input
        player.getKeyInput(worldScene);
    }

    // Execute 3D and 2D GUI and execute main game loop
    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
