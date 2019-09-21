package com.example.clement.readingmood.Pages;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.clement.readingmood.Adapters.MyAdapterFragment;
import com.example.clement.readingmood.Fragments.FragmentAtmosphere.SongFragment;
import com.example.clement.readingmood.Fragments.FragmentAtmosphere.SmellFragment;
import com.example.clement.readingmood.R;

import java.util.ArrayList;


public class ListAtmosphere extends AppCompatActivity {

    private Toolbar myToolBar;
    /*
    Toolbar
     */
    private MyAdapterFragment adapter;
    /*
    Adapter for the tabLayout
     */
    private ViewPager viewPager;
    /*
    ViewPager for the tabLayout
     */
    private TabLayout tabLayout;
    /*
    TabLayout
     */
    private ArrayList<Integer> listForManual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atmosphere_page);
        /*
        Initialise the tabLayout
         */

        viewPager = findViewById(R.id.view_pager_atmosphere_id);
        /*
        Initialise the adapter
         */
        adapter = new MyAdapterFragment(getSupportFragmentManager());
        /*
        Add the two fragments for the tabLayout
         */
        adapter.addFragment(new SongFragment(), "Song");
        adapter.addFragment(new SmellFragment(), "Smell");

        viewPager.setAdapter(adapter);


        tabLayout = findViewById(R.id.tab_layout_id);

        tabLayout.setupWithViewPager(viewPager);


        this.configurizeToolBar();

        if (getIntent().getBooleanExtra("isSmell",false))
        {
            viewPager.setCurrentItem(1);
        }

        listForManual = new ArrayList<Integer>();

    }

    private void configurizeToolBar()
    /*
    Initialisation of the toolbar and add the listener
     */ {
        myToolBar = findViewById(R.id.my_toolbar_atmosphere);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    /*
    Add the menu
     */ {

        return true;
    }

}