package design.petterns.flyweight;

public class Client {
    public static void main(String[] args) {
        AbstractBox Ibox = BoxFactory.getInstance().getBox("I");
        Ibox.display("红色");

        AbstractBox Lbox = BoxFactory.getInstance().getBox("L");
        Lbox.display("绿色");

        AbstractBox Obox = BoxFactory.getInstance().getBox("O");

        AbstractBox Obox2 = BoxFactory.getInstance().getBox("O");
        Obox.display("灰色");
        System.out.println(Obox == Obox2);
    }
}
