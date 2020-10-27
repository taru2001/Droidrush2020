package com.example.tysgrocery;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class userHome extends AppCompatActivity {

    private Button logout;
    private Button profile;
    ImageButton bev,dairy,bakery,fruits,cleaners,dry;
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        logout = findViewById(R.id.logout);
        profile = findViewById(R.id.profile);
        bev = findViewById(R.id.Beverage);
        dairy = findViewById(R.id.dairy);
        bakery = findViewById(R.id.bakery);
        fruits = findViewById(R.id.fruits);
        cleaners=findViewById(R.id.cleaners);
        dry = findViewById(R.id.dry);

        actionBar = getActionBar();
        if (actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

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

        bev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(userHome.this, "Beverages", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Beverages.class));
                finish();
            }
        });

        dairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(userHome.this, "Dairy Products", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Dairy.class));
                finish();
            }
        });

        bakery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(userHome.this, "Bakery", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Bakery.class));
                finish();
            }
        });

        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(userHome.this, "Fruits & Vegetables", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),FandV.class));
                finish();
            }
        });

        cleaners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(userHome.this, "Cleaners", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Cleaners.class));
                finish();
            }
        });

        dry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(userHome.this, "Dry Products", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),dry.class));
                finish();
            }
        });



    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}