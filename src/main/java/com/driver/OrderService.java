package com.driver;

import org.springframework.beans.factory.annotation.Autowired;

public class OrderService {
    @Autowired
    private OrderRepository orderRepositoryObj;
    public void addOrder(Order order){
        orderRepositoryObj.addOrders(order);
    }
}
