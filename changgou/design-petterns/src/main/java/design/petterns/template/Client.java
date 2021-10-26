package design.petterns.template;

public class Client {

    public static void main(String[] args) {
        ConcreteClass_BaoCai concreteClass_baoCai = new ConcreteClass_BaoCai();
        concreteClass_baoCai.cookProcess();

        //炒蒜蓉菜心
        ConcreteClass_CaiXin caiXin = new ConcreteClass_CaiXin();
        caiXin.cookProcess();
    }
}
