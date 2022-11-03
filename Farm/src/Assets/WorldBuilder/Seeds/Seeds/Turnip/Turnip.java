package Assets.WorldBuilder.Seeds.Seeds.Turnip;

import Assets.WorldBuilder.Seeds.CropType;
import Assets.WorldBuilder.Seeds.Seed;
import Assets.WorldBuilder.Seeds.SeedType;

public final class Turnip extends Seed {
    public Turnip() {
        super();
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
