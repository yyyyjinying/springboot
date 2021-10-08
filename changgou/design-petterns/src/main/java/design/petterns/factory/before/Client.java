package design.petterns.factory.before;

public class Client {
    public static void main(String[] args) {
        CoffeeStore coffeeStore = new CoffeeStore();
        Coffee coffee = coffeeStore.orderCoffee("latte");
        String name = coffee.getName();
        System.out.println(name);
    }
}
