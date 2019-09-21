package com.example.clement.readingmood.Adapters.AtmosphereAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Objets.DescriptionAtmosphere;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.ViewHolder.HolderAtmosphere.ViewHolderSelectSmell;
import com.example.clement.readingmood.ViewHolder.HolderAtmosphere.ViewHolderSelectSong;
import java.util.ArrayList;


public class AdapterSelectSmell extends RecyclerView.Adapter<ViewHolderSelectSmell> {


    private Context mContext;
    /*
    Current context
     */
    private ArrayList<Atmosphere> listDisplayed;

    private ViewHolderSelectSmell myHolder;

    private ActionSelectSmell fragmentToSelectSmell;


    public AdapterSelectSmell(ArrayList<Atmosphere> listDisplayed, Context mContext)
    {
        this.mContext = mContext;
        this.listDisplayed = listDisplayed;
    }


    @NonNull
    @Override
    public ViewHolderSelectSmell onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.recycle_view_select_smell, viewGroup, false);
        final ViewHolderSelectSmell vHolder = new ViewHolderSelectSmell(view);
        myHolder = vHolder;
        myHolder.setActionSelected(fragmentToSelectSmell);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSelectSmell viewHolderSelectSmell, int i) {


        try
        {
            Atmosphere atmosphere = new Atmosphere(listDisplayed.get(i).getTitle(), listDisplayed.get(i).getImageRessource(),
                    DescriptionAtmosphere.createDescription().get(i), listDisplayed.get(i).getPathToSong());
            viewHolderSelectSmell.display(listDisplayed.get(i));
        } catch (IndexOutOfBoundsException e )
        {
            Log.e("Liste out of ", String.valueOf(i) + DescriptionAtmosphere.createDescription().size());
        }




    }

    @Override
    public int getItemCount() {
        return listDisplayed.size();
    }


    public interface ActionSelectSmell
    {
        void addSongSmell(Atmosphere myAtmopshere);
        void deleteSongSmell(Atmosphere myAtmosphere);
    }

    public void makeActionSelecteed(ActionSelectSmell fragmentToSelectSmell)
    {
        this.fragmentToSelectSmell = fragmentToSelectSmell;
    }



}
