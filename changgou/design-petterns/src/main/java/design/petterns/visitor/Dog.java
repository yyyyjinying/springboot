package design.petterns.visitor;

public class Dog implements Animal {
    public void accept(Person person) {
        person.feed(this);
        System.out.println("好好吃，汪汪汪！！！");
    }
}
