package com.example.tysgrocery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class Dairy extends AppCompatActivity {

    public static final String MSGS = "com.example.tysgrocery.DAIRY";

    Button logout,upload;
    FirebaseFirestore fstore;
    FirebaseAuth auth;
    String UserId;
    ListView dairylist;
//    String [] product;
    String x;
    ArrayList<String> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dairy);

        logout = findViewById(R.id.logout);
        upload = findViewById(R.id.upload);

        fstore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Dairy.this, "Logged Out!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Dairy.this,MainActivity.class));
                finish();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dairy.this,Upload.class);
                String message = "dairy";
                intent.putExtra(MSGS,message);
                startActivity(intent);
                finish();
            }
        });

//         product = new String[1];
//         product[0]="aa";
          products = new ArrayList<>();

        UserId = auth.getCurrentUser().getUid();
        DocumentReference documentReference = fstore.collection("Categories").document("dairy").collection(UserId).document("milk can");
        documentReference.addSnapshotListener(Dairy.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                 x = documentSnapshot.getString("Product Name");
//                 if(docref!=null)
    
//                     x=docref.toString();

//                 product[0]=x;
                Toast.makeText(Dairy.this, x, Toast.LENGTH_SHORT).show();
                if(x!=null)
                {
                    products.add(x);
                }
            }
        });
//        if(x!=null)
//        {
//            products.add(x);
//        }

        products.add("Milk Can");

         dairylist = findViewById(R.id.list);

        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,products);
        dairylist.setAdapter(arrayAdapter);

        dairylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Dairy.this, "Milk", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),userHome.class));
                finish();
            }
        });

    }
}