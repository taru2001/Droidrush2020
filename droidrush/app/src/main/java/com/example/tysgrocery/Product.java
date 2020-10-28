package com.example.tysgrocery;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Product extends AppCompatActivity {

    ActionBar actionBar;
    String product,price;
    TextView products,prices,qt,desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        actionBar = getActionBar();
        if (actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        products = findViewById(R.id.product);
        prices = findViewById(R.id.price);
        qt = findViewById(R.id.quantity);
        desc = findViewById(R.id.description);

        Intent intent = getIntent();
        product = intent.getStringExtra(dry.MSG);
        price = intent.getStringExtra(dry.MSG);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            String product = bundle.getString("product");
            String price = bundle.getString("price");
            String quantity = bundle.getString("quantity");
            String description = bundle.getString("description");
            products.setText(product);
            prices.setText(price);
            qt.setText(quantity);
            desc.setText(description);
        }



    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), userHome.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}