package com.example.clement.readingmood.Adapters.CollectionAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.clement.readingmood.Objets.BookMyCollection;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.ViewHolder.HolderCollection.ViewHolderMyCollectionVersion2;

import java.util.ArrayList;

public class MyAdapterMyCollectionVersion2 extends AdapterAbstractCollection {

    private ViewHolderMyCollectionVersion2 vHolder ;
    private AddForFavorite myFav;
    private ArrayList<ArrayList<String> > textTxt;

    public MyAdapterMyCollectionVersion2(ArrayList<BookMyCollection> listDisplayed, Context mContext,
                                         ArrayList<ArrayList<String>> textTxt) {
        super(listDisplayed, mContext);
        this.textTxt = textTxt;
    }

    public void makeFav(AddForFavorite myFav)
    {
        this.myFav = myFav;
    }


    @NonNull
    @Override
    public ViewHolderMyCollectionVersion2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)

    {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.collection_recycle_view_version_2, viewGroup, false);
//        vHolder = new ViewHolderMyCollectionVersion2(view,mContext, textTxt.get(i));
        vHolder = new ViewHolderMyCollectionVersion2(view,mContext, null);
        vHolder.setAddForFav(myFav);
        return vHolder;
    }


    public interface AddForFavorite
    {
        void addOnFav(BookMyCollection myBook);
        void deleteOnFav(BookMyCollection myBook);
        void deleteCollection(BookMyCollection myBook);
    }




}
