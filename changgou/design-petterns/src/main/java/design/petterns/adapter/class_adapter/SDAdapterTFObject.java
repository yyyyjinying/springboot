package design.petterns.adapter.class_adapter;

/**
 * 对象适配器模式
 */
public class SDAdapterTFObject implements SDCard {
    private TFCard tfCard;

    public void setTfCard(TFCard tfCard){
        this.tfCard = tfCard;
    }

    public String readSD() {
        return tfCard.readTF();
    }

    public void writeSD(String name) {
    tfCard.writeTF(name);
    }
}
