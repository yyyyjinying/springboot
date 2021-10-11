package design.petterns.proxy.jdk_proxy;

public class TrainStation implements SellTickets {
    public void sell(String a) {
        System.out.println("火车站买票:"+a);

    }
}
