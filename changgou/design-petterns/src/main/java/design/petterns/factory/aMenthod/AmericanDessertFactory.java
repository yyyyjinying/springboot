package design.petterns.factory.aMenthod;

public class AmericanDessertFactory implements DessertFactory {
    public Coffee createCoffee() {
        return new AmerkanCoffee();
    }

    public Dessert createDessert() {
        return new MatchaMousse();
    }
}
