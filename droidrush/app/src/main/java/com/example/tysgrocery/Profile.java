package com.example.tysgrocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class Profile extends AppCompatActivity {

    TextView name,usrname,phone,email,verify;
    Button logout, resendCode;
    FirebaseAuth auth;
    FirebaseFirestore fstore;
    String UserId;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        actionBar= getActionBar();
        if (actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.name);
        usrname = findViewById(R.id.user_name);
        phone = findViewById(R.id.price);
        email = findViewById(R.id.email);
        logout = findViewById(R.id.logout);
        verify = findViewById(R.id.verify);
        resendCode = findViewById(R.id.resendMail);

        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        FirebaseUser firebaseUser = auth.getCurrentUser();

        if (!firebaseUser.isEmailVerified()){
            resendCode.setVisibility(View.VISIBLE);
            verify.setVisibility(View.VISIBLE);

            resendCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseUser firebaseUser1 = auth.getCurrentUser();
                    firebaseUser1.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Profile.this, "Verification email has been sent", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Log.d("TAG", "onFaliure: email not sent "+ e.getMessage());
                        }
                    });
                }
            });
        }

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

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), userHome.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}