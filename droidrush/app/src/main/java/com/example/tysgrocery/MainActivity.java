package com.example.tysgrocery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button register;
    private Button login;
    String UserId="aa";
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.register);
        login = findViewById(R.id.login);

        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null)
        {
            UserId = auth.getCurrentUser().getUid();
            startActivity(new Intent(MainActivity.this,userHome.class));
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,register.class));
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserId=="aa")
                {
                    startActivity(new Intent(MainActivity.this, login.class ));
                    finish();
                }
                else {
                    startActivity(new Intent(MainActivity.this, userHome.class));
                    finish();
                }

            }
        });
    }
}