package com.moringaschool.patienttracker.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.patienttracker.Constants;
import com.moringaschool.patienttracker.R;
import com.moringaschool.patienttracker.models.Business;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FirebaseClinicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseClinicViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }
    public void bindClinic(Business clinic) {
        ImageView clinicImageView = (ImageView) mView.findViewById(R.id.clinicImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.clinicNameTextView);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);

        Picasso.get().load(clinic.getImageUrl()).into(clinicImageView);

        nameTextView.setText(clinic.getName());
        categoryTextView.setText(clinic.getCategories().get(0).getTitle());
        ratingTextView.setText("Rating: " + clinic.getRating() + "/5");
    }


    @Override
    public void onClick(View view) {
        final ArrayList<Business> clinics =  new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CLINICS);
    }
}
