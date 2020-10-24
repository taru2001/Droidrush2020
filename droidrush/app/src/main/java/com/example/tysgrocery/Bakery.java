package com.example.tysgrocery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Bakery extends AppCompatActivity {

    public static final String MSGS = "com.example.tysgrocery.BAKERY";

    Button logout,upload;
    FirebaseFirestore fstore;
    FirebaseAuth auth;
    String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakery);

        logout = findViewById(R.id.logout);
        upload = findViewById(R.id.upload);

        fstore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

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
    }
}