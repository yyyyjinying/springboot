package design.petterns.stategy;

//为中秋准备的促销活动B
public class StrategyB implements Strategy {
    public void show() {
        System.out.println("满200元减50元");
    }
}
