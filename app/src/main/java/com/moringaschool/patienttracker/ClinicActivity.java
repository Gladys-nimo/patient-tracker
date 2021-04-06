package com.moringaschool.patienttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClinicActivity extends AppCompatActivity {
    private static final String TAG = ClinicActivity.class.getSimpleName();


    @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.listView) ListView mListView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic);
        ButterKnife.bind(this);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String clinics = ((TextView)view).getText().toString();
                Toast.makeText(ClinicActivity.this, clinics, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the clinics near: " + location);

     HealthTapApi client = HealthTapClient.getClient();

    Call<HealthTapClinicResponse> call = client.getClinics(location, "clinics");

    call.enqueue(new Callback<HealthTapClinicResponse>() {
        @Override
        public void onResponse(Call<HealthTapClinicResponse> call, Response<HealthTapClinicResponse> response) {
            hideProgressBar();
            if (response.isSuccessful()) {
                List<Attributes> clinicList = (List<Attributes>) response.body().getData();
                String[] clinics = new String[clinicList.size()];
                String[] data_1 = new String[clinicList.size()];

                for (int i = 0; i < clinics.length; i++){
                    clinics[i] =clinicList.get(i).getName();
                }

                for (int i = 0; i < data_1.length; i++) {
                  String data__1 = clinicList.get(i).getName();
                  data_1[i] = String.valueOf(data_1.getClass());

                }

                ArrayAdapter adapter = new MyClinicsArrayAdapter(ClinicActivity.this, android.R.layout.simple_list_item_1, clinics, data_1);
                mListView.setAdapter(adapter);

               showClinics();
            } else {
                showUnsuccessfulMessage();
            }
        }

        @Override
        public void onFailure(Call<HealthTapClinicResponse> call, Throwable t) {
            Log.e("Error Message", "onFailure: ",t );
            hideProgressBar();
            showFailureMessage();
        }
    });
}

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showClinics() {
        mListView.setVisibility(View.VISIBLE);
        mLocationTextView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}