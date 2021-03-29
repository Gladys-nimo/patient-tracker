  package com.moringaschool.patienttracker;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    private String [] locations = new String[]{"WestLands","UpperHill","NgongRoad","Embakasi", "Thika Highway"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        ButterKnife.bind(this);


        MyHospitalsArrayAdapter adapter = new MyHospitalsArrayAdapter(this, android.R.layout.simple_list_item_1, hospitals, locations);
        mListView.setAdapter(adapter);

       mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
               String hospital = ((TextView)view).getText().toString();
               Toast.makeText(HospitalActivity.this, hospital, Toast.LENGTH_SHORT).show();
           }
       });
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
       mLocationTextView.setText("Here are the hospitals near:" + location);
        Log.d("HospitalActivity", "in onCreate Method!");




    }
}