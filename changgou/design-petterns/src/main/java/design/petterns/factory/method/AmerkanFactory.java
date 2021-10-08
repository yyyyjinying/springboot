package design.petterns.factory.method;

public class AmerkanFactory implements CoffeeFactory {


    public Coffee createCoffee() {
        return new AmerkanCoffee();
    }
}
