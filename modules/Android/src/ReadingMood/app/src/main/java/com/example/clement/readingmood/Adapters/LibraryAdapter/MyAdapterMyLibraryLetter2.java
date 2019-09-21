package com.example.clement.readingmood.Adapters.LibraryAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clement.readingmood.Objets.BookMyLibrary;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.ViewHolder.HolderLibrary.ViewHolderLibraryLetter;
import com.example.clement.readingmood.ViewHolder.HolderLibrary.ViewHolderLibraryLetter2;
import com.example.clement.readingmood.ViewHolder.HolderLibrary.ViewHolderLibraryName;

import java.util.ArrayList;


public class MyAdapterMyLibraryLetter2 extends RecyclerView.Adapter<ViewHolderLibraryLetter2>{


    private ArrayList<BookMyLibrary> bookToDisplay;
    protected Context mContext;

    private MyAdapterMyLibraryLetter2.CreateDownload fragmentForDownload;

    public interface CreateDownload
    {
        void downloadForAutomatic(BookMyLibrary myBook);
    }

    public void makeCreation(MyAdapterMyLibraryLetter2.CreateDownload fragmentForDownload)
    {
        this.fragmentForDownload = fragmentForDownload;
    }

    public MyAdapterMyLibraryLetter2(ArrayList<BookMyLibrary> bookToDisplay , Context mContext)
    {
        this.mContext = mContext;
        this.bookToDisplay = bookToDisplay;
    }

    @NonNull
    @Override
    public ViewHolderLibraryLetter2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.recycler_view_library_letter, viewGroup, false);

        ViewHolderLibraryLetter2 vHolder  = new ViewHolderLibraryLetter2(view);
        vHolder.setFragmentForDownload(fragmentForDownload);
        return vHolder;
    }
    /*
    Will display the current view of the recycleView (the number i)
     */

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLibraryLetter2 myViewHolder, int i) {

        myViewHolder.display(  bookToDisplay.get(i) );
    }


    @Override
    public int getItemCount() {
        return bookToDisplay.size();
    }


    public void setListBook(ArrayList<BookMyLibrary> bookToDisplay) {
        this.bookToDisplay.addAll(bookToDisplay);
        notifyDataSetChanged();
    }

    public void clear()
    {
        bookToDisplay.clear();
        notifyDataSetChanged();
    }



}
