package Assets.Player.Controller.Camera;

import javafx.scene.PerspectiveCamera;

// Camera method/s blueprints
public interface Camera {
    public void updateCameraCoordinates(int[] playerCoordinates);
    public PerspectiveCamera getPlayerCamera(); 
}
