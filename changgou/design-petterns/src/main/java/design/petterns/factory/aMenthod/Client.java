package design.petterns.factory.aMenthod;

public class Client {
    public static void main(String[] args) {
        DessertFactory factory = new ItalyDessertFactory();
//        DessertFactory factory = new AmericanDessertFactory();
        Store store = new Store(factory);
        store.orderMenu();
    }
}
