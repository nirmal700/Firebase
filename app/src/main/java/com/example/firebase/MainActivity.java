package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView mOutputText,mOutputTex2;
    Button mButton,pButton,nbutton;
    EditText mInputText,mInputText2,mId;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton=findViewById(R.id.button);
        mOutputText=findViewById(R.id.TextView);
        mInputText=findViewById(R.id.TextField);
        pButton=findViewById(R.id.button2);
        mOutputTex2=findViewById(R.id.textView3);
        mInputText2=findViewById(R.id.EditText2);
        nbutton=findViewById((R.id.nextbutton));
        mId=findViewById(R.id.editTextTextPersonName3);
        mDatabase=FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data=mInputText.getText().toString();
                String age = mInputText2.getText().toString();
                String id = mId.getText().toString();
                mRef.child(id).child("Patient Name").setValue(data);
                mRef.child(id).child("Age").setValue(age);
                Toast.makeText(MainActivity.this, "Data has been pushed Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        pButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.child("1223").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) {
                        Map<String,Object> data = (Map<String, Object>)snapshot.getValue();
                        String name = (String) data.get("Patient Name");
                           mOutputText.setText(name);
                         String age = (String)data.get("Age");
                         mOutputTex2.setText(age);

                        Log.d("TAG","On data change : Name :" + data.get("Patient Name"));
                        Log.d("TAG","On data change : Age :" + data.get("Age"));
                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {

                    }
                });
            }
        });
        nbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Patient_data.class);
                startActivity(intent);
            }
        });
    }
}