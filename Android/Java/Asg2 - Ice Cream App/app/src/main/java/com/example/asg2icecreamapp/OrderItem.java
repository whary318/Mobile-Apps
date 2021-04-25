package com.example.asg2icecreamapp;

import java.io.Serializable;
import java.util.Date;

public class OrderItem implements Serializable {
    Date date;
    String flavor;
    String size;
    String cost;

    OrderItem(String flavor, String size, String cost) {
        date = new Date();
        this.flavor = flavor;
        this.size = size;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return  "Date: " + date + '\n' +
                "Flavor: " + flavor + '\n' +
                "Size: " + size + '\n' +
                "Cost: " + cost;
    }
}
