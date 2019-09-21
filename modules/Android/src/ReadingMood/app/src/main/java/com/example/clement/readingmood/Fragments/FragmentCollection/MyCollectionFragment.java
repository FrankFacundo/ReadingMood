package com.example.clement.readingmood.Fragments.FragmentCollection;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clement.readingmood.Adapters.CollectionAdapter.MyAdapterMyCollection;
import com.example.clement.readingmood.Objets.BookMyCollection;
import com.example.clement.readingmood.SQLite.DataBaseMyCollection;
import com.example.clement.readingmood.Singletons.SingletonDataBaseMyColletion;
import com.example.clement.readingmood.R;

import java.util.ArrayList;

public class MyCollectionFragment extends Fragment {

    private ArrayList<BookMyCollection> list_displayed = new ArrayList<BookMyCollection>();
    /*
    List of Book (that is a JavaClass) that will be displayed in the recycleView
     */

    View v;

    private RecyclerView myRecycle;
    /*
    Recycle View
     */
    private MyAdapterMyCollection madapter;
    /*
    Adapter for the recycle view
     */


    private DataBase myData;

    private DataBaseMyCollection db;

    public MyCollectionFragment() {    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_my_collection, container, false);

        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        myRecycle = view.findViewById(R.id.recycle_view_collection);
        /*
        Initialise the adapter
         */

        db = SingletonDataBaseMyColletion.getInstance().getDataBase();

        try
        {
            getDataFromDataBase();
        } catch (RuntimeException e)
        {
            onStop();
        }
//        list_displayed = SingletonDataBaseMyColletion.getInstance().getDataBase().getAllData();
        madapter = new MyAdapterMyCollection(list_displayed, getContext());
        madapter.makeCreation((MyAdapterMyCollection.CreateFragment) getActivity()  );

        /*
        Display the recycleView with a linearLayoutManager
         */
        myRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycle.setAdapter(madapter);
    }

    public interface DataBase
    {
        public boolean insertData(ArrayList<BookMyCollection> liste);
    }

    public void addElementBase( DataBase myData)
    {
        this.myData = myData;
    }


    public void getDataFromDataBase()

    {
        list_displayed = new ArrayList<>();
        Cursor data = db.getAllData();
        if (data.getCount() ==0)
        {
            Log.e("Error","No data found");
        }
        else
        {
//            data.moveToFirst();
                while (data!=null && data.moveToNext())
                {

                    String id = data.getString(0);
                    String title = data.getString(1);
                    String author = data.getString(2);
                    int link_image = data.getInt(3);
                    String summary =data.getString(4);
                    String pathtoread =data.getString(5);
                    String content =data.getString(6);
                    String listesong =data.getString(7);
                    String listesmell =data.getString(8);
                    String listindexes = data.getString(9);
                    int currentPage = data.getInt(10);
                    BookMyCollection new_book = new BookMyCollection(title,author,link_image,summary,
                            pathtoread, DataBaseMyCollection.makeListToArray(listesong), null,
                            DataBaseMyCollection.makeStringToArrayInteger(listindexes),
                            DataBaseMyCollection.makeListToArray(content),currentPage);
                    list_displayed.add(new_book);

                }


        }
    }



    public void deleteAll() {
        db.deleteAll();
    }


    private void showMessage(String title, String message)
    {
        AlertDialog.Builder builder  = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
