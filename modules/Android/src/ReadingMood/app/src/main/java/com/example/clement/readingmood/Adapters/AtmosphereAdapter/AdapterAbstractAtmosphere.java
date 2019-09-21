package com.example.clement.readingmood.Adapters.AtmosphereAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Objets.DescriptionAtmosphere;
import com.example.clement.readingmood.ViewHolder.HolderAtmosphere.AbstractViewHolderAtmosphere;

import java.util.ArrayList;

public abstract class AdapterAbstractAtmosphere extends RecyclerView.Adapter<AbstractViewHolderAtmosphere>{

    protected Context mcontext;
    /*
    Current context
     */
    protected ArrayList<Atmosphere> listDisplayed;
    /*
    Atmosphere to display
     */

    public AdapterAbstractAtmosphere(ArrayList<Atmosphere> listDisplayed, Context mContext) {
        this.listDisplayed = listDisplayed;
        this.mcontext = mContext;
    }

    @Override
    public abstract AbstractViewHolderAtmosphere onCreateViewHolder(ViewGroup viewGroup, int i);


    @Override
    public void onBindViewHolder(@NonNull AbstractViewHolderAtmosphere myViewHolder, int i) {
    /*
       Link the view of the current item of the recycleView
     */
    try
    {

        Atmosphere atmosphere = new Atmosphere(listDisplayed.get(i).getTitle(), listDisplayed.get(i).getImageRessource(),
                DescriptionAtmosphere.createDescription().get(i), listDisplayed.get(i).getPathToSong());
        myViewHolder.display(atmosphere);
    } catch (IndexOutOfBoundsException e )
    {
        Log.e("Liste out of ", String.valueOf(i) + DescriptionAtmosphere.createDescription().size());
    } catch(NullPointerException e)
    {

    }


    }


    @Override
    public int getItemCount() {
        return listDisplayed.size();
    }


}



