package design.petterns.pototype.demo1;

public class Client {
    public static void main(String[] args) throws CloneNotSupportedException {
        Realizetype realizetype = new Realizetype();
        Realizetype clone = realizetype.clone();

        System.out.println("原型对象与克隆对象是否是同一个对象：" + (clone == realizetype));

    }
}
