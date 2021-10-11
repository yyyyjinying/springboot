package design.petterns.proxy.cglib_proxy;

import design.petterns.proxy.static_proxy.SellTickets;

public class TrainStation implements SellTickets {
    public void sell() {
        System.out.println("火车站买票");

    }
}
