package com.example.veda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HeartActivity extends AppCompatActivity {
    FirebaseAuth mFireBaseAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference heart_change;
    TextView heart_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart);
        mFireBaseAuth = FirebaseAuth.getInstance();
        heart_value = findViewById(R.id.textView2);
        FirebaseUser mFireBaseUser = mFireBaseAuth.getCurrentUser();
        final String uId = mFireBaseUser.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
        heart_change = FirebaseDatabase.getInstance().getReference("UserData/"+uId+"/heart");
        heart_change.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String heart = dataSnapshot.getValue(String.class);
                heart_value.setText(heart);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
