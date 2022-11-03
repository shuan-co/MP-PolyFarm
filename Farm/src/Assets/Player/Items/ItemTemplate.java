package Assets.Player.Items;

public abstract class ItemTemplate {
    protected float value;

    public ItemTemplate(float value){
        this.value = value;
    }

    public float getValue(){
        return value;
    }
    public void updateValue(float value){
        this.value += value;
    }
}
