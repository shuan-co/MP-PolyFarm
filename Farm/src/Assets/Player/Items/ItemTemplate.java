package Assets.Player.Items;

// Class template to determine what methods and attributes an item must have
public abstract class ItemTemplate {

    // Attribute
    protected float value;

    // Constructor, set a default starting value
    public ItemTemplate(float value){
        this.value = value;
    }

    // Getter
    public float getValue(){
        return value;
    }

    // Update object'svalue attribute by a certain value
    public void updateValue(float value){
        this.value += value;
    }
}
