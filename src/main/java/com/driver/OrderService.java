package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepositoryObj;
    public void addOrder(Order order){
        orderRepositoryObj.addOrders(order);
    }
    public void addPartner(String partnerId){
        orderRepositoryObj.addPartner(partnerId);
    }
    public void orderPartnerPair(String orderId,String partnerId){
        orderRepositoryObj.orderPartnerPair(orderId,partnerId);
    }
    public Order getOrder(String id){
        return orderRepositoryObj.getOrder(id);
    }
    public DeliveryPartner getPartner(String id){
        return orderRepositoryObj.getPartner(id);
    }
    public int getOrderCountByPartnerId(String id){
        return orderRepositoryObj.getOrderCountByPartnerId(id);
    }
    public List<String> getOrders(String id){
        return orderRepositoryObj.getOrders(id);
    }
    public List<String> getAllOrder(){
        return orderRepositoryObj.getAllOrder();
    }
    public int getUnassignedOrders(){
        return orderRepositoryObj.getCountOfUnassignedOrder();
    }
    public int getOrdersLeft(String time,String partnerId){
        return orderRepositoryObj.getOrdersLeft(time,partnerId);
    }
    public String getLastDeliveryTime(String partnerId){
        return orderRepositoryObj.getLastDeliveryTime(partnerId);
    }
    public void deletePartnerById(String partnerId){
        orderRepositoryObj.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId){
        orderRepositoryObj.deleteOrderById(orderId);
    }
}
