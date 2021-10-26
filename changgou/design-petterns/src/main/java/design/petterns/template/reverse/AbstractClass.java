package design.petterns.template.reverse;

public abstract class AbstractClass implements IAbc {
    public abstract void read();

    public void read(String name) {
        System.out.println("read " + name);
        read();
    }
}
