package com.moringaschool.patienttracker.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringaschool.patienttracker.models.Business;
import com.moringaschool.patienttracker.ui.ClinicDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class ClinicPagerAdapter extends FragmentPagerAdapter {
    private List<Business> mClinics;


    public ClinicPagerAdapter(@NonNull FragmentManager fragmentManager, int behavior, List<Business> clinics) {
        super(fragmentManager, behavior);
        mClinics = clinics;
    }


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
