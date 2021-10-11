package design.petterns.proxy.static_proxy;

public class Client {
    public static void main(String[] args) {
        SellTickets proxyPoint = new ProxyPoint();

        proxyPoint.sell();
    }
}
