package com.example.tysgrocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Beverages extends AppCompatActivity implements FirestoreAdapter.OnListItemClick {

    public static final String MSG = "com.example.tysgrocery.BEVERAGES";

    Button logout,upload;
    FirebaseFirestore fstore;
    FirebaseAuth auth;
    RecyclerView products;
    String UserId;
//    FirestoreRecyclerAdapter adapter;
    FirestoreAdapter adapter;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverages);

        actionBar = getActionBar();
        if (actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        logout = findViewById(R.id.logout);
        upload = findViewById(R.id.upload);
        products = findViewById(R.id.products);

        fstore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        UserId = auth.getCurrentUser().getUid();

        Query query = fstore.collection("Categories").document("beverages").collection(UserId);
//        Query query = fstore.collection("Users");
        FirestoreRecyclerOptions<ProductModel> options = new FirestoreRecyclerOptions.Builder<ProductModel>().
                setQuery(query,ProductModel.class)
                .build();

//        adapter = new FirestoreRecyclerAdapter<ProductModel, ProductViewHolder>(options) {
//            @NonNull
//            @Override
//            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product,parent,false);
//                return new ProductViewHolder(view);
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull ProductModel model) {
//                holder.list_name.setText(model.getProduct());
//
//            }
//        };

        adapter = new FirestoreAdapter(options,this);



//         flist.setHasFixedSize(true);
        if(products!=null)
        {
            products.setHasFixedSize(true);
            products.setLayoutManager(new LinearLayoutManager(this));
            products.setAdapter(adapter);
        }



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Beverages.this, "Logged Out!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Beverages.this,MainActivity.class));
                finish();
            }
        });


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beverages.this,Upload.class);
                String message = "beverages";
                intent.putExtra(MSG,message);
                startActivity(intent);
                finish();
            }
        });

        
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), userHome.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    @Override
    public void onItemClick(ProductModel a, int position) {
        Log.d("ITEM_CLICK","Clicked an item "+position + " " + a.getProduct());
        Toast.makeText(this, "Item Clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Beverages.this,Product.class);
        Bundle bundle = new Bundle();
        bundle.putString("product",a.getProduct());
        bundle.putString("price",a.getPrice());
        bundle.putString("quantity",a.getQuantity());
        bundle.putString("description",a.getDescription());
        intent.putExtras(bundle);
        startActivity(intent);
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