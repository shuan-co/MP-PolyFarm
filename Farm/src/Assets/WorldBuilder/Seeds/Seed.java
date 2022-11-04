package Assets.WorldBuilder.Seeds;

import java.net.URL;
import java.util.Random;

import Assets.Player.Items.Coins.Coins;
import Assets.Player.Items.Experience.Experience;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;

// Class Template for what methods and attributes a Seed must contain
public abstract class Seed {
    // Attributes
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

    // Seed Texture
    protected URL modelTextures;

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
    // Randomizes a harvest produce once a seed is ready to be harvested and returns it
    public int getHarvestProduce(){
        // Can only generate a harvest produce once for every Seed
        if (harvestProduce == -1){
            Random rand = new Random();
            // Randomized within the bounds of possible products produced by the seed
            harvestProduce = rand.nextInt(productsProduced[0], productsProduced[1]+1);
        }
        return harvestProduce;
    }
    // Updates and returns new harvestProduce, occurs when a seed & tile withers
    public int updateHarvestProduce(){
        // Can only occur once, simply becomes a getter after first call of function per seed
        if (harvestProduce != 0)
            harvestProduce = 0;
        return harvestProduce;
    }
    public void setProductsProduces(int[] productsProduced){
        this.productsProduced = productsProduced;
    }
    public void updateHarvestTime(){
        // Similar to setter but rather it updates by decrementing harvestTime
        harvestTime --;
    }
    public void setModelTextures(URL modelTextures){
        this.modelTextures = modelTextures;
    }
    public URL getModelTextures(){
        return modelTextures;
    }

    // Animations Method [To highlight selection and selection of Seed]
    public static void selectSeedPlanted(Node currentTile, Node previousTile){
        /*
            Creates an AnimationTimer which will be called each frame while it is active,
            handle will be called in every frame
        */ 
        AnimationTimer timer = new AnimationTimer() {
            double speed = 1;
            @Override
            public void handle(long now) {
                /*
                    speed * speed * (3.0 - 2.0 * speed), is a math formula to simulate ease in and ease out animations
                    will move currentTile and previousTile's position by a certain distance increasing and decreasing
                    for every frame of execution
                */ 
                currentTile.setTranslateY(currentTile.getTranslateY() + speed * speed * (3.0 - 2.0 * speed));
                previousTile.setTranslateY(previousTile.getTranslateY() - speed * speed * (3.0 - 2.0 * speed));

                // if both current Seed and previous Seed reaches their destination
                if (currentTile.getTranslateY() <= -250 && 
                    previousTile.getTranslateY() >= -50){
                    /*
                        Most case, the coordinates of currentTile and previousTile will overshoot their target
                        location by a little, as such during the last frame of execution we simply teleport
                        them to where they exactly should be, not noticable due to the small difference and
                        time frame
                    */
                    previousTile.setTranslateY(-50);
                    currentTile.setTranslateY(-250);
                    // end animation
                    this.stop();
                }
                // increase speed to create momentum in animation
                speed += 0.02;
            }
            
        };
        // start animation
        timer.start();
    }
}
