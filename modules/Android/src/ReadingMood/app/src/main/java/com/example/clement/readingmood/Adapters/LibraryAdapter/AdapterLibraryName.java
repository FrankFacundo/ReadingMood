package com.example.clement.readingmood.Adapters.LibraryAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.clement.readingmood.R;
import com.example.clement.readingmood.ViewHolder.HolderLibrary.ViewHolderLibraryName;


public class AdapterLibraryName extends RecyclerView.Adapter<ViewHolderLibraryName>{


    protected String[] listLetter;
    protected Context mContext;

    private AdapterLibraryName.CreateDownload fragmentForDownload;

    public interface CreateDownload
    {
        void inflateLayout(int layout, String letter);
        void deflateLayout(int layout, String letter);
    }

    public void setFragmentForDownload(CreateDownload fragmentForDownload) {
        this.fragmentForDownload = fragmentForDownload;
    }

    public AdapterLibraryName(String[] listLetter, Context mContext)
    {
        this.listLetter = listLetter;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolderLibraryName onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.recycler_view_library_name, viewGroup, false);
        ViewHolderLibraryName vHolder  = new ViewHolderLibraryName(view);
        vHolder.setFragmentForDownload(fragmentForDownload);
        return vHolder;
    }
    /*
    Will display the current view of the recycleView (the number i)
     */

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLibraryName myViewHolder, int i) {

        String letter = new String();
        try
        {
            letter = listLetter[i];
        }catch (IndexOutOfBoundsException e)
        {
            Log.e("AdapterLibrary","letter null");
        }

        myViewHolder.display(letter);
    }


    @Override
    public int getItemCount() {
        return listLetter.length;
    }



}
