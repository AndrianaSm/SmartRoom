package com.example.smartroom.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartroom.Holders.SugarBloodMeasurementsViewHolder;
import com.example.smartroom.R;
import com.example.smartroom.model.BloodSugar;

import java.util.ArrayList;

public class SugarBloodMeasurementsAdapter extends RecyclerView.Adapter<SugarBloodMeasurementsViewHolder> {

    ArrayList<BloodSugar> thePills;

    public SugarBloodMeasurementsAdapter(ArrayList<BloodSugar> thePills) {
        this.thePills = thePills;
    }
    @NonNull
    @Override
    public SugarBloodMeasurementsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View measurementCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_measurements,parent,false);
        return new SugarBloodMeasurementsViewHolder(measurementCard);
    }

    @Override
    public void onBindViewHolder(@NonNull SugarBloodMeasurementsViewHolder holder, int position) {
        BloodSugar bloodSugar = this.thePills.get(position);
        holder.updateUI(bloodSugar);
    }


    @Override
    public int getItemCount() {
        return thePills.size();
    }
}
