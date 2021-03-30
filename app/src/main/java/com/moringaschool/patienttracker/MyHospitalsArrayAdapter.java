package com.moringaschool.patienttracker;

import android.content.Context;
import android.widget.ArrayAdapter;


public class MyHospitalsArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mHospitals;
    private String[] mLocations;

    public MyHospitalsArrayAdapter(Context mContext, int resource, String[] mHospitals, String[] mLocations) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mHospitals = mHospitals;
        this.mLocations = mLocations;
    }

    @Override
    public Object getItem(int position) {
      String hospital = mHospitals[position];
      String location = mLocations[position];
       return String.format("%s \nTreats very well: %s", hospital, location);
    }

    @Override
    public int getCount() {
        return mHospitals.length;
    }
}
