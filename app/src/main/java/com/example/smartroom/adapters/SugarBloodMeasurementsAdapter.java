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

    ArrayList<BloodSugar> sugarMeasurements;

    public SugarBloodMeasurementsAdapter(ArrayList<BloodSugar> sugarMeasurements) {
        this.sugarMeasurements = sugarMeasurements;
    }
    @NonNull
    @Override
    public SugarBloodMeasurementsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View sugarMeasurementCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_sugar_measurements,parent,false);
        return new SugarBloodMeasurementsViewHolder(sugarMeasurementCard);
    }

    @Override
    public void onBindViewHolder(@NonNull SugarBloodMeasurementsViewHolder holder, int position) {
        BloodSugar bloodSugar = this.sugarMeasurements.get(position);
        holder.updateUI(bloodSugar);
    }


    @Override
    public int getItemCount() {
        return sugarMeasurements.size();
    }
}
