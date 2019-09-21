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
import com.example.clement.readingmood.ViewHolder.HolderAtmosphere.ViewHolderSelectSong;
import java.util.ArrayList;


public class AdapterSelectSong extends RecyclerView.Adapter<ViewHolderSelectSong> {


    private Context mContext;
    /*
    Current context
     */
    private ArrayList<Atmosphere> listDisplayed;

    private ViewHolderSelectSong myHolder;

    private ActionSelect fragmentToSelect;

    private ActionSelectToDisplay secondAction;

    public void setSecondAction( ActionSelectToDisplay frag)
    {
        this.secondAction = frag;
    }


    public AdapterSelectSong(ArrayList<Atmosphere> listDisplayed, Context mContext)
    {
        this.mContext = mContext;
        this.listDisplayed = listDisplayed;
    }


    @NonNull
    @Override
    public ViewHolderSelectSong onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.recycle_view_select_song, viewGroup, false);
        final ViewHolderSelectSong vHolder = new ViewHolderSelectSong(view);
        myHolder = vHolder;
        myHolder.setActionSelected(fragmentToSelect);
        myHolder.setMySecondAction(secondAction);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSelectSong viewHolderSelectSong, int i) {


//        holder.setIsRecyclable(false);
        try
        {
            viewHolderSelectSong.display(listDisplayed.get(i));
        } catch (IndexOutOfBoundsException e )
        {
            Log.e("Liste out of ", String.valueOf(i) + DescriptionAtmosphere.createDescription().size());
        }




    }

    @Override
    public int getItemCount() {
        return listDisplayed.size();
    }


    public interface ActionSelect
    {
        void addSong(Atmosphere myAtmopshere);
        void deleteSong(Atmosphere myAtmosphere);
    }

    public interface ActionSelectToDisplay
    {
        void updateAtmosphere();
    }

    public void makeActionSelecteed(ActionSelect fragmentToSelect)
    {
        this.fragmentToSelect = fragmentToSelect;
    }



}
