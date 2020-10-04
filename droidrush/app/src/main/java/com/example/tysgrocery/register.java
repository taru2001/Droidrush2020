package com.example.tysgrocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {

    public EditText name;
    public EditText phone;
    public EditText email;
    public EditText password;
    public EditText confirm_password;
    public EditText user_name;
    public Button register;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        user_name = findViewById(R.id.usr_name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.regemail);
        password = findViewById(R.id.regpassword);
        confirm_password = findViewById(R.id.regcpassword);
        register = findViewById(R.id.regsubmit);

        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_name = name.getText().toString();
                String txt_uname = user_name.getText().toString();
                String txt_phone = phone.getText().toString();
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_cpassword = confirm_password.getText().toString();

                if( TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_name) || TextUtils.isEmpty(txt_uname) ||
                        TextUtils.isEmpty(txt_phone) || TextUtils.isEmpty(txt_password) || TextUtils.isEmpty(txt_cpassword) )
                {
                    Toast.makeText(register.this, "Empty Credentials!", Toast.LENGTH_SHORT).show();
                }

                if(!txt_password.equals(txt_cpassword))
                {
                    Toast.makeText(register.this, "Password and Confirm Password are not same", Toast.LENGTH_SHORT).show();
                }

                else {
//                    Toast.makeText(register.this, "Wait", Toast.LENGTH_SHORT).show();
                    registerUser(txt_email, txt_password);
                }

            }
        });
    }

    public void registerUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(register.this, "Registration failed"+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}