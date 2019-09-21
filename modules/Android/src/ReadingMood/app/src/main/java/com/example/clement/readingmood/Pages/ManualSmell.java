package com.example.clement.readingmood.Pages;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterManualMode;
import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterManualModeSmell;
import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSelectSong;
import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSongVersion2;
import com.example.clement.readingmood.FocusGroup;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Objets.DescriptionAtmosphere;
import com.example.clement.readingmood.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ManualSmell extends AppCompatActivity {

    private Toolbar myToolBar;
    private RecyclerView myRecycle;
    private AdapterManualModeSmell madapter;
    private ArrayList<Atmosphere> listAtmosphere;
    private FloatingActionButton addButton;
    static final int requestCode = 1;
    private ArrayList<Atmosphere> listFromSong;
    private Menu menu;
    private String pathToUrl;
    private boolean isFocus = false;

    private final String[] listTitle = { "Agricultural field","Christmas", "Cook","Forest","Floral garden"
            ,"Ocean"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_smell);

        configurizeToolBar();
        myRecycle = findViewById(R.id.recycle_view_manual_mode_smell);
        addButton = findViewById(R.id.floatingAddManual_smell);
        initialiseForTest();
        initialiseListener();

        if (getIntent().getExtras()!=null)
        {
            pathToUrl = getIntent().getStringExtra("path");
            listFromSong = getIntent().getParcelableArrayListExtra("resultFromManual");
        }
        if (getIntent().hasExtra("focus"))
        {
            isFocus = true;
        }

        madapter = new AdapterManualModeSmell(listAtmosphere, this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        myRecycle.setLayoutManager(layoutManager);
        myRecycle.setAdapter(madapter);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                ItemTouchHelper.DOWN | ItemTouchHelper.UP, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                int fromAtmosphere = viewHolder.getAdapterPosition();
                int toAtmosphere = target.getAdapterPosition();
                Collections.swap(listAtmosphere, fromAtmosphere, toAtmosphere);
                madapter.notifyItemMoved(fromAtmosphere, toAtmosphere);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                listAtmosphere.remove(viewHolder.getAdapterPosition());
                madapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            }
        });
        helper.attachToRecyclerView(myRecycle);

    }

    private void configurizeToolBar()
    /*
    Initialise the toolbar and add the backbutton actioon
     */ {
        myToolBar = findViewById(R.id.my_toolbar_manual_smell);
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



    private void initialiseForTest() {

        listAtmosphere = new ArrayList<Atmosphere>();
        TypedArray atmosphere_ressource = getResources().obtainTypedArray(R.array.atmosphere_liste_smell);
        int m = listTitle.length;
        for (int i = 0; i < m; i++) {

            listAtmosphere.add(new Atmosphere(listTitle[i], atmosphere_ressource.getResourceId(i, 0),
                        DescriptionAtmosphere.createDescription().get(i), 0));


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    /*
    Create the menu on the toolbar
     */ {
         this.menu = menu;
         getMenuInflater().inflate(R.menu.menu_manual, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    /*
    Add action on the icon of the toolbar
     */ {
        int id = menuItem.getItemId();
        if (id ==R.id.menu_play_manual)
        {
            MenuItem item = menu.findItem(R.id.menu_play_manual);
            item.setEnabled(false);
            Toast.makeText(getApplicationContext(), "Smells saved", Toast.LENGTH_SHORT).show();
            Intent myIntent ;


            myIntent = new Intent(this, Resume.class);
            Bundle myBundle = new Bundle();
            ArrayList<Parcelable> listToPass = new ArrayList<Parcelable>();
            for (Atmosphere x : listAtmosphere)
            {
                listToPass.add((Parcelable) x  );
            }
            myBundle.putParcelableArrayList("resultFromManualSmell", listToPass);
            myBundle.putParcelableArrayList("resultFromManual", listFromSong);
//            myBundle.putString("path",pathToUrl);
            myBundle.putBoolean("isManual", true);
            myIntent.putExtras(myBundle);
            startActivity(myIntent);


            return true;

        }

        return super.onOptionsItemSelected(menuItem);
    }


    private void initialiseListener() {

        final Context myContext = ManualSmell.this;
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(myContext, ChooseAtmosphereManualSmell.class);
                startActivityForResult(myIntent, requestCode);


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == this.requestCode) {
            Bundle myBundle = data.getExtras();
            if(myBundle !=null)
            {
                ArrayList<? extends Atmosphere> listToAdd = myBundle.getParcelableArrayList("resultForManualSmell");
                int n = listAtmosphere.size();
                for (int i=0; i<listToAdd.size(); i++)
                {
                    listAtmosphere.add(listToAdd.get(i));

                }
                madapter = new AdapterManualModeSmell(listAtmosphere, this);
                myRecycle.setAdapter(madapter);

            }


        }


    }
}