package com.example.clement.readingmood.Objets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;

import com.example.clement.readingmood.R;

import java.util.ArrayList;

public class AtmosphereByName {

    private Context myContext;

    private final static String[] listTitle ={ "Administration", "Anger", "Attic", "Bathroom",
            "Bedroom", "Calm", "Car", "Castle", "Cave", "Cemetery",
            "Church", "Circus","Company", "Country Town","Desert", "Fear",
            "Field", "Fight","Fog", "Forest", "Garden", "Gunshot","Gym","Happy","Hospital", "Industry", "Jail",
            "Kitchens", "Library", "LivingRoom", "Love","Meadow", "Mine", "Mountain","Ocean",
            "Plane","Rain", "Sad", "School", "Snow","Street","Sun", "Thunder", "TrainStation", "Wind"};

    private final static String[] listSmell = {"Agricultural field","Christmas", "Cook","Forest","Floral garden"
            ,"Ocean"};

    private static TypedArray atmosphere_ressource ;
    private static TypedArray atmosphere_song ;
    private static ArrayList<Atmosphere> listAtmosphere = new ArrayList<>();

    public AtmosphereByName(Context myContext)
    {
        this.myContext = myContext;
        getData();
    }

    private void getData()
    {
        listAtmosphere.clear();
        atmosphere_ressource = myContext.getResources().obtainTypedArray(R.array.atmosphere_liste);
        atmosphere_song  = myContext.getResources().obtainTypedArray(R.array.atmosphere_song);

        int m = listTitle.length;
        for (int i=0; i<m ; i++)
        {
            listAtmosphere.add(new Atmosphere(listTitle[i], atmosphere_ressource.getResourceId(i,0),
                    DescriptionAtmosphere.createDescription().get(i),atmosphere_song.getResourceId(i,0)));
        }


    }

    public Atmosphere getAtmosphereFromTitle(String title)
    {
        Atmosphere result = null;
        int index = getIndex(title);
        if (index != -1)
        {
            result = listAtmosphere.get(index);
        }else
        {
            Log.e("Erreur AtmosphereByName", "Sound Not found");
        }
        return result;

    }

    private int getIndex(String title)
    {
        int m = listTitle.length;
        for (int i = 0; i<m ; i++)
        {

            if (title.equals(listTitle[i]))
            {
                return i;
            }
        }
        return -1;
    }


    public ArrayList<Atmosphere> getAllSmell()
    {
        ArrayList<Atmosphere> result = new ArrayList<>();
        TypedArray atmosphere_ressource = myContext.getResources().obtainTypedArray(R.array.atmosphere_liste_smell);
        int m = listSmell.length;
        for (int i=0; i<m ; i++)
        {
            result.add(new Atmosphere(listSmell[i], atmosphere_ressource.getResourceId(i,0), null,0));
        }

        return result;
    }

    public ArrayList<Atmosphere> getAllSongs()
    {

        return listAtmosphere;

    }




}
