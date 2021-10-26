package design.petterns.stategy;

//为春节准备的促销活动A
public class StrategyA implements Strategy  {
    public void show() {
        System.out.println("买一送一");
    }
}
