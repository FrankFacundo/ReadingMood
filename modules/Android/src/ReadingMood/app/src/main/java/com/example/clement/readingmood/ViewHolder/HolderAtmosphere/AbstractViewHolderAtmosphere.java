package com.example.clement.readingmood.ViewHolder.HolderAtmosphere;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.clement.readingmood.Objets.Atmosphere;

public abstract class AbstractViewHolderAtmosphere extends RecyclerView.ViewHolder  {


    public AbstractViewHolderAtmosphere(@NonNull View itemView) {
        super(itemView);
        initializeDatas(itemView);
        initialiseListener();

    }

    public abstract void display(Atmosphere book);
    /*
     It will change the current values on the .xml by the current attributes of the atmosphere
     */
    protected abstract void initializeDatas(View itemView);
    /*
    It initialises all the textView ... that is presents in each item of the recycleView
     */

    protected abstract void initialiseListener();
    /*
    It will add the action of a click on each recycleView
     */
    protected abstract void addIntent(View itemView);
    /*
    Adds the datas on an intent and change the activity
     */


}
