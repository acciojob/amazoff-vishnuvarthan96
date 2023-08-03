package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;

public class OrderRepository {
    HashMap<Integer,List<Order>> orderLIST=new HashMap<>();

    public void addOrders(Order order){
      int key=parseInt(order.getId());
      List<Order> orderList=orderLIST.getOrDefault(key,new ArrayList<>());
      orderList.add(order);
      orderLIST.put(key,orderList);
        System.out.println(orderLIST);

    }
}
