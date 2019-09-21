package com.example.clement.readingmood.Fragments.FragmentLibrary;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSongVersion2;
import com.example.clement.readingmood.Adapters.LibraryAdapter.AdapterListAuthor;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Objets.BookMyLibrary;
import com.example.clement.readingmood.Objets.DescriptionAtmosphere;
import com.example.clement.readingmood.R;

import java.util.ArrayList;

public class FragmentAuthorVersion2 extends Fragment {

    private View v;

    private RecyclerView myRecycle;

    private ArrayList<String> listAuthor, listDate;

    private AdapterListAuthor madapter;

    public FragmentAuthorVersion2() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*
        Initialise the view
         */
        v = inflater.inflate(R.layout.atmosphere_fragment_library_author, container, false);

        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        /*
        Initialise the adapter
         */
        initializeList();
         /*
        Initialise the recycleView
         */
        myRecycle = v.findViewById(R.id.recycler_view_library_horizontal_author);
        madapter = new AdapterListAuthor(getContext(),listAuthor,listDate);
        /*
        Display the recycleView with a linearLayoutManager
         */
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        myRecycle.setLayoutManager(layoutManager);
        myRecycle.setAdapter(madapter);

    }



    private void initializeList()
    /*
    Initialise the list of recycleView
     */
    {

        listAuthor = new ArrayList<>();
        listDate = new ArrayList<>();
        for (int i = 0; i<10; i++)
        {
            listAuthor.add("KFC "+ i);
            listDate.add(String.valueOf(i));
        }

    }


}
