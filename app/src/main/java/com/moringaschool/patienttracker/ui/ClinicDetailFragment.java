package com.moringaschool.patienttracker.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.patienttracker.Constants;
import com.moringaschool.patienttracker.R;
import com.moringaschool.patienttracker.models.Business;
import com.moringaschool.patienttracker.models.Category;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClinicDetailFragment extends Fragment implements View.OnClickListener {

        @BindView(R.id.clinicImageView) ImageView mImageLabel;
    @BindView(R.id.clinicNameTextView) TextView mNameLabel;
        @BindView(R.id.clinicTypeTextView) TextView mCategoriesLabel;
    @BindView(R.id.ratingTextView) TextView mRatingLabel;
    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.addressTextView) TextView mAddressLabel;
    @BindView(R.id.saveClinicButton) TextView mSaveClinicButton;

    private Business mClinic;

    public ClinicDetailFragment() {

    }


    public static ClinicDetailFragment newInstance(Business clinic) {
        ClinicDetailFragment clinicDetailFragment = new ClinicDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("clinic", Parcels.wrap(clinic));
        clinicDetailFragment.setArguments(args);
        return clinicDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mClinic = Parcels.unwrap(getArguments().getParcelable("clinic"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_clinic_detail, container, false);
        ButterKnife.bind(this, view);
        Picasso.get().load(mClinic.getImageUrl()).into(mImageLabel);


        List<String> categories = new ArrayList<>();

        for (Category category : mClinic.getCategories()) {
            categories.add(category.getTitle());
        }
//      //

        mNameLabel.setText(mClinic.getName());
       mCategoriesLabel.setText(android.text.TextUtils.join(", ", categories));
       mRatingLabel.setText(Double.toString(mClinic.getRating()) + "/5");
      mPhoneLabel.setText(mClinic.getPhone());
        mAddressLabel.setText(mClinic.getLocation().toString());

        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);

        mSaveClinicButton.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mClinic.getUrl()));
            startActivity(webIntent);

        }   if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mClinic.getPhone()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + mClinic.getCoordinates().getLatitude()
                    + "," + mClinic.getCoordinates().getLongitude()
                    + "?q=(" + mClinic.getName() + ")"));
            startActivity(mapIntent);
        }
        if (v == mSaveClinicButton) {
            DatabaseReference restaurantRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_CLINICS);
            restaurantRef.push().setValue(mClinic);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
    }


//    @Override
//    public void onClick(View v) {
//        if (v == mWebsiteLabel) {
//            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mClinic.getUrl()));
//            startActivity(webIntent);
//        }
//        if (v == mPhoneLabel) {
//            Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mClinic.getPhone()));
//            startActivity(phoneIntent);
//        }
//        if (v == mAddressLabel) {
//            Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + mClinic.getCoordinates().getLatitude()
////                    + "," + mClinic.getCoordinates().getLongitude()
//                    + "?q=(" + mClinic.getName() + ")"));
//            startActivity(mapIntent);
//        }
//        if (v == mSavedClinicButton) {
////            Log.d("saved clinics","show clinics saved");
//           DatabaseReference clinicRef = FirebaseDatabase
//                 .getInstance()
//                  .getReference(Constants.FIREBASE_CHILD_CLINICS);
//            clinicRef.push().setValue(mClinic);
//            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
//
//        }
//    }
