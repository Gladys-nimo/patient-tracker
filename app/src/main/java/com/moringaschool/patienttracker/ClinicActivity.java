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
import retrofit2.Retrofit;


public class ClinicActivity extends AppCompatActivity {
    private static final String TAG = ClinicActivity.class.getSimpleName();


    @BindView(R.id.locationTextView)
    TextView mLocationTextView;
    @BindView(R.id.listView)
    ListView mListView;

    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic);
        ButterKnife.bind(this);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String clinics = ((TextView) view).getText().toString();
                Toast.makeText(ClinicActivity.this, clinics, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the clinics near: " + location);

        HealthTapApi client = HealthTapClient.getClient();
        Call<HealthTapClinicResponse> call = client.getName(name, " 6b1423a9d0fc6815d2e258a66ae97bf7baba5e2480405af6a87d2ea1daa0a755");
//        Call<List<Attributes>> call = HealthTapApi.getName();
        call.enqueue(new Callback<HealthTapClinicResponse>() {
            @Override
            public void onResponse(Call<HealthTapClinicResponse> call, Response<HealthTapClinicResponse> response) {
                if(response.isSuccessful()){
                    mLocationTextView.setText("code: " + response.code());
                    return;
                }
                List<Attributes> attributes = (List<Attributes>) response.body();


                }

            }

            @Override
            public void onFailure(Call<HealthTapClinicResponse> call, Throwable t) {
            mLocationTextView.setText(t.getMessage());

            }






        });

    }



}