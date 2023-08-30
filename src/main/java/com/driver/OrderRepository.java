package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;
@Repository
public class OrderRepository {
    HashMap<String, Order> orderList = new HashMap<>();
    HashMap<String,DeliveryPartner> deliveryPartnerHashMap =new HashMap<>();

    HashMap<String, List<Order>> partnerOrderMap=new HashMap<>();

    HashMap<String,List<Order>> deliveredOrder=new HashMap<>();


    public void addOrders(Order order) {
        orderList.put(order.getId(), order);
    }
    public void addPartner(String partnerId){
     DeliveryPartner dp=new DeliveryPartner(partnerId);
     deliveryPartnerHashMap.put(partnerId,dp);
    }
    public void orderPartnerPair(String orderId,String partnerId){
        Order order=orderList.get(orderId);
        System.out.println(order.getId());
        DeliveryPartner dp=deliveryPartnerHashMap.get(partnerId);
        System.out.println(dp.getId());
        if(order==null || dp==null)
            return;
        if(partnerOrderMap.containsKey(dp.getId())){
            partnerOrderMap.get(dp.getId()).add(order);
        }
        else{
            List<Order> list=new ArrayList<>();
            list.add(order);
            partnerOrderMap.put(dp.getId(),list);
        }
        dp.setNumberOfOrders(dp.getNumberOfOrders()+1);
    }

    public Order getOrder(String id){
        return orderList.get(id);
    }
    public DeliveryPartner getPartner(String id){
        return deliveryPartnerHashMap.get(id);
    }
    public int getOrderCountByPartnerId(String id){
        if(!partnerOrderMap.containsKey(id))
            return 0;
        List<Order> list=partnerOrderMap.get(id);
        return list.size();
    }
    public List<String> getOrders(String id){
        List<Order> order=partnerOrderMap.get(id);
        List<String> list=new ArrayList<>();
        for(Order o:order){
            list.add(o.getId());
        }
        return list;
    }
    public List<String> getAllOrder(){
        List<String> order=new ArrayList<>();
        for(String s:orderList.keySet()){
            order.add(s);
        }
        return order;
    }
    public int getCountOfUnassignedOrder(){
        int totalOrders=getAllOrder().size();
        int assignedOrders=0;
        for(String dp:partnerOrderMap.keySet()){
            assignedOrders+= getOrderCountByPartnerId(dp);
        }
        return totalOrders-assignedOrders;
    }
   public int getOrdersLeft(String time,String partnerId){
        String hh=time.substring(0,2);
        String mm=time.substring(3);
        int h=Integer.parseInt(hh)*60;
        int m=Integer.parseInt(mm);
        int givenTime=h+m;
        if(deliveredOrder.containsKey(partnerId)){
            deliveredOrder.remove(partnerId);
        }
        int countOfAllOrder= getOrderCountByPartnerId(partnerId);
        List<String> ordersOfPartner = getOrders(partnerId);
        List<Order> deliveredByPartner =new ArrayList<>();
        for(String s:ordersOfPartner) {
            Order order = orderList.get(s);
            if (givenTime - order.getDeliveryTime() >= 0) {
                deliveredByPartner.add(order);
            }
        }
            deliveredOrder.put(partnerId,deliveredByPartner);
            int countOfDeliveredOrder=deliveredByPartner.size();
            return countOfAllOrder-countOfDeliveredOrder;
        }

   public String getLastDeliveryTime(String partnerId){
        List<Order> deliveredOrderList=deliveredOrder.get(partnerId);
        int n=deliveredOrderList.size();
        Order lastDelivered=deliveredOrderList.get(n-1);
        int time=lastDelivered.getDeliveryTime();

        int h=time/60;
        int m=time%60;

        String hh=String.valueOf(n);
        String mm="";
        if(m>=0 && m<=9){
            mm="0"+String.valueOf(m);
        }
        else{
            mm=String.valueOf(m);
        }
        return hh+":"+mm;
   }
   public void deletePartnerById(String partnerId){
        deliveryPartnerHashMap.remove(partnerId);
        partnerOrderMap.remove(partnerId);
   }
   public void deleteOrderById(String orderId){
        orderList.remove(orderId);
        for(String dp:partnerOrderMap.keySet()){
            List<Order> orders=partnerOrderMap.get(dp);
            for(Order order:orders){
                if(order.getId()==orderId){
                    partnerOrderMap.get(dp).remove(order);
                }
            }
        }
   }
}
