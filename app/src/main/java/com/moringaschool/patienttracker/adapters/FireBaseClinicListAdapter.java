package com.moringaschool.patienttracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.moringaschool.patienttracker.R;
import com.moringaschool.patienttracker.models.Business;
import com.moringaschool.patienttracker.util.OnStartDragListener;

public class FireBaseClinicListAdapter extends FirebaseRecyclerAdapter<Business, FirebaseClinicViewHolder> implements ItemTouchHelper {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FireBaseClinicListAdapter(FirebaseRecyclerOptions<Business> options,
                                     DatabaseReference ref,
                                     OnStartDragListener onStartDragListener,
                                     Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull FirebaseClinicViewHolder firebaseClinicViewHolder, int position, @NonNull Business clinic) {
        firebaseClinicViewHolder.bindClinic(clinic);

    }

    @NonNull
    @Override
    public FirebaseClinicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clinic_list_item_drag, parent, false);
        return new FirebaseClinicViewHolder(view);
    }
    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
        return false;
    }

    @Override
    public void onItemDismiss(int position){

    }
}
