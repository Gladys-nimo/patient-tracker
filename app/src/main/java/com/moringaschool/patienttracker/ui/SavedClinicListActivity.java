package com.moringaschool.patienttracker.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.patienttracker.Constants;
import com.moringaschool.patienttracker.R;
import com.moringaschool.patienttracker.adapters.FirebaseClinicViewHolder;
import com.moringaschool.patienttracker.models.Business;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedClinicListActivity extends AppCompatActivity {
    private DatabaseReference mClinicReference;
    private FirebaseRecyclerAdapter<Business, FirebaseClinicViewHolder> mFirebaseAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView)
    TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic);
        ButterKnife.bind(this);


            mClinicReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CLINICS);
            setUpFirebaseAdapter();
            hideProgressBar();
            showClinics();
        }

        private void setUpFirebaseAdapter () {
            FirebaseRecyclerOptions<Business> options =
                    new FirebaseRecyclerOptions.Builder<Business>()
                            .setQuery(mClinicReference, Business.class)
                            .build();

            mFirebaseAdapter = new FirebaseRecyclerAdapter<Business, FirebaseClinicViewHolder>(options) {
                @Override
                protected void onBindViewHolder(@NonNull FirebaseClinicViewHolder firebaseClinicViewHolder, int position, @NonNull Business clinic) {
                    firebaseClinicViewHolder.bindClinic(clinic);
                }

                @NonNull
                @Override
                public FirebaseClinicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clinic_list_item_drag, parent, false);
                    return new FirebaseClinicViewHolder(view);
                }
            };

            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mFirebaseAdapter);
        }

        @Override
        protected void onStart () {
            super.onStart();
            mFirebaseAdapter.startListening();
        }

        @Override
        protected void onStop() {
            super.onStop();
            if (mFirebaseAdapter != null) {
                mFirebaseAdapter.stopListening();
            }
        }

        private void showClinics () {
            mRecyclerView.setVisibility(View.VISIBLE);
        }

        private void hideProgressBar () {
            mProgressBar.setVisibility(View.GONE);
        }
    }

