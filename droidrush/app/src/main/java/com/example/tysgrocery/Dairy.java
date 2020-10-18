package com.example.tysgrocery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Dairy extends AppCompatActivity {

    public static final String MSGS = "com.example.tysgrocery.DAIRY";

    Button logout,upload;
    FirebaseFirestore fstore;
    FirebaseAuth auth;

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
        final Intent intent = new Intent(this,Upload.class);
        String message = "dairy";
        intent.putExtra(MSGS,message);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });
    }
}