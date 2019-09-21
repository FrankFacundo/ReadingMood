package com.example.clement.readingmood.Fragments.FragmentNavigator;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSmellVersion2;
import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSongVersion2;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Objets.DescriptionAtmosphere;
import com.example.clement.readingmood.R;

import java.util.ArrayList;

public class FragmentAtmospheresBottom extends Fragment {



    private final String[] listTitle ={ "Admnistration", "Anger", "Attic", "Bathroom",
            "Bedroom", "Calm", "Car", "Castle", "Cave", "Cemetery",
            "Church", "Circus","Company", "Country Town","Desert", "Fear",
            "Field", "Fight","Fog", "Forest", "Garden", "Gunshot","Gym","Happy","Hospital", "Industry", "Jail",
            "Kitchens", "Library", "LivingRoom", "Love","Meadow", "Mine", "Mountain","Ocean",
            "Plane","Rain", "Sad", "School", "Snow","Sun", "Thunder", "TrainStation", "Wind"};


    private final String[] listTitleSmell ={ "Agricultural field","Christmas", "Cook","Forest","Floral garden"
            ,"Ocean"};


    /*
    List of atmosphere to display
     */
    View v;
    /*
    Current view
     */
    private RecyclerView myRecycle, myRecycleSmell;
    /*
    Recycle View
     */
    private ArrayList<Atmosphere> listAtmosphere,listAtmosphereSmell;
    /*
    List of the atmosphere that will be displayed
     */
    private AdapterSongVersion2 madapter;
    /*
    Adapter for the recycleView
     */
    private AdapterSmellVersion2 madapterSmell;
    /*
    Adapter for the recycleView
     */




    public FragmentAtmospheresBottom () {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*
        Initialise the view
         */
        v = inflater.inflate(R.layout.atmosphere_song_bottom_click, container, false);

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
        myRecycle = v.findViewById(R.id.recycle_view_atmosphere_bottom);
        myRecycleSmell = v.findViewById(R.id.recycle_view_atmosphere_bottom_smell);


        madapter = new AdapterSongVersion2(listAtmosphere,getContext());
        madapterSmell = new AdapterSmellVersion2(listAtmosphereSmell,getContext());


        LinearLayoutManager layoutManagerDisplay
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);




        if (view.findViewById(R.id.layout_for_tablet) != null)
        {
            GridLayoutManager managerSmell1 = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
            myRecycleSmell.setLayoutManager(managerSmell1 );
            GridLayoutManager manager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
            myRecycle.setLayoutManager(manager);

        }
        else
        {
            LinearLayoutManager smellLayout
                    = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            myRecycleSmell.setLayoutManager(smellLayout);
            myRecycle.setLayoutManager(layoutManagerDisplay);
        }

        myRecycleSmell.setAdapter(madapterSmell);
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
        Log.e("La taille est de : ", String.valueOf(m));
        for (int i=0; i<m ; i++)
        {
            listAtmosphere.add(new Atmosphere(listTitle[i], atmosphere_ressource.getResourceId(i,0),
                    DescriptionAtmosphere.createDescription().get(i),atmosphere_song.getResourceId(i,0)));
        }

        listAtmosphereSmell = new ArrayList<Atmosphere>() ;
        TypedArray atmosphere_ressourceSmell = getResources().obtainTypedArray(R.array.atmosphere_liste_smell);
        int n = listTitleSmell.length;
        for (int i=0; i<n ; i++)
        {
//            listAtmosphere.add(new Atmosphere(listTitleSmell[i], atmosphere_ressourceSmell.getResourceId(i,0),null));
                    listAtmosphereSmell.add(new Atmosphere(listTitleSmell[i], atmosphere_ressourceSmell.getResourceId(i,0),
                            null, 0));
        }





        }
}



