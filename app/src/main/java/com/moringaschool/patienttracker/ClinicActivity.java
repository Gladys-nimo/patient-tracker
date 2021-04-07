package com.moringaschool.patienttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClinicActivity extends AppCompatActivity {
    private String[] clinics= new String[] {"Stanford Hospital", "Sinai Medical Center, Los Angeles",
            "UCSF Medical Center", "UCLA Medical Center", "UC San Diego Medical Center", "Davis Medical Center",
            " Clinics, La Jolla", "john Muir Medical Center", " Keck Medical Center of USC", "Kaiser Permanente",
            "Irvine Medical Center, Orange", "Downey Medical Center", "Medical Center, Concord",
            "California Pacific Medical Center", "San Francisco Medical Center"};
    private String[] types = new String[] {"Acute care", "Community (General)", "Psychiatric Hospital", "Rehabilitation Hospital", "Teaching Hospital", "Children’s hospitals", "Research hospitals", "Psychiatric hospitals", "women's hospital", "Government Hospitals" };

   @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.listView) ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic);
        ButterKnife.bind(this);

        MyClinicsArrayAdapter adapter = new MyClinicsArrayAdapter(this, android.R.layout.simple_list_item_1,clinics, types);
        mListView.setAdapter(adapter);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the clinics near: " + location);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String clinics = ((TextView) view).getText().toString();
                Toast.makeText(ClinicActivity.this, clinics, Toast.LENGTH_LONG).show();
            }
            });
        }
    }


