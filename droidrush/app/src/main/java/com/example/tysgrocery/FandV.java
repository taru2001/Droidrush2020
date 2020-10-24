package com.example.tysgrocery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class FandV extends AppCompatActivity {

    public static final String MSG = "com.example.tysgrocery.BEVERAGES";

    Button logout,upload;
    FirebaseFirestore fstore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fand_v);

        logout = findViewById(R.id.logout);
        upload = findViewById(R.id.upload);

        fstore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(FandV.this, "Logged Out!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FandV.this,MainActivity.class));
                finish();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FandV.this,Upload.class);
                String message = "fruits&veg";
                intent.putExtra(MSG,message);
                startActivity(intent);
                finish();
            }
        });
    }
}