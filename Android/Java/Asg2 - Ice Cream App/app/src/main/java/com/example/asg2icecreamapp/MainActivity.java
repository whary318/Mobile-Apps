package com.example.asg2icecreamapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Double total;
    TextView totalTextView;

    Spinner iceCreamTypeSpinner, iceCreamSizeSpinner;

    CheckBox mAndMsCheckBox, marshmallowsCheckBox,
            browniesCheckBox, oreosCheckBox,
            strawberriesCheckBox, almondsCheckBox,
            gummyBearsCheckBox, peanutsCheckBox;

    SeekBar hotFudgeAmountSeekBar;
    TextView hotFudgeAmountTextView;

    //Button theWorksButton, resetButton, orderButton;

    NumberFormat fmt;
    ArrayList<OrderItem> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialization of variables and connection to views
        setTitle("Ice cream Maker App");
        fmt = NumberFormat.getCurrencyInstance();

        totalTextView = findViewById(R.id.total_textView);

        iceCreamTypeSpinner = findViewById(R.id.ice_cream_spinner);
        iceCreamSizeSpinner = findViewById(R.id.ice_cream_size_spinner);

        mAndMsCheckBox = findViewById(R.id.m_n_ms_checkBox);
        marshmallowsCheckBox = findViewById(R.id.marshmallows_checkBox);
        browniesCheckBox = findViewById(R.id.brownies_checkBox);
        oreosCheckBox = findViewById(R.id.oreos_checkBox);
        strawberriesCheckBox = findViewById(R.id.strawberries_checkBox);
        almondsCheckBox = findViewById(R.id.almonds_checkBox);
        gummyBearsCheckBox = findViewById(R.id.gummy_bears_checkBox);
        peanutsCheckBox = findViewById(R.id.peanuts_checkBox);

        hotFudgeAmountSeekBar = findViewById(R.id.hot_fudge_seekBar);
        hotFudgeAmountTextView = findViewById(R.id.hot_fudge_amount_textView);

        orders = new ArrayList<OrderItem>();

//        theWorksButton = findViewById(R.id.the_works_button);
//        resetButton = findViewById(R.id.reset_button);
//        orderButton = findViewById(R.id.order_button);


        //functions setup - listeners
        iceCreamSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calculations();
                updateUI();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        hotFudgeAmountSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                calculations();
                updateUI();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        calculations();
        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_order_history) {
            Intent i = new Intent(this, OrderHistoryActivity.class);
            i.putExtra("OrdersKey", orders);
            startActivity(i);
            return true;
        }
        else if (id == R.id.action_about) {
            Intent i = new Intent(this, AboutPageActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void handleReset(View v) {
        iceCreamTypeSpinner.setSelection(0);
        iceCreamSizeSpinner.setSelection(0);

        mAndMsCheckBox.setChecked(false);
        marshmallowsCheckBox.setChecked(false);
        browniesCheckBox.setChecked(false);
        oreosCheckBox.setChecked(false);
        strawberriesCheckBox.setChecked(false);
        almondsCheckBox.setChecked(false);
        gummyBearsCheckBox.setChecked(false);
        peanutsCheckBox.setChecked(false);

        hotFudgeAmountSeekBar.setProgress(1);

        calculations();
        updateUI();
    }
    public void handleOrder(View v) {
        orders.add(new OrderItem(
                iceCreamTypeSpinner.getSelectedItem().toString(),
                iceCreamSizeSpinner.getSelectedItem().toString(),
                fmt.format(total)
            )
        );
        Toast.makeText(this, "Yay, your order is on the way!", Toast.LENGTH_SHORT).show();
        //Log.d("DEBUG", orders.toString());
    }
    public void handleTheWorks(View v) {
        iceCreamSizeSpinner.setSelection(2);

        mAndMsCheckBox.setChecked(true);
        marshmallowsCheckBox.setChecked(true);
        browniesCheckBox.setChecked(true);
        oreosCheckBox.setChecked(true);
        strawberriesCheckBox.setChecked(true);
        almondsCheckBox.setChecked(true);
        gummyBearsCheckBox.setChecked(true);
        peanutsCheckBox.setChecked(true);

        hotFudgeAmountSeekBar.setProgress(hotFudgeAmountSeekBar.getMax());

        calculations();
        updateUI();
    }
    public void handleChecked(View v) {
        calculations();
        updateUI();
    }

    public void updateUI(){
        totalTextView.setText(fmt.format(total));
        hotFudgeAmountTextView.setText(hotFudgeAmountSeekBar.getProgress() + " oz");
    }
    public void calculations() {
        total = 0.0;
        switch (iceCreamSizeSpinner.getSelectedItemPosition()){
            case 0:
                total += 2.99;
                break;
            case 1:
                total += 3.99;
                break;
            case 2:
                total += 4.99;
        }

        if(peanutsCheckBox.isChecked())
            total += 0.15;
        if(mAndMsCheckBox.isChecked())
            total += 0.25;
        if(almondsCheckBox.isChecked())
            total += 0.15;
        if(browniesCheckBox.isChecked())
            total += 0.20;
        if(strawberriesCheckBox.isChecked())
            total += 0.20;
        if(oreosCheckBox.isChecked())
            total += 0.20;
        if(gummyBearsCheckBox.isChecked())
            total += 0.20;
        if(marshmallowsCheckBox.isChecked())
            total += 0.15;

        switch (hotFudgeAmountSeekBar.getProgress()){
            case 1:
                total += 0.15;
                break;
            case 2:
                total += 0.25;
                break;
            case 3:
                total += 0.30;
                break;
        }
    }
}
