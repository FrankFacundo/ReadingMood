package com.example.clement.readingmood.Fragments.FragmentNavigator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.clement.readingmood.Fragments.FragmentAtmosphere.SmellFragmentVersion2;
import com.example.clement.readingmood.Fragments.FragmentAtmosphere.SongFragmentVersion2;
import com.example.clement.readingmood.Fragments.FragmentCollection.MyCollectionFragmentVersion2;
import com.example.clement.readingmood.Fragments.FragmentLibrary.FragmentAuthorVersion2;
import com.example.clement.readingmood.Fragments.FragmentLibrary.FragmentBookVersion2;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Objets.BookMyCollection;
import com.example.clement.readingmood.Pages.MainPage;
import com.example.clement.readingmood.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FragmentDiscoverPage extends Fragment {

    private FragmentManager myFragmentManager;
    private MyCollectionFragmentVersion2 fragFav ;
    private FragmentDiscoverPage.baseIntents myBase;
    int compteur = 0;

    // To test
    private Button testDataBase, deleteDataBase, addDataBase, deleteAllDataBase ;

    public FragmentDiscoverPage() {
        // Required empty public constructor
    }

    public void addFragmentManager(FragmentManager myFragmentManager)
    {
        this.myFragmentManager = myFragmentManager;

    }

    public void addBaseIntent(FragmentDiscoverPage.baseIntents base)
    {
        this.myBase = base;
    }

    public interface baseIntents
    {
        public void add(BookMyCollection book);
        public void delete(String title);
        public void displayAll();
        public void deleteAll();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.discover_page_v_2, container, false);

        final SongFragmentVersion2 fragmentListAtmosphere = new SongFragmentVersion2 ();


        fragFav = new MyCollectionFragmentVersion2();




        myFragmentManager.beginTransaction().add(R.id.layout_atmosphere_version_2_recycle
                ,fragmentListAtmosphere).commit();

        myFragmentManager.beginTransaction().add(R.id.layout_atmosphere_smell_version_2,
                new SmellFragmentVersion2()).commit();

        myFragmentManager.beginTransaction().add(R.id.layout_collection_version_2,
                fragFav).commit();

//        myFragmentManager.beginTransaction().add(R.id.layout_book_to_download_discover,
//                new FragmentBookVersion2()).commit();
//        myFragmentManager.beginTransaction().add(R.id.layout_author_discover,
//                new FragmentAuthorVersion2()).commit();

        return v;
    }


    public void update()
    {
        if (fragFav !=null)
        {
            fragFav.updateData();
        }
    }

    public void addBook(BookMyCollection newBook)
    {
        if (fragFav != null)
        {
            fragFav.addBook(newBook);
        }
    }

    public void addBooks(ArrayList<BookMyCollection> newBooks)
    {
        if (fragFav != null)
        {

            for (BookMyCollection x : newBooks)
            {
                Log.e("FragmentDiscover", String.valueOf(x.getListAllTextToDisplay() == null));
                fragFav.addBook(x);
            }
        }


    }



    public void deleteBook(BookMyCollection oldBook)
    {
        fragFav.deleteCollection(oldBook);
    }



}