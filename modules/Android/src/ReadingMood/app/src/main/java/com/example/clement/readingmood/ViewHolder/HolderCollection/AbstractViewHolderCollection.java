package com.example.clement.readingmood.ViewHolder.HolderCollection;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.example.clement.readingmood.Objets.BookMyCollection;


public abstract class AbstractViewHolderCollection extends RecyclerView.ViewHolder  {
    public AbstractViewHolderCollection(@NonNull View itemView) {
        super(itemView);
        this.initializeDatas(itemView);
        this.initialiseListener();
    }

    /*
      SuperClass that specifies each content of each recycleView item
     */

    //public AbstractViewHolderCollection(@NonNull View itemView) {
      //  super(itemView);


    //}


    public abstract void display(BookMyCollection book);
    /*
     It will change the current values on the .xml by the current attributes of the book
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
