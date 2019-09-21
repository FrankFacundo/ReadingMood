package com.example.clement.readingmood.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clement.readingmood.Objets.Pact35;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.ViewHolder.HolderAtmosphere.AbstractViewHolderAtmosphere;
import com.example.clement.readingmood.ViewHolder.HolderAtmosphere.ViewHolderSong;
import com.example.clement.readingmood.ViewHolder.ViewHolderAbout;

import java.util.ArrayList;

public class AdapterAbout extends RecyclerView.Adapter<ViewHolderAbout> {

    private ArrayList<Pact35> listToDisplay = new ArrayList<>();
    private Context myContext;

    public AdapterAbout(ArrayList<Pact35> listToDisplay, Context myContext)
    {
        this.listToDisplay = listToDisplay;
        this.myContext = myContext;
    }


    @NonNull
    @Override
    public ViewHolderAbout onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {



        LayoutInflater inflater = LayoutInflater.from(myContext);
        View view = inflater.inflate(R.layout.recycler_view_about, viewGroup, false);
        final ViewHolderAbout vHolder = new ViewHolderAbout (view);
        return vHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAbout viewHolderAbout, int i) {

        viewHolderAbout.display(listToDisplay.get(i));

    }

    @Override
    public int getItemCount() {

        return listToDisplay.size();
    }
}
