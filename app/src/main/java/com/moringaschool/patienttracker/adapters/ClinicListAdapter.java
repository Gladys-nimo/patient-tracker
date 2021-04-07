package com.moringaschool.patienttracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.patienttracker.R;
import com.moringaschool.patienttracker.models.Business;

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

    @NonNull
    @Override
    public ClinicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clinic_list_item, parent, false);
        ClinicViewHolder viewHolder = new ClinicViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClinicViewHolder holder, int position) {
  holder.bindClinic(mClinics.get(position));
    }

    @Override
    public int getItemCount() {
        return mClinics.size();
    }

    public class ClinicViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.clinicImageView) ImageView mRestaurantImageView;
        @BindView(R.id.clinicNameTextView) TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;

        public ClinicViewHolder(View itemView) {
           super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindClinic(Business clinic) {
            mNameTextView.setText(clinic.getName());
            mCategoryTextView.setText(clinic.getCategories().get(0).getTitle());
            mRatingTextView.setText("Rating: " +clinic.getRating() + "/5");
        }
    }
}
