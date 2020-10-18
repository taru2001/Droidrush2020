package com.example.tysgrocery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

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
    EditText product_number;
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
//        product_number = findViewById(R.id.product_number);
        upload = findViewById(R.id.upload);

        Intent intent = getIntent();
        Intent intent2 = getIntent();
        final String message = intent.getStringExtra(Beverages.MSG);
        final String msg = intent2.getStringExtra(Dairy.MSGS);

        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
//        System.out.println(message);
//        System.out.println(msg);
        Log.d("TAG","this "+ msg);
        Log.d("TAG","this "+ message);


            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    UserId = auth.getCurrentUser().getUid();
                    final String txt_product = product.getText().toString();
                    final String txt_quantity = quantity.getText().toString();
                    final String txt_price = price.getText().toString();
                    final String txt_description = description.getText().toString();
//
                    uploadproduct(txt_product,txt_quantity,txt_price,txt_description, msg,  message);


                }
            });


    }
    public void uploadproduct(final String product,final String quantity,final String price, final String desc,

                              String msg, String message ){
        try {

            if (msg.equals("dairy")) {
                UserId = auth.getCurrentUser().getUid();

                DocumentReference documentReference = fstore.collection("Dairy").document(product).collection("Product No.").document(product);
                Map<String, Object> dairy = new HashMap<>();
//                    beverage.put("Product Number", txt_product_number);
                dairy.put("Product Name", product);
                dairy.put("Price", price);
                dairy.put("Quantity", quantity);
                dairy.put("Description", desc);
                documentReference.set(dairy).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "Product is added " + product);
                    }
                });
                Toast.makeText(Upload.this, "Product Uploaded", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), userHome.class));
                finish();
            } else if (message.equals("beverages")) {
                UserId = auth.getCurrentUser().getUid();
                DocumentReference documentReference = fstore.collection("Beverages").document(product).collection("Product No.").document(product);
                Map<String, Object> beverage = new HashMap<>();
//                    beverage.put("Product Number", txt_product_number);
                beverage.put("Product Name", product);
                beverage.put("Price", price);
                beverage.put("Quantity", quantity);
                beverage.put("Description", desc);
                documentReference.set(beverage).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "Product is added " + product);
                    }
                });
                Toast.makeText(Upload.this, "Product Uploaded", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), userHome.class));
                finish();
            }
            else{
                Log.d("TAG", "are chl jaa");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}