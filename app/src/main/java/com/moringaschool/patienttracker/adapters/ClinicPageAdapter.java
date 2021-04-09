package com.moringaschool.patienttracker.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringaschool.patienttracker.models.Business;
import com.moringaschool.patienttracker.ui.ClinicDetailFragment;

import java.util.List;

public class ClinicPageAdapter extends FragmentPagerAdapter {
    private List<Business> mClinics;


    public ClinicPageAdapter(@NonNull FragmentManager fragmentManager, int behavior, List<Business> clinics) {
        super(fragmentManager, behavior);
        mClinics = clinics;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ClinicDetailFragment.newInstance(mClinics.get(position));
    }

    @Override
    public int getCount() {
        return mClinics.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mClinics.get(position).getName();
    }
}
