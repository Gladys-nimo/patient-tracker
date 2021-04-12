package com.moringaschool.patienttracker.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
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

    private static final String TAG = ClinicListActivity.class.getSimpleName();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;
    private ClinicListAdapter mAdapter;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.errorTextView) TextView mErrorTextView;

public List<Business> clinics;


    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_clinic);
    ButterKnife.bind(this);
//    Log.d("Shared Pref Location", mRecentAddress);
    mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
    if(mRecentAddress != null) {
        fetchClinics(mRecentAddress);

    }

}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String location) {
                addToSharedPreferences(location);
                fetchClinics(location);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String location) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }


    private void fetchClinics(String location) {
        YelpApi client = YelpClient.getClient();
        Call<YelpBusinessesSearchResponse> call = client.getClinics(location, "clinics");
        call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
            @Override
            public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {

                hideProgressBar();

                if (response.isSuccessful()) {
                    clinics = response.body().getBusinesses();
                    mAdapter = new ClinicListAdapter(ClinicListActivity.this, clinics);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ClinicListActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showClinics();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                hideProgressBar();
                showFailureMessage();
            }

        });
    }
}