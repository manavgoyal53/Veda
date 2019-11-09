package com.example.veda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
    EditText weight_view;
    EditText height_view;
    Button save;
    private RadioGroup radioGroup;
    TextView textView;
    FirebaseAuth mFireBaseAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference name_change;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mFireBaseAuth = FirebaseAuth.getInstance();
        radioGroup = findViewById(R.id.groupradio);
        FirebaseUser mFireBaseUser = mFireBaseAuth.getCurrentUser();
        btnLogout = findViewById(R.id.button2);
        final String uId = mFireBaseUser.getUid();
        save = findViewById(R.id.button3);
        textView = (TextView)findViewById(R.id.textView3);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        name_change = FirebaseDatabase.getInstance().getReference("UserData/"+uId+"/name");
        mDatabase.keepSynced(true);
        name_change.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String sensor1 = dataSnapshot.getValue(String.class);
                textView.setText("Hello");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton radioButton = (RadioButton)group.findViewById(checkedId);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name_view = findViewById(R.id.editText);
                age_view =  findViewById(R.id.editText4);
                height_view = findViewById(R.id.editText5);
                weight_view = findViewById(R.id.editText6);
                String name = name_view.getText().toString();
                String age = age_view.getText().toString();
                String height = height_view.getText().toString();
                String weight = weight_view.getText().toString();
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton)radioGroup.findViewById(selectedId);
                String gender  = (String) radioButton.getText();
                if(name!=null) {
                    mDatabase.child("UserData").child(uId).child("Name").setValue(name);
                }
                if(age!=null)
                {
                    mDatabase.child("UserData").child(uId).child("Age").setValue(age);
                }
                if(gender!=null)
                {
                    mDatabase.child("UserData").child(uId).child("Gender").setValue(gender);
                }
                if(height!=null){
                    mDatabase.child("UserData").child(uId).child("Height").setValue(height);
                }
                Toast.makeText(getApplicationContext(),"Data Updated",Toast.LENGTH_SHORT).show();
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
