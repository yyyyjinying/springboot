package design.petterns.visitor;

public class Owner implements Person {
    public void feed(Cat cat) {
        System.out.println("主人喂食猫");
    }

    public void feed(Dog dog) {
        System.out.println("主人喂食狗");

    }
}
