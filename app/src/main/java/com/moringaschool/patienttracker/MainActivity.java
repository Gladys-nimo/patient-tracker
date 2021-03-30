package com.moringaschool.patienttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.findHospitalButton) Button mFindHospitalButton;
    @BindView(R.id.location) EditText mLocation;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFindHospitalButton.setOnClickListener(this);
        }

            @Override
            public void onClick(View v) {
                if (v == mFindHospitalButton) {
                    String location = mLocation.getText().toString();
                    Intent intent = new Intent(MainActivity.this, HospitalActivity.class);

                    intent.putExtra("location", location);
                    startActivity(intent);
                }
            }
}


