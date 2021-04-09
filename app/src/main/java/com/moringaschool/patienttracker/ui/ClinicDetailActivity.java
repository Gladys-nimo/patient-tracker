package com.moringaschool.patienttracker.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.moringaschool.patienttracker.R;
import com.moringaschool.patienttracker.adapters.ClinicPagerAdapter;
import com.moringaschool.patienttracker.models.Business;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClinicDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private ClinicPagerAdapter adapterViewPager;
    List<Business> mClinics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_detail);
        ButterKnife.bind(this);


        mClinics = Parcels.unwrap(getIntent().getParcelableExtra("clinics"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new ClinicPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mClinics);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
    }
