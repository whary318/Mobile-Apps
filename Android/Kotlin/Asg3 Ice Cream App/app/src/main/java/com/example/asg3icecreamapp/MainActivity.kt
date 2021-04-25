package com.example.asg3icecreamapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.SeekBar
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private val fmt = NumberFormat.getCurrencyInstance()
    private var orders = ArrayList<OrderItem>()
    private var total = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        title = "Ice cream Maker App"

        //listeners setup
        iceCreamSizeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                calculations()
                updateUI()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
        hotFudgeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                calculations()
                updateUI()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_order_history -> {
                val i = Intent(this, OrderHistoryActivity::class.java)
                i.putExtra("OrdersKey", orders)
                startActivity(i)
                return true
            }
            R.id.action_about -> {
                val i = Intent(this, AboutPageActivity::class.java)
                startActivity(i)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun handleReset(v: View) {
        iceCreamSpinner.setSelection(0)
        iceCreamSizeSpinner.setSelection(0)

        mAndMsCheckBox.isChecked = false
        marshmallowsCheckBox.isChecked = false
        browniesCheckBox.isChecked = false
        oreosCheckBox.isChecked = false
        strawberriesCheckBox.isChecked = false
        almondsCheckBox.isChecked = false
        gummyBearsCheckBox.isChecked = false
        peanutsCheckBox.isChecked = false

        hotFudgeSeekBar.progress = 1

        calculations()
        updateUI()
    }
    fun handleChecked(v: View) {
        calculations()
        updateUI()
    }
    fun handleTheWorks(v: View) {
        iceCreamSizeSpinner.setSelection(iceCreamSizeSpinner.count - 1)

        mAndMsCheckBox.isChecked = true
        marshmallowsCheckBox.isChecked = true
        browniesCheckBox.isChecked = true
        oreosCheckBox.isChecked = true
        strawberriesCheckBox.isChecked = true
        almondsCheckBox.isChecked = true
        gummyBearsCheckBox.isChecked = true
        peanutsCheckBox.isChecked = true

        hotFudgeSeekBar.progress = hotFudgeSeekBar.max

        calculations()
        updateUI()
    }
    fun handleOrder(v: View) {
        orders.add(OrderItem(
            iceCreamSpinner.selectedItem.toString(),
            iceCreamSizeSpinner.selectedItem.toString(),
            fmt.format(total)
            )
        )
        Toast.makeText(this, "Yay! you made an order", Toast.LENGTH_SHORT).show()
    }

    private fun updateUI() {
        totalTextView.text = fmt.format(total)
        hotFudgeAmountTextView.text = hotFudgeSeekBar.progress.toString() + " oz"
    }
    private fun calculations() {
        total = 0.0
        when (iceCreamSizeSpinner.selectedItemPosition) {
            0 -> total += 2.99
            1 -> total += 3.99
            2 -> total += 4.99
        }

        if (peanutsCheckBox.isChecked)
            total += 0.15
        if (mAndMsCheckBox.isChecked)
            total += 0.25
        if (almondsCheckBox.isChecked)
            total += 0.15
        if (browniesCheckBox.isChecked)
            total += 0.20
        if (strawberriesCheckBox.isChecked)
            total += 0.20
        if (oreosCheckBox.isChecked)
            total += 0.20
        if (gummyBearsCheckBox.isChecked)
            total += 0.20
        if (marshmallowsCheckBox.isChecked)
            total += 0.15

        when (hotFudgeSeekBar.progress) {
            1 -> total += 0.15
            2 -> total += 0.25
            3 -> total += 0.30
        }
    }
}
