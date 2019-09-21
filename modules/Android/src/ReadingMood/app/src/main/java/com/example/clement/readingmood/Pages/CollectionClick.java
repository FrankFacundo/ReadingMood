package com.example.clement.readingmood.Pages;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.example.clement.readingmood.Fragments.FragmentCollection.FragmentCollectionClick;
import com.example.clement.readingmood.R;

public class CollectionClick extends AppCompatActivity {

    private Toolbar myToolBar;



    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_click);
        /*
        Set the layout where will be the page
         */
        Bundle monIntent = getIntent().getExtras();
        /*
        Get the intent with the extra given by the previous page
         */
        FragmentCollectionClick fragment;
        /*
        Fragment that will be displayed
         */
        if (monIntent!=null)
        {
            /*
            Get the extra from the intent
             */

            String bookTitle = monIntent.getString("title");
            String bookAuthor = monIntent.getString("author");
            String bookSummary = monIntent.getString("summary");
            int pathImage = monIntent.getInt("image");
            String pathToRead = monIntent.getString("path");
            fragment = FragmentCollectionClick.create(bookTitle,bookAuthor,bookSummary, pathImage, pathToRead);

        }else
        {
            fragment = new FragmentCollectionClick();
        }
        configurizeToolBar();
        /*
        Makes the fragment active in this page
         */
        getSupportFragmentManager().beginTransaction().add(R.id.layout_fragment_collection_click
            ,fragment).commit();



    }


    private void configurizeToolBar()
    /*
    Initialisation of the toolbar and add the listener
     */
    {
        myToolBar = findViewById(R.id.my_toolbar_my_collection_click);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        myToolBar.setNavigationIcon(R.drawable.back_button);
        myToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }




}
