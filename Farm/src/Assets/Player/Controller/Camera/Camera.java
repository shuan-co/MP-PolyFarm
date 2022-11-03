package Assets.Player.Controller.Camera;

import javafx.scene.PerspectiveCamera;

public interface Camera {
    public void updateCameraCoordinates(int[] playerCoordinates);
    public void createPlayerCamera(int[] playerCoordinates);
    public PerspectiveCamera getPlayerCamera(); 
}
