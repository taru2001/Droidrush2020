package com.example.tysgrocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Bakery extends AppCompatActivity implements FirestoreAdapter.OnListItemClick {

    public static final String MSGS = "com.example.tysgrocery.BAKERY";

    Button logout,upload;
    FirebaseFirestore fstore;
    FirebaseAuth auth;
    RecyclerView products;
    String UserId;
    FirestoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakery);

        logout = findViewById(R.id.logout);
        upload = findViewById(R.id.upload);
        products = findViewById(R.id.products);

        fstore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        UserId = auth.getCurrentUser().getUid();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Bakery.this, "Logged Out!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Bakery.this,MainActivity.class));
                finish();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bakery.this,Upload.class);
                String message = "bakery";
                intent.putExtra(MSGS,message);
                startActivity(intent);
                finish();
            }
        });

        Query query = fstore.collection("Categories").document("bakery").collection(UserId);
        FirestoreRecyclerOptions<ProductModel> options = new FirestoreRecyclerOptions.Builder<ProductModel>().
                setQuery(query,ProductModel.class)
                .build();

        adapter = new FirestoreAdapter(options,this);

        if(products!=null)
        {
            products.setHasFixedSize(true);
            products.setLayoutManager(new LinearLayoutManager(this));
            products.setAdapter(adapter);
        }

    }

    @Override
    public void onItemClick(ProductModel a, int position) {
        Log.d("ITEM_CLICK","Clicked an item "+position + " " + a.getProduct());
        Toast.makeText(this, "Item Clicked", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Bakery.this,userHome.class));
        finish();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        public TextView list_name;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            list_name = itemView.findViewById(R.id.product);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}