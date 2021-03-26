package com.moringaschool.patienttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

public class PatientActivity extends AppCompatActivity {
    private TextView mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        mPhone = (TextView) findViewById(R.id.phone);
        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        mPhone.setText("Here is your number:" + phone);
    }
}