package design.petterns.factory.method;

public class CoffeStore {


    private CoffeeFactory coffeeFactory;

    public CoffeStore(CoffeeFactory coffeeFactory){
        this.coffeeFactory = coffeeFactory;

    }

    public Coffee orderCoffee(){
        Coffee coffee = coffeeFactory.createCoffee();
        coffee.addsugar();
        coffee.adMilk();
        return coffee;
    }
}
