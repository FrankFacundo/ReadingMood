package com.example.clement.readingmood.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.ViewHolder.ViewHolderDescription;

import java.util.ArrayList;

public class AdapterDescription extends RecyclerView.Adapter<ViewHolderDescription> {

    private ArrayList<String> myDescription ;
    private Context mContext ;
    private ViewHolderDescription vHolder;

    public AdapterDescription(ArrayList<String> myDescription,Context mContext )
    {
        this.myDescription = myDescription;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolderDescription onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.recycle_view_description_atmopshere, viewGroup, false);
        vHolder = new ViewHolderDescription(view);
        return vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDescription viewHolderDescription, int i) {

        vHolder.display(myDescription.get(i), i);

    }

    @Override
    public int getItemCount() {
        return myDescription.size();
    }




}
