package com.example.clement.readingmood.Fragments.FragmentAtmosphere;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSmell;
import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSmellVersion2;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.R;

import java.util.ArrayList;

public class SmellFragmentVersion2 extends Fragment {
    private final String[] listTitle ={ "AgriculturalField","Christmas", "Cook","Forest","FloralGarden"
            ,"Ocean"};
    /*
    Title of all the view of this page
     */
    View v;
    /*
    Current view
     */
    private RecyclerView myRecycle;
    /*
    Recycleview to display the atmosphere
     */
    private ArrayList<Atmosphere> listAtmosphere;
    /*
    List of atmosphere that will be displayed on the recycleView
     */
    private AdapterSmellVersion2 madapter;
    /*
    Adapter for the recycleView
     */


    public SmellFragmentVersion2() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*
        Initialise the view
         */
        v = inflater.inflate(R.layout.atmosphere_fragment_smell_version_2, container, false);
        /*
        Initialise the recycleView
         */
        myRecycle = v.findViewById(R.id.recycle_view_atmosphere_smell_version_2);
        /*
        Initialise the list that will be displayed
         */
        initializeList();
        /*
        Initialise the adapter
         */
        madapter = new AdapterSmellVersion2(listAtmosphere,getContext());
        /*
        Display with a linearLayout the recycle view
         */
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        myRecycle.setLayoutManager(layoutManager);
        myRecycle.setAdapter(madapter);
        return v;

    }

    private void initializeList()
    /*
    Initialise the view of atmosphere
     */
    {

        listAtmosphere = new ArrayList<Atmosphere>() ;
        TypedArray atmosphere_ressource = getResources().obtainTypedArray(R.array.atmosphere_liste_smell);
        int m = listTitle.length;
        for (int i=0; i<m ; i++)
        {
            listAtmosphere.add(new Atmosphere(listTitle[i], atmosphere_ressource.getResourceId(i,0), null,0));
        }


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initializeList();
        super.onCreate(savedInstanceState);
    }
}