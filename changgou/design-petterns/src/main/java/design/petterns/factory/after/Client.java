package design.petterns.factory.after;

public class Client {
    public static void main(String[] args) {
        CoffeeStore coffeeStore = new CoffeeStore();
        coffeeStore.orderCoffee("latte");
        coffeeStore.orderCoffee("american");
     ;
    }
}
