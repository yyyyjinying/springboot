package design.petterns.singleton.demo3;

public class Client {
    public static void main(String[] args) {
       Singleton a = Singleton.INSTANCE;
       Singleton b = Singleton.INSTANCE;
        System.out.println(a == b);
    }
}
