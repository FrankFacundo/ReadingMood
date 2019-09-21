package com.example.clement.readingmood.Adapters.LibraryAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clement.readingmood.Objets.BookMyLibrary;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.ViewHolder.HolderLibrary.ViewHolderLibraryName;
import com.example.clement.readingmood.ViewHolder.HolderLibrary.ViewHolderListAuhtor;

import java.util.ArrayList;

import nl.siegmann.epublib.domain.Book;

public class AdapterListAuthor extends RecyclerView.Adapter<ViewHolderListAuhtor> {

    private Context mContext;
    private ArrayList<String> listName, listDate;
    private ViewHolderListAuhtor myHolder;
    private ChangePage change;

    public interface ChangePage
    {
        void changePage(String author, String date);
    }

    public AdapterListAuthor(Context mContext, ArrayList<String> listName, ArrayList<String> listDate)
    {
        this.mContext = mContext;
        this.listDate = listDate;
        this.listName = listName;
    }

    public void makeChange(ChangePage change)
    {
        this.change = change;
    }

    @NonNull
    @Override
    public ViewHolderListAuhtor onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.recycler_view_list_author, viewGroup, false);
        ViewHolderListAuhtor vHolder  = new ViewHolderListAuhtor(view);
        vHolder.makeChange(change);
        myHolder = vHolder;
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListAuhtor viewHolderListAuhtor, int i) {

        viewHolderListAuhtor.display(listName.get(i), listDate.get(i));

    }

    @Override
    public int getItemCount() {
        return listDate.size();
    }
}
