package com.example.clement.readingmood.Adapters.AtmosphereAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.ViewHolder.HolderAtmosphere.ViewHolderSong;
import com.example.clement.readingmood.ViewHolder.HolderAtmosphere.ViewHolderSongList;

import java.util.ArrayList;

public class AdapterSongList extends AdapterAbstractAtmosphere {


    public AdapterSongList(ArrayList<Atmosphere> listDisplayed, Context mContext) {
        super(listDisplayed, mContext);
    }

    @NonNull
    @Override
    public ViewHolderSongList onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        /*
        Will display the current view of the recycleView (the number i)
         */

        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.atmosphere_recycler_view_list, viewGroup, false);
        final ViewHolderSongList vHolder = new ViewHolderSongList(view);
        return vHolder;

    }

}