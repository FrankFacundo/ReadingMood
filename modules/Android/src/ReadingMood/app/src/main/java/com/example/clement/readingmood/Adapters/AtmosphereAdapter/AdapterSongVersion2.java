package com.example.clement.readingmood.Adapters.AtmosphereAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.ViewHolder.HolderAtmosphere.ViewHolderSongVersion2;

import java.util.ArrayList;

public class AdapterSongVersion2 extends AdapterAbstractAtmosphere {
    public AdapterSongVersion2(ArrayList<Atmosphere> listDisplayed, Context mContext) {
        super(listDisplayed, mContext);
    }

    @NonNull
    @Override
    public ViewHolderSongVersion2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        /*
        Will display the current view of the recycleView (the number i)
         */
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.atmopshere_recycle_view_version_2, viewGroup, false);
        final ViewHolderSongVersion2 vHolder = new ViewHolderSongVersion2(view, mcontext);
        return vHolder;

    }

}
