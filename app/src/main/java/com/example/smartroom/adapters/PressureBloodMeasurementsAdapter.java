package com.example.smartroom.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartroom.Holders.PressureBloodMeasurementsViewHolder;
import com.example.smartroom.R;
import com.example.smartroom.model.BloodPressure;

import java.util.ArrayList;

public class PressureBloodMeasurementsAdapter extends RecyclerView.Adapter<PressureBloodMeasurementsViewHolder> {

    ArrayList<BloodPressure> pressureMeasurements;

    public PressureBloodMeasurementsAdapter(ArrayList<BloodPressure> pressureMeasurements) {
        this.pressureMeasurements = pressureMeasurements;
    }

    @NonNull
    @Override
    public PressureBloodMeasurementsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View pressureMeasurementCard = LayoutInflater.from((parent.getContext())).inflate(R.layout.card_pressure_measurement,parent,false);
        return new PressureBloodMeasurementsViewHolder(pressureMeasurementCard);
    }

    @Override
    public void onBindViewHolder(@NonNull PressureBloodMeasurementsViewHolder holder, int position) {
        BloodPressure bloodPressure = this.pressureMeasurements.get(position);
        holder.updateUI(bloodPressure);
    }

    @Override
    public int getItemCount() {
        return pressureMeasurements.size();
    }
}
