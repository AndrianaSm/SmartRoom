package com.example.smartroom.Holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartroom.R;
import com.example.smartroom.model.BloodSugar;

public class SugarBloodMeasurementsViewHolder extends RecyclerView.ViewHolder {

    private TextView date;
    private TextView time;
    public  final  TextView measurement;

    public CardView cardThePills;
    public SugarBloodMeasurementsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.date = (TextView)itemView.findViewById(R.id.date);
        this.measurement = (TextView)itemView.findViewById(R.id.measurement);
        this.time = (TextView)itemView.findViewById(R.id.time);
        this.cardThePills = (CardView)itemView.findViewById(R.id.card_measurement);

    }

    public void updateUI(BloodSugar bloodSugar) {
        date.setText(bloodSugar.getDate());
        measurement.setText(bloodSugar.getMeasurements());
        time.setText(bloodSugar.getTime());
        cardThePills.setBackgroundResource(R.drawable.border);
    }
}
