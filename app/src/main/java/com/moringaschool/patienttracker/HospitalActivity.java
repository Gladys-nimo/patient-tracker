  package com.moringaschool.patienttracker;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


  public class HospitalActivity extends AppCompatActivity {
    @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.listView) ListView mListView;


    private String[] hospitals = new String[]{"Mater Hospital", "Chiromo lane Hospital"
    ,"Bliss Medical Center", "MediHeal Group of hospitals", "SavannahHealthCare","Getrude", "Agakhan",
            "Kenyatta", "Nairobi women's", "Nairobi west", "caren hospital","Bristol Park", "Balozi hopsital", "WentWorth","Neema","PlainsView","St Mary Health"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        ButterKnife.bind(this);



        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
       mLocationTextView.setText("Here are the hospitals near:" + location);
        Log.d("HospitalActivity", "in onCreate Method!");




    }
}