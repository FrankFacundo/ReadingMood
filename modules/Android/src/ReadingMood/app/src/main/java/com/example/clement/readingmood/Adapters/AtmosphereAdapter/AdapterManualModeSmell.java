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
import com.example.clement.readingmood.ViewHolder.HolderAtmosphere.ViewHolderManual;
import com.example.clement.readingmood.ViewHolder.HolderAtmosphere.ViewHolderManualSmell;

import java.util.ArrayList;

public class AdapterManualModeSmell extends RecyclerView.Adapter<ViewHolderManualSmell> {

    private Context mcontext;
    private ArrayList<Atmosphere> listDisplayed;
    private ViewHolderManualSmell myViewHolder;


    public AdapterManualModeSmell(ArrayList<Atmosphere> listDisplayed, Context mContext) {
        this.listDisplayed = listDisplayed;
        this.mcontext = mContext;
    }

    @Override
    public ViewHolderManualSmell onCreateViewHolder(ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.recycle_view_manual_mode_smell, viewGroup, false);
        final ViewHolderManualSmell vHolder = new ViewHolderManualSmell(view, i);
        this.myViewHolder = vHolder;
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderManualSmell viewHolderManual, int i) {
        try
        {
            Atmosphere atmosphere = new Atmosphere(listDisplayed.get(i).getTitle(), listDisplayed.get(i).getImageRessource(),
                    DescriptionAtmosphere.createDescription().get(i),listDisplayed.get(i).getPathToSong());
            viewHolderManual.display(atmosphere,i);
        } catch (IndexOutOfBoundsException e )
        {
            Log.e("Liste out of ", String.valueOf(i) + DescriptionAtmosphere.createDescription().size());
        }

    }

    @Override
    public int getItemCount() {
        return listDisplayed.size();
    }



}
