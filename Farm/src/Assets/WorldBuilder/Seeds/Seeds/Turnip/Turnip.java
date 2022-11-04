package Assets.WorldBuilder.Seeds.Seeds.Turnip;

import Assets.WorldBuilder.Seeds.CropType;
import Assets.WorldBuilder.Seeds.Seed;
import Assets.WorldBuilder.Seeds.SeedType;

// Children class of the Seed class, adds, updates attributes and methods a Turnip should have
public final class Turnip extends Seed {
    // Constructor, gets all parent attributes and updates it to the needs of what a Turnip should be
    public Turnip() {
        super.getExperience().updateValue(5);
        super.setSeedType(SeedType.Turnip);
        super.setCropType(CropType.ROOTCROP);
        super.getBasePrice().updateValue(6);
        super.getSeedCost().updateValue(5);
        super.setHarvestTime(2);
        super.setWaterNeeds(new int[]{1,2});
        super.setFertilizerNeeds(new int[]{0,1});
        super.setProductsProduces(new int[]{1,2});
        super.setModelTextures(getClass().getResource("./Models/turnip"));
    }
}
