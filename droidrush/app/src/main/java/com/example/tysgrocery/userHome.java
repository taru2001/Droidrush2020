package com.example.tysgrocery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class userHome extends AppCompatActivity {

    private Button logout;
    private Button profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        logout = findViewById(R.id.logout);
        profile = findViewById(R.id.profile);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(userHome.this, "Logged Out!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(userHome.this,MainActivity.class));
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(userHome.this, "Profile", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(userHome.this,Profile.class));
                startActivity(new Intent(getApplicationContext(),Profile.class));
                finish();
            }
        });
    }
}