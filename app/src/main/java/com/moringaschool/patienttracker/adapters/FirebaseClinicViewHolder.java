package com.moringaschool.patienttracker.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.patienttracker.Constants;
import com.moringaschool.patienttracker.R;
import com.moringaschool.patienttracker.models.Business;
import com.moringaschool.patienttracker.ui.ClinicDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

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
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    clinics.add(snapshot.getValue(Business.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, ClinicDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("clinics", Parcels.wrap(clinics));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
