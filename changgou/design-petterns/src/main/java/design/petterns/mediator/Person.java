package design.petterns.mediator;

//抽象同事类
public abstract class Person {
    protected String name;
    protected Mediator mediator;

    public Person(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public abstract void constact(String message);
    public abstract void getMessage(String message);
}
