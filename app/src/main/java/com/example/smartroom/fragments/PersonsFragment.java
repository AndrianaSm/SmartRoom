package com.example.smartroom.fragments;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;

import com.example.smartroom.R;
import com.example.smartroom.adapters.PersonAdapter;
import com.example.smartroom.services.DataService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PersonsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonsFragment newInstance(String param1, String param2) {
        PersonsFragment fragment = new PersonsFragment();
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
        View view = inflater.inflate(R.layout.fragment_persons, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_persons);
        recyclerView.setHasFixedSize(true);

        PersonAdapter personAdapter = new PersonAdapter(DataService.getOurInstance().getPersons());

        recyclerView.addItemDecoration(new HorizontalSpaceItemDecorator(10));
        recyclerView.setAdapter(personAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }
}

class HorizontalSpaceItemDecorator extends RecyclerView.ItemDecoration {
    private final int spacer;

    public HorizontalSpaceItemDecorator(int spacer) {
        this.spacer = spacer;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.right = spacer;
        outRect.left = spacer;
        outRect.bottom = spacer;
        outRect.top = spacer;


    }
}