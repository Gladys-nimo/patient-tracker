package com.moringaschool.patienttracker.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.patienttracker.MyClinicsArrayAdapter;
import com.moringaschool.patienttracker.R;
import com.moringaschool.patienttracker.adapters.ClinicListAdapter;
import com.moringaschool.patienttracker.network.YelpApi;
import com.moringaschool.patienttracker.network.YelpClient;
import com.moringaschool.patienttracker.models.Business;
import com.moringaschool.patienttracker.models.Category;
import com.moringaschool.patienttracker.models.YelpBusinessesSearchResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ClinicActivity extends AppCompatActivity {
    private static final String TAG = ClinicActivity.class.getSimpleName();



    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.errorTextView) TextView mErrorTextView;


private ClinicListAdapter mAdapter;

public List<Business> clinics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic);
        ButterKnife.bind(this);
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String clinics = ((TextView) view).getText().toString();
//                Toast.makeText(ClinicActivity.this, clinics, Toast.LENGTH_LONG).show();
//            }
//        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        YelpApi client = YelpClient.getClient();

        Call<YelpBusinessesSearchResponse> call = client.getClinics(location, "clinics");

        call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
            @Override
            public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    clinics = response.body().getBusinesses();
                  mAdapter = new ClinicListAdapter(ClinicActivity.this, clinics);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ClinicActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);


                    showClinics();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {
                Log.e("Error message", "onFailure:",t);
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
        mRecyclerView.setVisibility(View.GONE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}