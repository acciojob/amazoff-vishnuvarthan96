package com.driver;

import static java.lang.Integer.parseInt;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {
   this.id=id;
   String temp=deliveryTime.substring(0,2);
   String temp2=deliveryTime.substring(3);
   int t1=parseInt(temp);
   t1*=60;
   this.deliveryTime=t1+parseInt(temp2);
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        System.out.println(this.deliveryTime);
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
