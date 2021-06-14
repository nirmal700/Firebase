package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Patient_data extends AppCompatActivity {
    EditText mPid,mEmail,mPname,mCnumber,mDob,mId;
    Button mSave, mDobbutton;
    RadioGroup mBgroup,mBtype,mGender;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_data);
        mDatabase=FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference();
        mPid=findViewById(R.id.patientid);
        mEmail=findViewById(R.id.emailid);
        mPname=findViewById(R.id.patientname);
        mCnumber=findViewById(R.id.contactid);
        mDob=findViewById(R.id.dobid);
        mId=findViewById(R.id.nid);
        mBgroup=findViewById(R.id.bgid);
        mBtype=findViewById(R.id.btid);
        mGender=findViewById((R.id.genderid));
        mSave=findViewById(R.id.saveid);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String mpid,memail,mpname,mcnumber,mdob,mid;
               mpid=mPid.getText().toString();
               memail=mEmail.getText().toString();
               mpname=mPname.getText().toString();
               mcnumber=mCnumber.getText().toString();
               mdob=mDob.getText().toString();
               mid=mId.getText().toString();
                mRef.child(mpid).child("Patient ID").setValue(mpid);
                mRef.child(mpid).child("Patient E-mail").setValue(memail);
                mRef.child(mpid).child("Patient Name").setValue(mpname);
                mRef.child(mpid).child("Patient Contact Number").setValue(mcnumber)
                ;mRef.child(mpid).child("Patient DOB").setValue(mdob);
                mRef.child(mpid).child("Patient I-D Card").setValue(mid);
                Toast.makeText(Patient_data.this, "The data has been pushed Successfully", Toast.LENGTH_SHORT).show();
                
            }
        });
    }
}