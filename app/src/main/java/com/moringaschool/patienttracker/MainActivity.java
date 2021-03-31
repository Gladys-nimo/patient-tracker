package com.moringaschool.patienttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;



public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button submit;
    @BindView(R.id.editText1)
    EditText name;
    @BindView(R.id.editText2)
    EditText location;
    @BindView(R.id.editText3)
    EditText email;
    @BindView(R.id.editText4)
    EditText date;
    @BindView(R.id.editText5)
    EditText contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (name.getText().toString().isEmpty() || location.getText().toString().isEmpty() || email.getText().toString().isEmpty() || date.getText().toString().isEmpty()
                        || contact.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the Data", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Name -  " + name.getText().toString() + " \n" + "Password -  " + location.getText().toString()
                            + " \n" + "E-Mail -  " + email.getText().toString() + " \n" + "Date -  " + date.getText().toString()
                            + " \n" + "Contact -  " + contact.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(MainActivity.this, PatientActivity.class);
            }
        });
    }
}

