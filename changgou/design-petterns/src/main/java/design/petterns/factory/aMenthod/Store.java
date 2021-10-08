package design.petterns.factory.aMenthod;

public class Store {
    DessertFactory dessertFactory;

    public Store(DessertFactory dessertFactory){
        this.dessertFactory = dessertFactory;
    }

    public void orderMenu(){

        Coffee coffee = dessertFactory.createCoffee();
        coffee.addsugar();
        coffee.adMilk();
        Dessert dessert = dessertFactory.createDessert();
        dessert.addsugar();
        dessert.adMilk();
        System.out.println(coffee.getName() + "----" + dessert.getName());

    }
}
