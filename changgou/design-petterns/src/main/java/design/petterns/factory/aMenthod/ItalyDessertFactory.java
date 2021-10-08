package design.petterns.factory.aMenthod;

public class ItalyDessertFactory implements DessertFactory{

    public Coffee createCoffee() {
        return new LatteCoffee();
    }

    public Dessert createDessert() {
        return new Tiramisu();
    }
}
