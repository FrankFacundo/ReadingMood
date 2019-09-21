package com.example.clement.readingmood.Adapters.CollectionAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.clement.readingmood.Objets.BookMyCollection;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.ViewHolder.HolderCollection.AbstractViewHolderCollection;
import com.example.clement.readingmood.ViewHolder.HolderCollection.ViewHolderMyCollectionClicked;

import java.util.ArrayList;

public class MyAdapterMyCollectionClicked extends AdapterAbstractCollection {

    public MyAdapterMyCollectionClicked(ArrayList<BookMyCollection> listDisplayed, Context mContext)
    {
        super(listDisplayed,mContext);
    }

    @NonNull
    @Override
    public AbstractViewHolderCollection onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.fragment_collection_click, viewGroup, false);

        final ViewHolderMyCollectionClicked vHolder = new ViewHolderMyCollectionClicked(view, view.getContext());
        return vHolder;

    }
}
