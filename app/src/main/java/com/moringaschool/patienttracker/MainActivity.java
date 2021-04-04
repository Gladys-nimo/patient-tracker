package com.moringaschool.patienttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button mFindClinicButton;
    private EditText mLocationEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLocationEditText = (EditText) findViewById(R.id.locationEditText);
        mFindClinicButton = (Button) findViewById(R.id.findClinicButton);
        mFindClinicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = mLocationEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, ClinicActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });
    }
}

