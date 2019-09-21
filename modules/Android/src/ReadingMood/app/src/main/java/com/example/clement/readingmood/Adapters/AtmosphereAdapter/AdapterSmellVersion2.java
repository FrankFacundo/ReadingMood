package com.example.clement.readingmood.Adapters.AtmosphereAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.ViewHolder.HolderAtmosphere.ViewHolderSmellVersion2;
import com.example.clement.readingmood.ViewHolder.HolderAtmosphere.ViewHolderSongVersion2;

import java.util.ArrayList;

public class AdapterSmellVersion2 extends AdapterAbstractAtmosphere {
    public AdapterSmellVersion2(ArrayList<Atmosphere> listDisplayed, Context mContext) {
        super(listDisplayed, mContext);
    }

    @NonNull
    @Override
    public ViewHolderSmellVersion2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        /*
        Will display the current view of the recycleView (the number i)
         */

        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.atmosphere_recycle_view_smell_version_2, viewGroup, false);
        final ViewHolderSmellVersion2 vHolder = new ViewHolderSmellVersion2(view, mcontext);
        return vHolder;

    }

}
