package design.petterns.adapter.class_adapter;

/**
 * 类适配器模式
 */
public class SDAdapterTF extends TFCardImpl implements SDCard {
    public String readSD() {
        System.out.println("adapter read tf card ");
        return readTF();
    }

    public void writeSD(String name) {
        System.out.println("adapter write tf card");
        writeTF(name);

    }
}
