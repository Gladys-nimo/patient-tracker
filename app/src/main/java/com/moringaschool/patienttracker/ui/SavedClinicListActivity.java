package com.moringaschool.patienttracker.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.patienttracker.Constants;
import com.moringaschool.patienttracker.R;
import com.moringaschool.patienttracker.adapters.FireBaseClinicListAdapter;
import com.moringaschool.patienttracker.adapters.FirebaseClinicViewHolder;
import com.moringaschool.patienttracker.models.Business;
import com.moringaschool.patienttracker.util.OnStartDragListener;
import com.moringaschool.patienttracker.util.SimpleItemTouchHelperCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedClinicListActivity extends AppCompatActivity {
    private DatabaseReference mClinicReference;
    private FireBaseClinicListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic);
        ButterKnife.bind(this);



            setUpFirebaseAdapter();
            hideProgressBar();
            showClinics();
        }

        private void setUpFirebaseAdapter () {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            mClinicReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CLINICS).child(uid);
            FirebaseRecyclerOptions<Business> options =
                    new FirebaseRecyclerOptions.Builder<Business>()
                            .setQuery(mClinicReference, Business.class)
                            .build();
            mFirebaseAdapter = new FireBaseClinicListAdapter(options, mClinicReference, (OnStartDragListener) this, this);

            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mFirebaseAdapter);
            mRecyclerView.setHasFixedSize(true);
            ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
            mItemTouchHelper = new ItemTouchHelper(callback);
            mItemTouchHelper.attachToRecyclerView(mRecyclerView);
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

