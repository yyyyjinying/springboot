package design.petterns.decorator;

public class Egg extends Garnish {


    public Egg(FastFood fastFood) {
        super(fastFood, 1,"鸡蛋");
    }

    @Override
    public String getDesc() {
        return super.getDesc() + getFastFood().getDesc();
    }

    public float cost() {
        return getPrice() + getFastFood().cost();
    }


}
