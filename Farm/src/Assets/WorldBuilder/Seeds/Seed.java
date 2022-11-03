package Assets.WorldBuilder.Seeds;

import java.net.URL;
import java.util.Random;

import Assets.Player.Items.Coins.Coins;
import Assets.Player.Items.Experience.Experience;
import Assets.WorldBuilder.Farm;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;

public abstract class Seed {
    private Experience experienceYield = new Experience(0);
    private SeedType seedType;
    private CropType cropType;
    private Coins basePrice = new Coins(0),
                    seedCost = new Coins(0);
    
    private int harvestTime;
    private int[] waterNeeds = new int[2];
    private int[] fertilizerNeeds = new int[2];
    private int[] productsProduced = new int[2];
    private int harvestProduce = -1;

    protected URL modelTextures;

    // Methods
    public void Grow(){
        
    }

    // Getters & Setters
    public Experience getExperience(){
        return experienceYield;
    }

    public SeedType getSeedType(){
        return seedType;
    }
    public void setSeedType(SeedType seedType){
        this.seedType = seedType;
    }

    public CropType getCropType(){
        return cropType;
    }
    public void setCropType(CropType cropType){
        this.cropType = cropType;
    }

    public Coins getBasePrice(){
        return basePrice;
    }
    public Coins getSeedCost(){
        return seedCost;
    }

    public int getHarvestTime(){
        return harvestTime;
    }
    public void setHarvestTime(int harvestTime){
        this.harvestTime = harvestTime;
    }

    public int[] getWaterNeeds(){
        return waterNeeds;
    }
    public void setWaterNeeds(int[] waterNeeds){
        this.waterNeeds = waterNeeds;
    }

    public int[] getFertilizerNeeds(){
        return fertilizerNeeds;
    }
    public void setFertilizerNeeds(int[] fertilizerNeeds){
        this.fertilizerNeeds = fertilizerNeeds;
    }

    public int[] getProductsProduced(){
        return productsProduced;
    }

    public int getHarvestProduce(){
        if (harvestProduce == -1){
            Random rand = new Random();
            harvestProduce = rand.nextInt(productsProduced[0], productsProduced[1]+1);
        }
        return harvestProduce;
    }
    public int updateHarvestProduce(){
        if (harvestProduce != 0)
            harvestProduce = 0;
        return harvestProduce;
    }

    public void setProductsProduces(int[] productsProduced){
        this.productsProduced = productsProduced;
    }

    public void updateHarvestTime(){
        harvestTime --;
    }

    public void setModelTextures(URL modelTextures){
        this.modelTextures = modelTextures;
    }
    public URL getModelTextures(){
        return modelTextures;
    }


    // Animations
    public static void selectSeedPlanted(int currentTileIndex, int previousTileIndex){
        Node currentTile = Farm.getSeedsPlantedTextures().getChildren().get(currentTileIndex);
        Node previousTile = Farm.getSeedsPlantedTextures().getChildren().get(previousTileIndex);
        AnimationTimer timer = new AnimationTimer() {
            double speed = 1;
            @Override
            public void handle(long now) {
                currentTile.setTranslateY(currentTile.getTranslateY() + speed * speed * (3.0 - 2.0 * speed));
                previousTile.setTranslateY(previousTile.getTranslateY() - speed * speed * (3.0 - 2.0 * speed));
                if (currentTile.getTranslateY() <= -250 && previousTile.getTranslateY() >= -50){
                    previousTile.setTranslateY(-50);
                    currentTile.setTranslateY(-250);
                    this.stop();
                }
                speed += 0.02;
            }
            
        };
        timer.start();
    }
}
