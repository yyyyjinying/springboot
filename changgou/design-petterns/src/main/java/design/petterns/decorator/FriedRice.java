package design.petterns.decorator;

public class FriedRice extends FastFood{

    public FriedRice() {
        super(10,"炒饭");
    }

    public float cost() {
        return getPrice();
    }
}
