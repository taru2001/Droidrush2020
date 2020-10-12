package com.example.tysgrocery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class Profile extends AppCompatActivity {

    TextView name,usrname,phone,email;
    Button logout;
    FirebaseAuth auth;
    FirebaseFirestore fstore;
    String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.name);
        usrname = findViewById(R.id.user_name);
        phone = findViewById(R.id.price);
        email = findViewById(R.id.email);
        logout = findViewById(R.id.logout);

        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        UserId = auth.getCurrentUser().getUid();
//        phone.setText("dfdd");

        DocumentReference documentReference = fstore.collection("Users").document(UserId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                name.setText(documentSnapshot.getString("Name"));
                phone.setText(documentSnapshot.getString("Password"));
                usrname.setText(documentSnapshot.getString("User Name"));
                email.setText(documentSnapshot.getString("Email Id"));

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Profile.this, "Logged Out!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });


    }
}