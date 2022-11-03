package Assets.Player.Controller;

import Assets.Player.Controller.Camera.Camera;
import Assets.Player.Controller.Events.KeyEvents;
import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Rotate;

public abstract class Controller implements Camera, KeyEvents{
    // Camera Attributes
    private PerspectiveCamera playerCamera;
    private int[] playerCoordinates = new int[3];

    // Camera Methods
    @Override
    public void createPlayerCamera(int[] playerCoordinates) {
        this.playerCoordinates = playerCoordinates;

        playerCamera = new PerspectiveCamera(true);
        playerCamera.setNearClip(1); // Object Collision
        playerCamera.setFarClip(10000); // Render Distance

        // Set Camera Coordinates
        playerCamera.setTranslateX(this.playerCoordinates[0]);
        playerCamera.setTranslateY(this.playerCoordinates[1]);
        playerCamera.setTranslateZ(this.playerCoordinates[2]);

        // Set Camera Rotation [Bird's Eye View]
        Rotate rotate = new Rotate();
        rotate.setAxis(Rotate.X_AXIS);
        rotate.setAngle(-20);
        playerCamera.setRotationAxis(Rotate.Y_AXIS);
        playerCamera.setRotate(90);
        playerCamera.getTransforms().addAll(rotate);
    }

    @Override
    public void updateCameraCoordinates(int[] playerCoordinates) {
        this.playerCoordinates = playerCoordinates;

        // Set Camera Coordinates
        playerCamera.setTranslateX(this.playerCoordinates[0]);
        playerCamera.setTranslateY(this.playerCoordinates[1]);
        playerCamera.setTranslateZ(this.playerCoordinates[2]);
    }

    @Override
    public PerspectiveCamera getPlayerCamera() {
        return playerCamera;
    }

    public int[] getPlayerCoordinates(){
        return playerCoordinates;
    }


}
