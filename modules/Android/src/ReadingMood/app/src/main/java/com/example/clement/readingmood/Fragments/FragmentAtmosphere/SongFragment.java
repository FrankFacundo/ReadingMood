package com.example.clement.readingmood.Fragments.FragmentAtmosphere;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSong;
import com.example.clement.readingmood.Adapters.CollectionAdapter.MyAdapterMyCollection;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Objets.DescriptionAtmosphere;
import com.example.clement.readingmood.R;

import java.util.ArrayList;

public class SongFragment extends Fragment {

    private final String[] listTitle ={ "Administration", "Anger", "Attic", "Bathroom",
            "Bedroom", "Calm", "Car", "Castle", "Cave", "Cemetery",
            "Church", "Circus","Company", "Country Town","Desert", "Fear",
            "Field", "Fight","Fog", "Forest", "Garden", "Gunshot","Gym","Happy","Hospital", "Industry", "Jail",
            "Kitchens", "Library", "LivingRoom", "Love","Meadow", "Mine", "Mountain","Ocean",
            "Plane","Rain", "Sad", "School", "Snow","Street","Sun", "Thunder", "TrainStation", "Wind"};
    /*
    List of atmosphere to display
     */
    View v;
    /*
    Current view
     */
    private RecyclerView myRecycle;
    /*
    Recycle View
     */
    private ArrayList<Atmosphere> listAtmosphere;
    /*
    List of the atmosphere that will be displayed
     */
    private AdapterSong madapter;
    /*
    Adapter for the recycleView
     */

    public SongFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*
        Initialise the view
         */
        v = inflater.inflate(R.layout.atmosphere_fragment, container, false);

        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        /*
        Initialise the adapter
         */
        initializeList();
         /*
        Initialise the recycleView
         */
        myRecycle = v.findViewById(R.id.recycle_view_atmosphere);


        madapter = new AdapterSong(listAtmosphere,getContext());
        /*
        Display the recycleView with a linearLayoutManager
         */
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        myRecycle.setLayoutManager(layoutManager);

        myRecycle.setAdapter(madapter);


    }



    private void initializeList()
    /*
    Initialise the list of recycleView
     */
    {

        listAtmosphere = new ArrayList<Atmosphere>() ;
        TypedArray atmosphere_ressource = getResources().obtainTypedArray(R.array.atmosphere_liste);
        TypedArray atmosphere_song = getResources().obtainTypedArray(R.array.atmosphere_song);

        int m = listTitle.length;
        for (int i=0; i<m ; i++)
        {
            listAtmosphere.add(new Atmosphere(listTitle[i], atmosphere_ressource.getResourceId(i,0),
                    DescriptionAtmosphere.createDescription().get(i),atmosphere_song.getResourceId(i,0)));

        }

    }


}
