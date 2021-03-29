package com.moringaschool.patienttracker;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;


public class HospitalActivity extends AppCompatActivity {
    private TextView mhospitalTextView;
    private ListView mListView;
    private String[] hospitals = new String[]{"Getrude", "Agakhan",
            "Kenyatta", "Nairobi women's", "Nairobi west", "caren hospital"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        mhospitalTextView = (TextView) findViewById(R.id.hospitalTextView);
        mListView = (ListView) findViewById(R.id.listView);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mhospitalTextView.setText("Here are the hospitals near:" + location);




    }
}