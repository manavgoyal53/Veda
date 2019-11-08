package com.example.veda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText emailId, password;
    Button btnSingIn;
    TextView tvSignUp;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editText2);
        password = findViewById(R.id.editText3);
        btnSingIn = findViewById(R.id.button);
        tvSignUp = findViewById(R.id.textView);
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFireBaseUser = mFirebaseAuth.getCurrentUser();
                if(mFireBaseUser!=null){
                    Toast.makeText(LoginActivity.this,"You are Logegd in!",Toast.LENGTH_SHORT).show();
                    Intent i  = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(LoginActivity.this,"Please Log In!",Toast.LENGTH_SHORT).show();
                }
            }
        };
        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String email = emailId.getText().toString();
                    String pwd = password.getText().toString();
                    if(email.isEmpty()){
                        emailId.setError("Please enter the Email");
                        emailId.requestFocus();
                    }
                    else if(pwd.isEmpty()){
                        password.setError("Please enter the  Password");
                        password.requestFocus();
                    }
                    else if(email.isEmpty() && pwd.isEmpty() ){
                        Toast.makeText(LoginActivity.this,"Fields are Empty",Toast.LENGTH_SHORT).show();
                    }
                    else if(!(email.isEmpty() && pwd.isEmpty())){
                        mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this,"Login Error! Please Try Again.",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Intent toHome = new Intent(LoginActivity.this,HomeActivity.class);
                                    startActivity((toHome));
                                }
                            }
                        });

                    }
                    else{
                        Toast.makeText(LoginActivity.this,"Error Occured",Toast.LENGTH_SHORT).show();
                    }

            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignup = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intSignup);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
