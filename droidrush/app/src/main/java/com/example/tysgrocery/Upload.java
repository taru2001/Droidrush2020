package com.example.tysgrocery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Upload extends AppCompatActivity {

    public EditText product;
    EditText price;
    EditText quantity;
    EditText description;
    FirebaseAuth auth;
    FirebaseFirestore fstore;
    Button upload;
    String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        product = findViewById(R.id.product);
        price = findViewById(R.id.price);
        quantity = findViewById(R.id.quantity);
        description = findViewById(R.id.description);
        upload = findViewById(R.id.upload);

        final String txt_product = product.getText().toString();
        final String txt_quantity = quantity.getText().toString();
        final String txt_price = price.getText().toString();
        final String txt_description = description.getText().toString();

        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserId = auth.getCurrentUser().getUid();
                DocumentReference documentReference = fstore.collection("Beverages").document();
                Map<String, Object> beverage = new HashMap<>();
                beverage.put("Product Name",txt_product);
                beverage.put("Price",txt_price);
                beverage.put("Quantity",txt_quantity);
                beverage.put("Description",txt_description);
                documentReference.set(beverage).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG","Product is added"+txt_product);
                    }
                });
                Toast.makeText(Upload.this, "Product Uploaded", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),userHome.class));
                finish();

            }
        });


    }
}