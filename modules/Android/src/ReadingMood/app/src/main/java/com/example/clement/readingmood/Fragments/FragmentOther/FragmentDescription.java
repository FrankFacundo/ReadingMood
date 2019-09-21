package com.example.clement.readingmood.Fragments.FragmentOther;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clement.readingmood.Adapters.AdapterDescription;
import com.example.clement.readingmood.R;

import java.util.ArrayList;

public class FragmentDescription extends Fragment {


    private View v ;
    private RecyclerView myRecycle;
    private AdapterDescription madapter;


    public static FragmentDescription create(ArrayList<String> description)
    {
        Bundle args = new Bundle();
        args.putStringArrayList("description",description);
        FragmentDescription fragment = new FragmentDescription();
        fragment.setArguments(args);
        return fragment;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        Bundle myBundle = getArguments();
        ArrayList<String> myDescriptions = myBundle.getStringArrayList("description");
        if (myDescriptions!=null)
        {
            v = inflater.inflate(R.layout.description_layout, container, false);
            myRecycle = v.findViewById(R.id.recycle_view_description_atmosphere);
            madapter = new AdapterDescription(myDescriptions,getContext());
            myRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
            myRecycle.setAdapter(madapter);
        }

        return v;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
