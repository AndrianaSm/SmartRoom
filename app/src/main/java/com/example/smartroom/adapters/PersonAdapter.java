package com.example.smartroom.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartroom.Holders.PersonViewHolder;
import com.example.smartroom.R;
import com.example.smartroom.activities.BloodSugarActivity;
import com.example.smartroom.fragments.SugarBloodFragment;
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
        final int p = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SugarBloodFragment.getSugarBloodFragment().startFragmentMeasurements(person.getId());
                BloodSugarActivity.getBloodSugarActivity().setTheName(person.getName(),"Ζάχαρο");
            }
        });
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}
