package com.example.smartroom.Holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartroom.R;
import com.example.smartroom.model.Person;

public class PersonViewHolder extends RecyclerView.ViewHolder {

    private ImageView personImage;
    private TextView name;
    private CardView card_person;

    public PersonViewHolder(@NonNull View itemView) {
        super(itemView);
        this.personImage = (ImageView)itemView.findViewById(R.id.person_image);
        this.name = (TextView)itemView.findViewById(R.id.name);
        this.card_person = (CardView)itemView.findViewById(R.id.card_person);
    }

    public void updateUI(Person person) {
        String uri = person.getImageUri();
        int resource = personImage.getResources().getIdentifier(uri,null,personImage.getContext().getPackageName());
        personImage.setImageResource(resource);
        name.setText(person.getName());
        card_person.setBackgroundResource(R.drawable.my_button_light);
    }


}
