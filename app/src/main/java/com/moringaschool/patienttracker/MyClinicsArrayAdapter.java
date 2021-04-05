package com.moringaschool.patienttracker;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyClinicsArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mClinics;
    private String[] mCuisines;

    public MyClinicsArrayAdapter(Context mContext, int resource, String[] mRestaurants, String[] mCuisines) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mClinics = mRestaurants;
        this.mCuisines = mCuisines;
    }
    @Override
    public Object getItem(int position) {
        String clinic = mClinics[position];
        String cuisine = mCuisines[position];
        return String.format("%s \nServes great: %s", clinic, cuisine);
    }

    @Override
    public int getCount() {
        return mClinics.length;
    }
}