package com.example.veda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EcgActivity extends AppCompatActivity {
    FirebaseAuth mFireBaseAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference ecg_change;
    TextView ecg_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecg);
        mFireBaseAuth = FirebaseAuth.getInstance();
        ecg_value = findViewById(R.id.textView);
        FirebaseUser mFireBaseUser = mFireBaseAuth.getCurrentUser();
        final String uId = mFireBaseUser.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
        ecg_change = FirebaseDatabase.getInstance().getReference("UserData/"+uId+"/ecg");
        ecg_change.addValueEventListener(new ValueEventListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ecg = dataSnapshot.getValue(String.class);
                ecg_value.setTextColor(R.color.green);
                ecg_value.setText(ecg);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
