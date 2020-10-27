package com.example.tysgrocery;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    String UserId,msg="aa",message="aa",bakery_msg="aa",txt_product,txt_quantity,txt_price,txt_description;
    String fv_msg="aa",cleaners_msg="aa",dry_msg="aa";
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        actionBar=getActionBar();
        if (actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        product = findViewById(R.id.products);
        price = findViewById(R.id.price);
        quantity = findViewById(R.id.quantity);
        description = findViewById(R.id.description);
//        product_number = findViewById(R.id.product_number);
        upload = findViewById(R.id.upload);

        Intent intent = getIntent();
        Intent intent2 = getIntent();
        Intent ibakery = getIntent();
        Intent ifv = getIntent();
        Intent icleaners = getIntent();
        Intent idry = getIntent();

        message = intent.getStringExtra(Beverages.MSG);
        msg = intent2.getStringExtra(Dairy.MSGS);
        bakery_msg = ibakery.getStringExtra(Bakery.MSGS);
        fv_msg = ifv.getStringExtra(FandV.MSG);
        cleaners_msg = icleaners.getStringExtra(Cleaners.MSG);
        dry_msg = idry.getStringExtra(dry.MSG);

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
                     txt_product = product.getText().toString();
                     txt_quantity = quantity.getText().toString();
                     txt_price = price.getText().toString();
                      txt_description = description.getText().toString();
//
                    uploadproduct();


                }
            });


    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), userHome.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public void uploadproduct( ){


            if (msg!=null && msg.equals("dairy")) {
                UserId = auth.getCurrentUser().getUid();

                DocumentReference documentReference = fstore.collection("Categories").document(msg).collection(UserId).document(txt_product);
                Map<String, Object> dairy = new HashMap<>();
//                    beverage.put("Product Number", txt_product_number);
                dairy.put("Product", txt_product);
                dairy.put("Price", txt_price);
                dairy.put("Quantity", txt_quantity);
                dairy.put("Description", txt_description);
                documentReference.set(dairy).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "Product is added " + product);
                    }
                });
                Toast.makeText(Upload.this, "Product Uploaded", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), userHome.class));
                finish();
            }
//            else{
//                Log.d("TAG", "Product is added " );
//            }

            else if (message!=null && message.equals("beverages")) {
                DocumentReference documentReference = fstore.collection("Categories").document(message).collection(UserId).document(txt_product);
                if (documentReference != null) {
                    UserId = auth.getCurrentUser().getUid();


                    Map<String, Object> beverage = new HashMap<>();
//                    beverage.put("Product Number", txt_product_number);
                    beverage.put("Product", txt_product);
                    beverage.put("Price", txt_price);
                    beverage.put("Quantity", txt_quantity);
                    beverage.put("Description", txt_description);
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
            }

            else if (bakery_msg!=null && bakery_msg.equals("bakery")){
                DocumentReference documentReference = fstore.collection("Categories").document(bakery_msg).collection(UserId).document(txt_product);
                if (documentReference!=null){
                    Map<String,Object> bakery = new HashMap<>();
                    bakery.put("Product", txt_product);
                    bakery.put("Price", txt_price);
                    bakery.put("Quantity", txt_quantity);
                    bakery.put("Description", txt_description);
                    documentReference.set(bakery).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Upload.this, "Product Uploaded", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),userHome.class));
                            finish();
                        }
                    });
                }
            }

            else if(fv_msg!=null && fv_msg.equals("fruits&veg")){
                DocumentReference documentReference = fstore.collection("Categories").document(fv_msg).collection(UserId).document(txt_product);
                if(documentReference!=null){
                    Map<String,Object> fruits_veg = new HashMap<>();
                    fruits_veg.put("Product", txt_product);
                    fruits_veg.put("Price", txt_price);
                    fruits_veg.put("Quantity", txt_quantity);
                    fruits_veg.put("Description", txt_description);
                    documentReference.set(fruits_veg).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Upload.this, "Product Uploaded!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),userHome.class));
                            finish();
                        }
                    });
                }
            }

            else if (cleaners_msg!=null && cleaners_msg.equals("cleaners")){
                DocumentReference documentReference = fstore.collection("Categories").document(cleaners_msg).collection(UserId).document(txt_product);
                if(documentReference!=null){
                    Map<String,Object> cleaners = new HashMap<>();
                    cleaners.put("Product",txt_product);
                    cleaners.put("Price",txt_price);
                    cleaners.put("Quantity",txt_quantity);
                    cleaners.put("Description",txt_description);
                    documentReference.set(cleaners).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Upload.this, "Product Uploaded!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),userHome.class));
                            finish();
                        }
                    });
                }
            }

            else if(dry_msg!=null && dry_msg.equals("dry")){
                DocumentReference documentReference = fstore.collection("Categories").document(dry_msg).collection(UserId).document(txt_product);
                if(documentReference!=null){
                    Map<String,Object> dry = new HashMap<>();
                    dry.put("Product",txt_product);
                    dry.put("Price",txt_price);
                    dry.put("Quantity",txt_quantity);
                    dry.put("Description",txt_description);
                    documentReference.set(dry).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Upload.this, "Product Uploaded", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),userHome.class));
                            finish();
                        }
                    });
                }
            }
            else{
                Log.d("TAG", "are chl jaa");
            }


    }
}