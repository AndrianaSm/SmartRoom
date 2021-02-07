package com.example.smartroom.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartroom.Holders.PersonViewHolder;
import com.example.smartroom.R;
import com.example.smartroom.activities.BloodPressureActivity;
import com.example.smartroom.activities.BloodSugarActivity;
import com.example.smartroom.fragments.BloodPressureFragment;
import com.example.smartroom.fragments.BloodSugarFragment;
import com.example.smartroom.model.Person;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonViewHolder> {

    ArrayList<Person> persons;

    public PersonAdapter(ArrayList<Person> persons) {
        this.persons = persons;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View personCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_person,parent,false);
        return new PersonViewHolder(personCard);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person =persons.get(position);
        holder.updateUI(person);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodSugarFragment bloodSugarFragment = BloodSugarFragment.getBloodSugarFragment();

                if(bloodSugarFragment!=null&& bloodSugarFragment.isVisible()) {
                    BloodSugarFragment.getBloodSugarFragment().startFragmentSugarMeasurements(person.getId());
                    BloodSugarActivity.getBloodSugarActivity().setTheName(person.getId(), "Ζάχαρο");
                }else if(BloodPressureFragment.getBloodPressureFragment()!=null){
                    BloodPressureFragment.getBloodPressureFragment().startFragmentPressureMeasurements(person.getId());
                    BloodPressureActivity.getBloodPressureActivity().setTheName(person.getName(),"Πίεση");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}
