package design.petterns.visitor;

public class Cat implements Animal {
    public void accept(Person person) {
        person.feed(this);
        System.out.println("好好吃，喵喵喵！！！");
    }
}
