package com.example.smartroom.fragments;

import android.app.ActivityManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartroom.R;
import com.example.smartroom.activities.BloodPressureActivity;
import com.example.smartroom.model.BloodPressure;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BloodPressureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BloodPressureFragment extends Fragment {

    private static  BloodPressureFragment bloodPressureFragment;

    public static BloodPressureFragment getBloodPressureFragment() {
        return bloodPressureFragment;
    }

    public static void setBloodPressureFragment(BloodPressureFragment bloodPressureFragment) {
        BloodPressureFragment.bloodPressureFragment = bloodPressureFragment;
    }

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public BloodPressureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BloodPressureFragment.
     */
    public static BloodPressureFragment newInstance(String param1, String param2) {
        BloodPressureFragment fragment = new BloodPressureFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blood_pressure, container, false);

        BloodPressureFragment.setBloodPressureFragment(this);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        PersonsFragment personsFragment;

        if(mParam1.equals("A")||mParam1.equals("B")||mParam1.equals("C")) {
            startFragmentPressureMeasurements(mParam1);
            BloodPressureActivity.getBloodPressureActivity().setTheName(mParam1,"Πίεση");
        }

        personsFragment = PersonsFragment.newInstance("blah","blah");
        fragmentManager.beginTransaction().add(R.id.container_pressure_top_row,personsFragment).commit();

        return view;
    }

    public void startFragmentPressureMeasurements(String id) {
        PressureBloodMeasurementsFragment sugarBloodMeasurementsFragment;
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        sugarBloodMeasurementsFragment = PressureBloodMeasurementsFragment.newInstance(id,"blah");
        fragmentManager.beginTransaction().replace(R.id.container_pressure_bottom_row, sugarBloodMeasurementsFragment).commit();
    }
}