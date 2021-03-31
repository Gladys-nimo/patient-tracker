package com.moringaschool.patienttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PatientActivity extends AppCompatActivity {
    String selectedCondition;
    @BindView(R.id.problem) RadioButton problem;
    @BindView(R.id.describe) EditText describe;
    @BindView(R.id.checkups) RadioButton checkups;
    @BindView(R.id.switching) RadioButton switching;
    @BindView(R.id.submitButton) Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        ButterKnife.bind(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (problem.isChecked()) {
                    selectedCondition = describe.getText().toString();
                } else if (checkups.isChecked()) {
                    selectedCondition = checkups.getText().toString();
                } else if (switching.isChecked()) {
                    selectedCondition = switching.getText().toString();
                }
                Toast.makeText(getApplicationContext(), selectedCondition, Toast.LENGTH_LONG).show();
            }
        });


    }
}