package com.moringaschool.patienttracker;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyClinicsArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mClinics;
    private String[] mTypes;

    public MyClinicsArrayAdapter(Context mContext, int resource, String[] mClinics, String[] mTypes) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mClinics = mClinics;
        this.mTypes = mTypes;
    }
    @Override
    public Object getItem(int position) {
        String clinic = mClinics[position];
        String type = mTypes[position];
        return String.format("%s \nOffers great service: %s", clinic, type);
    }

//    @Override
//    public int getCount() {
//        return mClinics.length;
//    }
}