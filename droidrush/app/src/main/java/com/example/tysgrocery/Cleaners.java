package com.example.tysgrocery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Cleaners extends AppCompatActivity {

    public static final String MSG = "com.example.tysgrocery.CLEANERS";

    Button logout,upload;
    FirebaseAuth auth;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaners);

        logout = findViewById(R.id.logout);
        upload = findViewById(R.id.upload);

        auth=FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Cleaners.this, "Logged Out!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Cleaners.this,MainActivity.class));
                finish();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cleaners.this,Upload.class);
                String message = "cleaners";
                intent.putExtra(MSG,message);
                startActivity(intent);
                finish();
            }
        });
    }
}