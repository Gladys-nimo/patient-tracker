package com.moringaschool.patienttracker.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    public ImageView mClinicImageView;

    public FirebaseClinicViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }
    public void bindClinic(Business clinic) {
        ImageView mClinicImageView =  mView.findViewById(R.id.clinicImageView);
        TextView nameTextView =  mView.findViewById(R.id.clinicNameTextView);
        TextView categoryTextView = mView.findViewById(R.id.categoryTextView);
        TextView ratingTextView =  mView.findViewById(R.id.ratingTextView);

        nameTextView.setText(clinic.getName());
        categoryTextView.setText(clinic.getCategories().get(0).getTitle());
        ratingTextView.setText("Rating: " + clinic.getRating() + "/5");
        Picasso.get().load(clinic.getImageUrl()).into(mClinicImageView);

       }


    @Override
    public void onClick(View view) {
        final ArrayList<Business> clinics =  new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CLINICS).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener()  {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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
