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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterManualMode;
import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSelectSong;
import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSongVersion2;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Objets.DescriptionAtmosphere;
import com.example.clement.readingmood.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Manuel extends AppCompatActivity {

    private Toolbar myToolBar;
    private RecyclerView myRecycle;
    private AdapterManualMode madapter;
    private ArrayList<Atmosphere> listAtmosphere;
    private FloatingActionButton addButton;
    static final int requestCode = 1;

    private final String[] listTitle = {"Administration", "Anger", "Attic", "Bathroom",
            "Bedroom", "Calm", "Car", "Castle", "Cave", "Cemetery",
            "Church", "Circus", "Company", "Country Town", "Desert", "Fear",
            "Field", "Fight", "Fog", "Forest", "Garden", "Gunshot", "Gym", "Happy", "Hospital", "Industry", "Jail",
            "Kitchens", "Library", "LivingRoom", "Love", "Meadow", "Mine", "Mountain", "Ocean",
            "Plane", "Rain", "Sad", "School", "Snow", "Sun","Street" ,"Thunder", "TrainStation", "Wind"};
    private ArrayList<Integer> list_available = new ArrayList<Integer>();
    private String pathToUrl;
    private boolean isFocus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manuel);

        if (getIntent().getStringExtra("path") !=null)
        {
            pathToUrl = getIntent().getStringExtra("path");
        }
        if (getIntent().getStringExtra("focus") != null)
        {
            isFocus = true;
        }


        list_available.add(0);

        configurizeToolBar();

        myRecycle = findViewById(R.id.recycle_view_manual_mode);
        addButton = findViewById(R.id.floatingAddManual);

        initialiseForTest();

        initialiseListener();



        madapter = new AdapterManualMode(listAtmosphere, this);

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
        myToolBar = findViewById(R.id.my_toolbar_manual);
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


    private boolean containInt(int i)
    {
        for (int x : list_available)
        {
            if (i==x)
            {
                return true;
            }

        }
        return false;
    }


    private void initialiseForTest() {

        listAtmosphere = new ArrayList<Atmosphere>();
        TypedArray atmosphere_ressource = getResources().obtainTypedArray(R.array.atmosphere_liste);
        TypedArray atmosphere_songs = getResources().obtainTypedArray(R.array.atmosphere_song);
        int m = listTitle.length;
        for (int i = 0; i < m; i++) {

            if (containInt(i))
            {
                listAtmosphere.add(new Atmosphere(listTitle[i], atmosphere_ressource.getResourceId(i, 0),
                        DescriptionAtmosphere.createDescription().get(i), atmosphere_songs.getResourceId(i,0)));

            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    /*
    Create the menu on the toolbar
     */ {
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
                Toast.makeText(getApplicationContext(), "Play selected", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(this, ManualSmell.class);

                Bundle myBundle = new Bundle();
                ArrayList<Parcelable> listToPass = new ArrayList<Parcelable>();
                for (Atmosphere x : listAtmosphere)
                {
                    listToPass.add((Parcelable) x  );
                }
                myBundle.putParcelableArrayList("resultFromManual", listToPass);
                myBundle.putString("path", pathToUrl);
                myIntent.putExtras(myBundle);

                if (isFocus)
                {
                    setResult(1,myIntent);
                    finish();
                } else
                {

                    startActivity(myIntent);
                }

                return true;

        }

        return super.onOptionsItemSelected(menuItem);
    }


    private void initialiseListener() {

        final Context myContext = Manuel.this;
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(myContext, ChooseAtmosphereManual.class);


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
                ArrayList<? extends Atmosphere> listToAdd = myBundle.getParcelableArrayList("resultForManual");
                int n = listAtmosphere.size();
                for (int i=0; i<listToAdd.size(); i++)
                {
                    listAtmosphere.add(listToAdd.get(i));

                }
                madapter = new AdapterManualMode(listAtmosphere, this);
                myRecycle.setAdapter(madapter);

            }


        }


    }
}