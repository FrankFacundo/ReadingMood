package com.example.clement.readingmood.Adapters.CollectionAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import com.example.clement.readingmood.Objets.BookMyCollection;
import com.example.clement.readingmood.ViewHolder.HolderCollection.AbstractViewHolderCollection;
import java.util.ArrayList;


public abstract class AdapterAbstractCollection extends RecyclerView.Adapter<AbstractViewHolderCollection>{


    protected ArrayList<BookMyCollection> listDisplayed;
    /*
    Book that will be displayed
     */
    protected Context mContext;

    public AdapterAbstractCollection(ArrayList<BookMyCollection> listDisplayed, Context mContext)
    {
        this.listDisplayed = listDisplayed;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public abstract AbstractViewHolderCollection onCreateViewHolder(@NonNull ViewGroup viewGroup, int i);
    /*
    Will display the current view of the recycleView (the number i)
     */

    @Override
    public void onBindViewHolder(@NonNull AbstractViewHolderCollection myViewHolder, int i) {
     /*
    Add a data to a view
     */

        BookMyCollection myBook = listDisplayed.get(i);
        Log.e("Abstract Adatper",myBook.getListAtmosphere() + "" );
        myViewHolder.display(myBook);
    }


    @Override
    public int getItemCount() {
        return listDisplayed.size();
    }




}
