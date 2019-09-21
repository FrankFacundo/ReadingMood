package com.example.clement.readingmood.Fragments.FragmentNavigator;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clement.readingmood.Adapters.CollectionAdapter.MyAdapterFavorite;
import com.example.clement.readingmood.Adapters.CollectionAdapter.MyAdapterMyCollection;
import com.example.clement.readingmood.Fragments.FragmentAtmosphere.SmellFragmentVersion2;
import com.example.clement.readingmood.Fragments.FragmentAtmosphere.SongFragmentVersion2;
import com.example.clement.readingmood.Fragments.FragmentCollection.MyCollectionFragmentVersion2;
import com.example.clement.readingmood.Objets.BookMyCollection;
import com.example.clement.readingmood.R;

import java.util.ArrayList;

import nl.siegmann.epublib.domain.Book;

public class FragmentFavoritesBottom extends Fragment {



    private ArrayList<BookMyCollection> list_displayed = new ArrayList<BookMyCollection>();
    /*
    List of Book (that is a JavaClass) that will be displayed in the recycleView
     */
    private ArrayList<String> listBook;
    /*
    Title of books
     */


    View v;

    private RecyclerView myRecycle;
    /*
    Recycle View
     */
    private MyAdapterFavorite madapter;
    /*
    Adapter for the recycle view
     */
    private LinearLayoutManager layoutManager;


    public FragmentFavoritesBottom() {    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        Log.e("Restart", String.valueOf(savedInstanceState==null)
        );
        if (savedInstanceState !=null)
        {
//            layoutManager = savedInstanceState.getParcelable("Layout");
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_favorite_bottom, container, false);
        if (savedInstanceState ==null)
        {
            initViews(v);
        }else
        {
//            ArrayList<BookMyCollection> listBook = savedInstanceState.getParcelableArrayList("myBook");
//            for (BookMyCollection books : listBook)
//            {
//                list_displayed.add(books);
//
//            }



        }

        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        /*
        Initialise the adapter
         */
        if (savedInstanceState == null)
        {
            madapter = new MyAdapterFavorite(list_displayed, getContext());
            myRecycle.setAdapter(madapter);
        }



    }


    private void initViews(View v)
    {
        myRecycle = v.findViewById(R.id.recycle_view_favorite);
        layoutManager = new LinearLayoutManager(getContext());
        myRecycle.setLayoutManager(layoutManager);
    }





    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {


//        Parcelable[] list_parcelable = new Parcelable[list_displayed.size()];
//        for (int i=0; i<list_displayed.size(); i++)
//        {
//            list_parcelable[i] = (Parcelable) list_displayed.get(i);
//        }
        super.onSaveInstanceState(outState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("On destroy","this is in the onDestroy");

    }

    public void addItem(BookMyCollection myBook)
    {
        try
        {
            list_displayed.add(myBook);
            myRecycle.getAdapter().notifyItemInserted(list_displayed.size());
            myRecycle.smoothScrollToPosition(list_displayed.size());

        } catch (NullPointerException e)
        {

        }
    }

    public void deleteItem(BookMyCollection myBook) {

        int i=0;
        for (int j=0; j<list_displayed.size(); j++)
        {

            if (list_displayed.get(j)== myBook)
            {

                list_displayed.remove(list_displayed.get(j));
                i=j;
            }

        }
        madapter.notifyItemRemoved(i);
        madapter.notifyItemRangeChanged(i, list_displayed.size());
    }



}
