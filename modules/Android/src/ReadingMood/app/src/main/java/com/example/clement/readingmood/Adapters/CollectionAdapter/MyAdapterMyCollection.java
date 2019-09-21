package com.example.clement.readingmood.Adapters.CollectionAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.clement.readingmood.Objets.BookMyCollection;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.ViewHolder.HolderCollection.ViewHolderMyCollection;

import java.util.ArrayList;

public class MyAdapterMyCollection extends AdapterAbstractCollection {

    private ViewHolderMyCollection vHolder ;

    private CreateFragment fragmentForTablet;

    public interface CreateFragment
    {
        void addFragmentForTablet(String title, String authorView, String summaryView, int pathImage);
    }


    public MyAdapterMyCollection(ArrayList<BookMyCollection> listDisplayed, Context mContext) {
        super(listDisplayed, mContext);
    }


    public void makeCreation(CreateFragment fragmentForTablet)
    {
        this.fragmentForTablet = fragmentForTablet;
    }

    @NonNull
    @Override
    public ViewHolderMyCollection onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)

    {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.my_collection_recycle_view, viewGroup, false);
        vHolder = new ViewHolderMyCollection(view);
        vHolder.setFragmentForTablet(fragmentForTablet);
        return vHolder;
    }









}