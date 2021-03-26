package com.moringaschool.patienttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private EditText mPhoneHint;
//    private
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPhoneHint = (EditText) findViewById(R.id.phone);

         Button button = (Button) findViewById(R.id.addPatientButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String phone = mPhoneHint.getText().toString();
                Intent intent = new Intent(MainActivity.this, PatientActivity.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
//            Toast.makeText(MainActivity.this, "Add patient", Toast.LENGTH_LONG).show();
            }
        });

    }
//
//    private class onClickListener implements View.OnClickListener {
//    }
}