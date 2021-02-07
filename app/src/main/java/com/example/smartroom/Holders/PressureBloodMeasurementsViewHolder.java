package com.example.smartroom.Holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartroom.R;
import com.example.smartroom.model.BloodPressure;

public class PressureBloodMeasurementsViewHolder extends RecyclerView.ViewHolder {
    private TextView date;
    private TextView time;
    public  TextView systoliki;
    public  TextView diastoliki;
    public  TextView pulse;
    public CardView cardPressureMeasurements;


    public PressureBloodMeasurementsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.date = (TextView)itemView.findViewById(R.id.date);
        this.time = (TextView)itemView.findViewById(R.id.time);
        this.systoliki = (TextView)itemView.findViewById(R.id.systoliki);
        this.diastoliki = (TextView)itemView.findViewById(R.id.diastoliki);
        this.pulse = (TextView)itemView.findViewById(R.id.pulse);
        this.cardPressureMeasurements = (CardView)itemView.findViewById(R.id.card_pressure_measurement);
    }

    public void updateUI (BloodPressure bloodPressure) {
        date.setText((bloodPressure.getDate()));
        time.setText(bloodPressure.getTime());
        systoliki.setText(bloodPressure.getSystoliki());
        diastoliki.setText(bloodPressure.getDiastoliki());
        pulse.setText(bloodPressure.getPulse());
        cardPressureMeasurements.setBackgroundResource(R.drawable.bold_border);
    }
}
