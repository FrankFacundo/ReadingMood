package com.example.clement.readingmood.Fragments.FragmentLibrary;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clement.readingmood.Adapters.LibraryAdapter.AdapterListAuthor;
import com.example.clement.readingmood.Adapters.LibraryAdapter.MyAdapterMyLibraryLetter;
import com.example.clement.readingmood.Adapters.LibraryAdapter.MyAdapterMyLibraryLetter2;
import com.example.clement.readingmood.Objets.BookMyLibrary;
import com.example.clement.readingmood.R;

import java.util.ArrayList;

public class FragmentBookVersion2 extends Fragment {

    private View v;

    private RecyclerView myRecycle;

    private ArrayList<BookMyLibrary> listBook;

    private MyAdapterMyLibraryLetter madapter;
    private MyAdapterMyLibraryLetter2 madapter2;

    private boolean isVertical = false;

    public FragmentBookVersion2() {

    }

    public static FragmentBookVersion2 create(ArrayList<BookMyLibrary> listBook, boolean isVertical)
    {
        FragmentBookVersion2 myFrag = new FragmentBookVersion2();
        Bundle myBundle = new Bundle();
        myBundle.putParcelableArrayList("listBook", listBook);
        myBundle.putBoolean("isVertical", isVertical);
        myFrag.setArguments(myBundle);
        return myFrag;
    }

    public static FragmentBookVersion2 create1(ArrayList<BookMyLibrary> listBook, boolean isVertical)
    {
        FragmentBookVersion2 myFrag = new FragmentBookVersion2();
        Bundle myBundle = new Bundle();
        myBundle.putParcelableArrayList("listBook", listBook);
        myBundle.putBoolean("isVertical", isVertical);
        myBundle.putBoolean("version",true);
        myFrag.setArguments(myBundle);
        return myFrag;
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*
        Initialise the view
         */
        v = inflater.inflate(R.layout.atmosphere_fragment_library_book, container, false);
        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        /*
        Initialise the adapter
         */
//        initializeList();

        Bundle myBundle = getArguments();
        if (myBundle !=null)
        {
            listBook = myBundle.getParcelableArrayList("listBook");
//            Log.e("FBookV2", listBook.get(0).getTitle());
            isVertical = myBundle.getBoolean("isVertical");
            //            Log.e("FBookV2", String.valueOf(isVertical));
            myRecycle = v.findViewById(R.id.recycler_view_library_horizontal_book);
            if (myBundle.getBoolean("version"))
            {
                madapter2 = new MyAdapterMyLibraryLetter2(listBook,getContext());
                myRecycle.setAdapter(madapter2);

            }else
            {
                madapter = new MyAdapterMyLibraryLetter(listBook,getContext());
                myRecycle.setAdapter(madapter);
            }
        /*
        Display the recycleView with a linearLayoutManager
         */
            LinearLayoutManager layoutManager;
            if (isVertical)
            {
                GridLayoutManager managerGrid = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
                myRecycle.setLayoutManager(managerGrid);
            }else
            {
                layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                myRecycle.setLayoutManager(layoutManager);
            }











        }else
        {
            Log.e("FBookv2", "bundle null");
            initializeList();
        }




    }

    private void initializeList()
    /*
    Initialise the list of recycleView
     */
    {
        listBook = new ArrayList<>();
        for (int i = 0; i<10; i++)
        {
            listBook.add(new BookMyLibrary("Title" + i,"Author" + i,"Date" + i,"ok"));

        }

    }





}
