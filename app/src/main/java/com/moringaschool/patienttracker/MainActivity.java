package com.moringaschool.patienttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         Button button = (Button) findViewById(R.id.addPatientButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            Toast.makeText(MainActivity.this, "Add patient", Toast.LENGTH_LONG).show();
            }
        });

    }
//
//    private class onClickListener implements View.OnClickListener {
//    }
}