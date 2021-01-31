package com.example.smartroom.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartroom.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SugarBloodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SugarBloodFragment extends Fragment {

    private static SugarBloodFragment sugarBloodFragment;

    public static void setSugarBloodFragment(SugarBloodFragment sugarBloodFragment) {
        SugarBloodFragment.sugarBloodFragment = sugarBloodFragment;
    }

    public static SugarBloodFragment getSugarBloodFragment() {
        return sugarBloodFragment;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SugarBloodFragment() {
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
    public static SugarBloodFragment newInstance(String param1, String param2) {
        SugarBloodFragment fragment = new SugarBloodFragment();
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
        View view = inflater.inflate(R.layout.fragment_blood_presure, container, false);

        SugarBloodFragment.setSugarBloodFragment(this);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        PersonsFragment personsFragment ;

        personsFragment = PersonsFragment.newInstance("blah","blah");
        fragmentManager.beginTransaction().add(R.id.container_top_row,personsFragment).commit();



        return view;
    }

    public void startFragmentMeasurements(String id) {
        SugarBloodMeasurementsFragment sugarBloodMeasurementsFragment;
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        sugarBloodMeasurementsFragment = SugarBloodMeasurementsFragment.newInstance(id,"blah");
        fragmentManager.beginTransaction().replace(R.id.container_bottom_row, sugarBloodMeasurementsFragment).commit();
    }
}