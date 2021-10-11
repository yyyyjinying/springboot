package design.petterns.pototype.demo1;

public class Realizetype implements Cloneable {
    @Override
    public Realizetype clone() throws CloneNotSupportedException {
        return (Realizetype) super.clone();
    }
}
