package com.example.asg3icecreamapp

import java.io.Serializable
import java.util.Date

class OrderItem (private val flavor:String, private val size:String, private val cost:String): Serializable {
    private val date = Date()
    override fun toString(): String {
        return  "Date: $date\n" +
                "Flavor: $flavor\n" +
                "Size: $size\n" +
                "Cost: $cost"
    }
}