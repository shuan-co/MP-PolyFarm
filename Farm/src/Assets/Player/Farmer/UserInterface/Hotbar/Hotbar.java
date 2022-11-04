package Assets.Player.Farmer.UserInterface.Hotbar;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

// HotBar User Interface Class, similar to Minecraft, stardew valley and terraria's tool selection interface 
public final class Hotbar {

    // Attributes for representation of GUI
    private Group hotBar = new Group(); // Group to store each Rectangle
    private Rectangle rectangle = new Rectangle(),
                      oneRectangle = new Rectangle(),
                      twoRectangle = new Rectangle(),
                      seed = new Rectangle(),
                      harvest = new Rectangle(),
                      nextDay = new Rectangle();

    // Constructor, initialize all attributes of the GUI
    public Hotbar() {

        // Position GUI in 2D Coordinate Plane
        hotBar.setTranslateX(550);
        hotBar.setTranslateY(-50);

        // Main Box Attributes
        rectangle.setX(150);
        rectangle.setY(75);
        rectangle.setWidth(507);
        rectangle.setHeight(75);
        rectangle.setFill(Color.WHITESMOKE);
        rectangle.setStroke(Color.DARKSLATEGRAY);
        rectangle.setStrokeWidth(3);
        hotBar.getChildren().add(rectangle);

        // Sub Boxes Attributes
        setDimensions(oneRectangle, 152, "./Images/plow.png");
        setDimensions(twoRectangle, 253, "./Images/wateringCan.png");;
        setDimensions(seed, 354, "./Images/seed.png");
        setDimensions(harvest, 455, "./Images/harvestor.png");
        setDimensions(nextDay, 556, "./Images/nextDay.png");
    }

    // Getters & Setters
    public Group getHotbar(){
        return hotBar;
    }

    // Update Sub Box attributes, primarily x coordinate and Image and current rectangle to group
    private void setDimensions(Rectangle currentBox, int coordinate, String urlImage){
        currentBox.setX(coordinate);
        currentBox.setY(77);
        currentBox.setWidth(99);
        currentBox.setHeight(71);
        currentBox.setFill(Color.LIGHTGRAY);
        currentBox.setStroke(Color.GRAY);
        currentBox.setStrokeWidth(2);
        currentBox.setFill(new ImagePattern(new Image(getClass().getResource(urlImage).toExternalForm())));
        hotBar.getChildren().add(currentBox);
    }

    // Reset Selection GUI, default line Stroke implies non selected
    private void resetUI(){
        oneRectangle.setStrokeWidth(2);
        twoRectangle.setStrokeWidth(2);
        seed.setStrokeWidth(2);
        harvest.setStrokeWidth(2);
        nextDay.setStrokeWidth(2);
    }

    // Determine which rectangle is selected, increased stroke implies selected
    public void updateSelection(int currentHotBar){
        
        // Reset Strokes
        resetUI();

        // Check which rectangle is selected and update stroke
        switch(currentHotBar){
            case 0: oneRectangle.setStrokeWidth(8); break;
            case 1: twoRectangle.setStrokeWidth(8); break;
            case 2: seed.setStrokeWidth(8); break;
            case 3: harvest.setStrokeWidth(8); break;
            case 4: nextDay.setStrokeWidth(8); break;
            default: break;
        }
    }
}
