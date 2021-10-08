package design.petterns.factory.after;

public abstract class Coffee {

    public abstract String getName();

    public void addsugar(){
        System.out.println("加糖");
    }

    public void adMilk(){
        System.out.println("加奶");
    }
}
