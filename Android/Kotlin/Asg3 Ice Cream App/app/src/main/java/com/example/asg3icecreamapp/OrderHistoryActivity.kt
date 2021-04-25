package com.example.asg3icecreamapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_order_history.*

class OrderHistoryActivity : AppCompatActivity() {
    private var ordersSTR = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)

        title = "Order History"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val i = intent
        val orders = i.getSerializableExtra("OrdersKey") as ArrayList<OrderItem>
        for(order in orders) {
            ordersSTR.add(order.toString())
        }

        val adapter = ArrayAdapter<String> (
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            ordersSTR)

        orderHistoryListView.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
