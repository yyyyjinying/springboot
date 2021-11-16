package com.changgou.order.service;

import com.changgou.order.pojo.OrderItem;

import java.util.List;

public interface CartService {
    public List<OrderItem> list(String username);
    public void add(Integer num, String id, String username);
}
