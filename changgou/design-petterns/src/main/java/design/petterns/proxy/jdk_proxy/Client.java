package design.petterns.proxy.jdk_proxy;

public class Client {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        SellTickets sellTickets = proxyFactory.getProxyObject();
        sellTickets.sell("a");



    }
}
