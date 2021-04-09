package com.moringaschool.patienttracker.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.patienttracker.R;
import com.moringaschool.patienttracker.models.Business;
import com.moringaschool.patienttracker.ui.ClinicDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClinicListAdapter extends RecyclerView.Adapter<ClinicListAdapter.ClinicViewHolder> {
    private List<Business> mClinics;
    private Context mContext;


    public ClinicListAdapter(Context context, List<Business> clinics) {
        mContext = context;
        mClinics = clinics;
    }


    @Override
    public ClinicListAdapter.ClinicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clinic_list_item, parent, false);
      ClinicViewHolder viewHolder = new ClinicViewHolder(view);
        return viewHolder;
    }

//    @Override
//    public void onBindViewHolder(@NonNull ClinicViewHolder holder, int position) {
//
//    }

    @Override
    public void onBindViewHolder(ClinicListAdapter.ClinicViewHolder holder, int position) {
  holder.bindClinic(mClinics.get(position));
    }

    @Override
    public int getItemCount() {
        return mClinics.size();
    }

    public class ClinicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.clinicImageView) ImageView mClinicImageView;
        @BindView(R.id.clinicNameTextView) TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;

        public ClinicViewHolder(View itemView) {
           super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindClinic(Business clinic) {
//            Picasso.get().load(clinic.getImageUrl()).into(mClinicImageView);
            mNameTextView.setText(clinic.getName());
            mCategoryTextView.setText(clinic.getCategories().get(0).getTitle());
            mRatingTextView.setText("Rating: " + clinic.getRating() + "/5");
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, ClinicDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("restaurants", Parcels.wrap(mClinics));
            mContext.startActivity(intent);
        }
        }
    }

