package design.petterns.factory.aMenthod;

public abstract class Dessert {
    public abstract String getName();

    public void addsugar(){
        System.out.println("加-糖");
    }

    public void adMilk(){
        System.out.println("加-奶");
    }
}
