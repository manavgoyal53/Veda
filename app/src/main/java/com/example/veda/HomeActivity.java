package com.example.veda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.veda.UserData;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    Button btnLogout;
    EditText name_view;
    EditText age_view;
    Button save;
    TextView textView;
    FirebaseAuth mFireBaseAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference sensor_update1;
    private DatabaseReference name_update;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mFireBaseAuth = FirebaseAuth.getInstance();

        FirebaseUser mFireBaseUser = mFireBaseAuth.getCurrentUser();
        btnLogout = findViewById(R.id.button2);
        final String uId = mFireBaseUser.getUid();
        save = findViewById(R.id.button3);
        textView = findViewById(R.id.textView3);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        name_update = mDatabase.child("UserDta").child(uId).child("name");
        mDatabase.keepSynced(true);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name_view = findViewById(R.id.editText);
                age_view =  findViewById(R.id.editText4);
                String id = mDatabase.push().getKey();
                String name = name_view.getText().toString();
                String age = age_view.getText().toString();
                UserData data= new UserData(name,age);
                mDatabase.child("UserData").child(uId).child("name").setValue(name);
                Toast.makeText(getApplicationContext(),"Data Added",Toast.LENGTH_SHORT).show();
            }
        });
        name_update.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String change = dataSnapshot.getValue(String.class);
                //String ch_name = change.getName();
                //textView.setText(ch_name);
                textView.setText(change);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intoMain = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intoMain);
            }
        });


    }
}
