package com.moringaschool.patienttracker.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.moringaschool.patienttracker.Constants;
import com.moringaschool.patienttracker.R;
import com.moringaschool.patienttracker.adapters.ClinicListAdapter;
import com.moringaschool.patienttracker.network.YelpApi;
import com.moringaschool.patienttracker.network.YelpClient;
import com.moringaschool.patienttracker.models.Business;
import com.moringaschool.patienttracker.models.YelpBusinessesSearchResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ClinicListActivity extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    private String mRecentAddress;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.errorTextView) TextView mErrorTextView;


private ClinicListAdapter mAdapter;

public List<Business> clinics;

protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_clinic);
    ButterKnife.bind(this);

    mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
    Log.d("Shared Pref Location", mRecentAddress);

    Intent intent = getIntent();
    String location = intent.getStringExtra("location");


    YelpApi client = YelpClient.getClient();
    Call<YelpBusinessesSearchResponse> call = client.getClinics(location, "clinics");
    call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
        @Override
        public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {
            if (response.isSuccessful()) {
                clinics = response.body().getBusinesses();
                mAdapter = new ClinicListAdapter(ClinicListActivity.this, clinics);
                mRecyclerView.setAdapter(mAdapter);
                RecyclerView.LayoutManager layoutManager =
                        new LinearLayoutManager(ClinicListActivity.this);
                mRecyclerView.setLayoutManager(layoutManager);
                // informs mRecyclerView that width and height should always be the same.
                mRecyclerView.setHasFixedSize(true);

                showclinics();
            } else {
                showUnsuccessfulMessage();
            }
        }

        @Override
        public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {
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

    private void showclinics() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }



}