package design.petterns.adapter.class_adapter;

public class Client {
    public static void main(String[] args) {
        Computer computer = new Computer();
        SDCardImpl sdCard = new SDCardImpl();
        System.out.println(computer.readSD(sdCard));

        System.out.println("=================");


        SDAdapterTF sdAdapterTF = new SDAdapterTF();

        System.out.println(computer.readSD(sdAdapterTF));

        System.out.println("object============");
        TFCard tfCard = new TFCardImpl();

        SDAdapterTFObject sdAdapterTFObject = new SDAdapterTFObject();
        sdAdapterTFObject.setTfCard(tfCard);
        System.out.println(sdAdapterTFObject.readSD());
    }
}
