package com.moringaschool.patienttracker.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.patienttracker.Constants;
import com.moringaschool.patienttracker.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //    private DatabaseReference mSearchedLocationReference;
    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.findClinicButton) Button mFindClinicButton;
    @BindView(R.id.appNameTextView)
    TextView mAppNameTextView;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.saveClinicButton) Button mSaveClinicButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mFindClinicButton.setOnClickListener(this);
        mSaveClinicButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mFindClinicButton) {
            Intent intent = new Intent(MainActivity.this, ClinicListActivity.class);
            startActivity(intent);
        }
        if (v == mSaveClinicButton) {
            Intent intent = new Intent(MainActivity.this, SavedClinicListActivity.class);
            startActivity(intent);
        }
    }
}


//        mSearchedLocationReference = FirebaseDatabase
//                .getInstance()
//                .getReference()
//                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);
//        mSearchedLocationReference.addValueEventListener(new ValueEventListener() { //attach listener
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
//                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
//                    String location = locationSnapshot.getValue().toString();
//                    Log.d("Locations updated", "location: " + location); //log
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.
//
//            }
//        });




//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();








//            if(!(location).equals("")) {
//                addToSharedPreferences(location);
//            }

//
//            public void saveLocationToFirebase(String location) {
//                mSearchedLocationReference.push().setValue(location);
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search, menu);
//
//        return true;
//    }

//








