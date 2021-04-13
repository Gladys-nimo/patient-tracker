package com.moringaschool.patienttracker.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.patienttracker.Constants;
import com.moringaschool.patienttracker.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        private DatabaseReference mSearchedLocationReference;
    public static final String TAG = MainActivity.class.getSimpleName();
//    private FirebaseAuth mAuth;
//    private FirebaseAuth.AuthStateListener mAuthListener;
    @BindView(R.id.findClinicButton) Button mFindClinicButton;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;
    @BindView(R.id.locationEditText)
    EditText mLocationEditText;
//    @BindView(R.id.toolbar) Toolbar mToolBar;
//    @BindView(R.id.savedClinicButton) Button mSavedClinicButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);


//
//       mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//    mEditor = mSharedPreferences.edit();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//       mSearchedLocationReference.addValueEventListener(new ValueEventListener() { //attach listener
//           @Override
//            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
//                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
//                    String location = locationSnapshot.getValue().toString();
//                   Log.d("Locations updated", "location: " + location); //log
//                }
//            }



//        mAuth = FirebaseAuth.getInstance();
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//
//
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
////                  getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
//                    Log.d("database","show an error");
//
//                } else {
//
//                }
//            }
//        };

        mFindClinicButton.setOnClickListener(this);
//        mSavedClinicButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mFindClinicButton) {
            String location = mLocationEditText.getText().toString();

            saveLocationToFireBase(location);
            Intent intent = new Intent(MainActivity.this, ClinicListActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
//        if (v == mSavedClinicButton) {
//            Intent intent = new Intent(MainActivity.this, SavedClinicListActivity.class);
//            startActivity(intent);
//        }
    }
    public void saveLocationToFireBase(String location) {
        mSearchedLocationReference.setValue(location);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        int id = item.getItemId();
//        if(id == R.id.action_logout){
//            logout();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void logout(){
//        FirebaseAuth.getInstance().signOut();
//        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//        finish();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//       mAuth.addAuthStateListener(mAuthListener);
//        Log.d("successful","show success message");
//    }
//  @Override
//  public boolean onCreateOptionsMenu(Menu menu) {
//      MenuInflater inflater = getMenuInflater();
//       inflater.inflate(R.menu.menu_search, menu);
//      return true;
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//
//        }
//    }
}


//
//            @Override
//            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.
//
//            }
//        });










//            if(!(location).equals("")) {
//                addToSharedPreferences(location);
//            }

//
//            public void saveLocationToFirebase(String location) {
//                mSearchedLocationReference.push().setValue(location);
//    }



//








