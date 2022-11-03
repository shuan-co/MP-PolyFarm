package Assets.Player.Farmer.Hotbar;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Hotbar {
    private Group hotBar = new Group();
    private Rectangle rectangle,
                      onerectangle,
                      tworectangle,
                      seed,
                      harvest,
                      nextDay;

    public Hotbar() {
        hotBar.setTranslateX(550);
        hotBar.setTranslateY(-50);
        rectangle = new Rectangle();
        rectangle.setX(150);
        rectangle.setY(75);
        rectangle.setWidth(507);
        rectangle.setHeight(75);
        rectangle.setFill(Color.WHITESMOKE);
        rectangle.setStroke(Color.DARKSLATEGRAY);
        rectangle.setStrokeWidth(3);

        onerectangle = new Rectangle();
        onerectangle.setX(152);
        onerectangle.setY(77);
        onerectangle.setWidth(99);
        onerectangle.setHeight(71);
        onerectangle.setFill(Color.LIGHTGRAY);
        onerectangle.setStroke(Color.GRAY);
        onerectangle.setStrokeWidth(2);
        onerectangle.setFill(new ImagePattern(new Image(getClass().getResource("./Images/plow.png").toExternalForm())));

        tworectangle = new Rectangle();
        tworectangle.setX(253);
        tworectangle.setY(77);
        tworectangle.setWidth(99);
        tworectangle.setHeight(71);
        tworectangle.setFill(Color.LIGHTGRAY);
        tworectangle.setStroke(Color.GRAY);
        tworectangle.setStrokeWidth(2);
        tworectangle.setFill(new ImagePattern(new Image(getClass().getResource("./Images/wateringCan.png").toExternalForm())));

        seed = new Rectangle();
        seed.setX(354);
        seed.setY(77);
        seed.setWidth(99);
        seed.setHeight(71);
        seed.setFill(Color.LIGHTGRAY);
        seed.setStroke(Color.GRAY);
        seed.setStrokeWidth(2);
        seed.setFill(new ImagePattern(new Image(getClass().getResource("./Images/seed.png").toExternalForm())));

        harvest = new Rectangle();
        harvest.setX(455);
        harvest.setY(77);
        harvest.setWidth(99);
        harvest.setHeight(71);
        harvest.setFill(Color.LIGHTGRAY);
        harvest.setStroke(Color.GRAY);
        harvest.setStrokeWidth(2);
        harvest.setFill(new ImagePattern(new Image(getClass().getResource("./Images/harvestor.png").toExternalForm())));

        nextDay = new Rectangle();
        nextDay.setX(556);
        nextDay.setY(77);
        nextDay.setWidth(99);
        nextDay.setHeight(71);
        nextDay.setFill(Color.LIGHTGRAY);
        nextDay.setStroke(Color.GRAY);
        nextDay.setStrokeWidth(2);
        nextDay.setFill(new ImagePattern(new Image(getClass().getResource("./Images/nextDay.png").toExternalForm())));

        hotBar.getChildren().add(rectangle);
        hotBar.getChildren().add(onerectangle);
        hotBar.getChildren().add(tworectangle);
        hotBar.getChildren().add(seed);
        hotBar.getChildren().add(harvest);
        hotBar.getChildren().add(nextDay);
    }

    // Getters & Setters
    public Group getHotbar(){
        return hotBar;
    }


    // Methods
    private void resetUI(){
        onerectangle.setStrokeWidth(2);
        tworectangle.setStrokeWidth(2);
        seed.setStrokeWidth(2);
        harvest.setStrokeWidth(2);
        nextDay.setStrokeWidth(2);
    }

    public void updateSelection(int currentHotBar){
        resetUI();
        switch(currentHotBar){
            case 0: onerectangle.setStrokeWidth(8); break;
            case 1: tworectangle.setStrokeWidth(8); break;
            case 2: seed.setStrokeWidth(8); break;
            case 3: harvest.setStrokeWidth(8); break;
            case 4: nextDay.setStrokeWidth(8); break;
            default: break;
        }
    }
}
