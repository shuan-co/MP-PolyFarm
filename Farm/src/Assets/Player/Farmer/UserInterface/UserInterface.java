package Assets.Player.Farmer.UserInterface;

import java.io.IOException;

import Assets.Player.Player;
import Assets.Player.Farmer.UserInterface.Hotbar.Hotbar;
import Assets.Player.Farmer.UserInterface.Hotbar.InfoBar;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

// Compilation of all User Interface type classes
public final class UserInterface {

    // Attributes [GUI Classes]
    private Hotbar hotBar = new Hotbar();
    private InfoBar infoBar = new InfoBar();

    // [FXML Reader] Using Scene Builder to create protype GUI
    private FXMLLoader infoBarLoader = new FXMLLoader(getClass().getResource("./Hotbar/infoBar.fxml"));

    // Group where all GUI will be added to added to the 2D Layer
    private Group userInterface = new Group();

    // Set Owner of the User Interface
    private Player owner;
    
    // Constructor, initialize nodes [GUI] to be added to group, assign owner of GUI
    // IOException handling required for FXMLLLoader
    public UserInterface(Player owner) throws IOException{
        userInterface.getChildren().add(hotBar.getHotbar());
        userInterface.getChildren().add(infoBarLoader.load());
        this.owner = owner;
    }

    // Getters & Setters
    public Hotbar getHotBar() {
        return hotBar;
    }
    public InfoBar getInfoBar() {
        return infoBar;
    }
    public Group getUserInterface() {
        return userInterface;
    }

    // Update all elements of each User Interface class
    public void updateUserInterface(){
        InfoBar infoBarController = (InfoBar) infoBarLoader.getController();
        infoBarController.update(owner);
        hotBar.updateSelection(owner.getCurrentHotbar());
    }
    
}
