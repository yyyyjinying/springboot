package design.petterns.decorator;

public class Bacon extends Garnish {


    public Bacon(FastFood fastFood) {
        super(fastFood, 2,"培根");
    }

    @Override
    public String getDesc() {
        return super.getDesc() + getFastFood().getDesc();
    }

    public float cost() {
        return getPrice() + getFastFood().cost();
    }


}
