package design.petterns.stategy;

import java.util.Arrays;
import java.util.Comparator;

public class Client {
    public static void main(String[] args) {
        // 春节来了，使用春节促销活动
        SalesMan salesMan = new SalesMan(new StrategyA());
        // 展示促销活动
        salesMan.salesManShow();

        // 促销B
        salesMan.setStrategy(new StrategyB());
        salesMan.salesManShow();

        Integer[] data = {23,8,6,76,4};

        Arrays.sort(data, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2 -o1;
            }
        });

        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);

        }
    }
}
