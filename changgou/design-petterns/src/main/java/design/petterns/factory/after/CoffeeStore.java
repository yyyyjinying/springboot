package design.petterns.factory.after;

public class CoffeeStore {
    public void orderCoffee(String type){
        SimpleCoffeeFactory simpleCoffeeFactory = new SimpleCoffeeFactory();
        Coffee coffee = simpleCoffeeFactory.createCoffee(type);

        coffee.addsugar();
        coffee.adMilk();
        System.out.println(coffee.getName());
    }
}
