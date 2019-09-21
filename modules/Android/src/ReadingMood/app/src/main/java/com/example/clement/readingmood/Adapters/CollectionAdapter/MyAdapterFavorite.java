package com.example.clement.readingmood.Adapters.CollectionAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clement.readingmood.Objets.BookMyCollection;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.ViewHolder.HolderCollection.ViewHolderFavorite;
import com.example.clement.readingmood.ViewHolder.HolderCollection.ViewHolderMyCollection;
import com.example.clement.readingmood.ViewHolder.HolderCollection.ViewHolderMyCollectionVersion2;

import java.util.ArrayList;

public class MyAdapterFavorite extends AdapterAbstractCollection {

    private ViewHolderFavorite vHolder ;

    public MyAdapterFavorite (ArrayList<BookMyCollection> listDisplayed, Context mContext) {
        super(listDisplayed, mContext);
    }

    @NonNull
    @Override
    public ViewHolderFavorite onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)

    {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.fragment_recycle_view_favorite, viewGroup, false);
        vHolder = new ViewHolderFavorite(view,mContext);
        return vHolder;
    }



}