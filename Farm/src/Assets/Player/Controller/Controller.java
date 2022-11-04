package Assets.Player.Controller;

import Assets.Player.Controller.Camera.Camera;
import Assets.Player.Controller.Events.KeyEvents;
import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Rotate;

// Template class that manages camera perspective
public abstract class Controller implements Camera, KeyEvents{
    // Camera Attributes
    private PerspectiveCamera playerCamera; // 3D Scene Camera
    private int[] playerCoordinates = new int[3]; // [x,y,z] coordinates

    // Constructor to Initialize Camera Values of the Scene, default location, attributes and rotations
    public Controller(int[] playerCoordinates){
        // Save coordinates to local class attribute
        this.playerCoordinates = playerCoordinates;

        // new Perspective Camera for 3D View of scene
        playerCamera = new PerspectiveCamera(true); // true as camera movement will be done.
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

    // Camera Methods

    // Simply changes the camera perspective and coordinates of the player
    @Override
    public void updateCameraCoordinates(int[] playerCoordinates) {
        this.playerCoordinates = playerCoordinates;

        // Set Camera Coordinates
        playerCamera.setTranslateX(this.playerCoordinates[0]);
        playerCamera.setTranslateY(this.playerCoordinates[1]);
        playerCamera.setTranslateZ(this.playerCoordinates[2]);
    }

    // Getters & Setters
    @Override
    public PerspectiveCamera getPlayerCamera() {
        return playerCamera;
    }
    public int[] getPlayerCoordinates(){
        return playerCoordinates;
    }


}
