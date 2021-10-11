package design.petterns.proxy.static_proxy;

public class ProxyPoint implements SellTickets {
    private TrainStation trainStation = new TrainStation();

    public void sell() {
        System.out.println("代理火车票收取一点费用");
        trainStation.sell();
    }
}
