package com.example.clement.readingmood.Adapters.AtmosphereAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.ViewHolder.HolderAtmosphere.ViewHolderSong;

import java.util.ArrayList;

public class AdapterSong extends AdapterAbstractAtmosphere {


    public AdapterSong(ArrayList<Atmosphere> listDisplayed, Context mContext) {
        super(listDisplayed, mContext);
    }

    @NonNull
    @Override
    public ViewHolderSong onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        /*
        Will display the current view of the recycleView (the number i)
         */

        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.atmosphere_recycle_view, viewGroup, false);
        final ViewHolderSong vHolder = new ViewHolderSong(view);
        return vHolder;

    }

}