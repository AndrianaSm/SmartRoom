package com.example.smartroom.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartroom.R;
import com.example.smartroom.activities.BloodSugarActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BloodSugarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BloodSugarFragment extends Fragment {

    private static BloodSugarFragment bloodSugarFragment;

    public static void setBloodSugarFragment(BloodSugarFragment bloodSugarFragment) {
        BloodSugarFragment.bloodSugarFragment = bloodSugarFragment;
    }

    public static BloodSugarFragment getBloodSugarFragment() {
        return bloodSugarFragment;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BloodSugarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PillsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BloodSugarFragment newInstance(String param1, String param2) {
        BloodSugarFragment fragment = new BloodSugarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blood_sugar, container, false);

        BloodSugarFragment.setBloodSugarFragment(this);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        PersonsFragment personsFragment ;

        personsFragment = PersonsFragment.newInstance("blah","blah");
        fragmentManager.beginTransaction().add(R.id.container_pressure_top_row,personsFragment).commit();


        if(mParam1.equals("A")||mParam1.equals("B")||mParam1.equals("C")) {
            startFragmentSugarMeasurements(mParam1);
            BloodSugarActivity.getBloodSugarActivity().setTheName(mParam1,"Ζάχαρο");
        }
        return view;
    }

    public void startFragmentSugarMeasurements(String id) {
        SugarBloodMeasurementsFragment sugarBloodMeasurementsFragment;
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        if (id == "") {

        } else {
            sugarBloodMeasurementsFragment = SugarBloodMeasurementsFragment.newInstance(id, "blah");
            fragmentManager.beginTransaction().replace(R.id.container_sugar_bottom_row, sugarBloodMeasurementsFragment).commit();
        }
    }
}