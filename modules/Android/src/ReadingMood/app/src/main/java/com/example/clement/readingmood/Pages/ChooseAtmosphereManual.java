package com.example.clement.readingmood.Pages;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSelectSong;
import com.example.clement.readingmood.Adapters.CollectionAdapter.MyAdapterMyCollection;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Objets.DescriptionAtmosphere;
import com.example.clement.readingmood.R;

import java.util.ArrayList;

public class ChooseAtmosphereManual extends AppCompatActivity implements AdapterSelectSong.ActionSelect, AdapterSelectSong.ActionSelectToDisplay {

    private final String[] listTitle ={ "Administration", "Anger", "Attic", "Bathroom",
            "Bedroom", "Calm", "Car", "Castle", "Cave", "Cemetery",
            "Church", "Circus","Company", "Country Town","Desert", "Fear",
            "Field", "Fight","Fog", "Forest", "Garden", "Gunshot","Gym","Happy","Hospital", "Industry", "Jail",
            "Kitchens", "Library", "LivingRoom", "Love","Meadow", "Mine", "Mountain","Ocean",
            "Plane","Rain", "Sad", "School", "Snow","Street","Sun", "Thunder", "TrainStation", "Wind"};
    /*
    List of atmosphere to display
     */

    private RecyclerView myRecycle;
    /*
    Recycle View
     */
    private ArrayList<Atmosphere> listAtmosphere;
    /*
    List of the atmosphere that will be displayed
     */
    private AdapterSelectSong madapter;

    private Context myContext;

    private Toolbar myToolBar;

    private ArrayList<Atmosphere> listToTransmit;

    private TextView listAtmospereToDisplay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_atmosphere_manual);
        myContext = ChooseAtmosphereManual.this;
        myRecycle = findViewById(R.id.recycle_view_select_songs);
        listAtmospereToDisplay = findViewById(R.id.selected_manual_songs);

        initializeList();


        madapter = new AdapterSelectSong(listAtmosphere, myContext);
        madapter.makeActionSelecteed(this);
        madapter.setSecondAction(this);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayout = new GridLayoutManager(getApplicationContext(),
                2, GridLayoutManager.VERTICAL, false);

        myRecycle.setLayoutManager(gridLayout);

        myRecycle.setAdapter(madapter);

        configurizeToolBar();

        listToTransmit = new ArrayList<Atmosphere>();
    }

    private void initializeList()
    /*
    Initialise the list of recycleView
     */
    {

        listAtmosphere = new ArrayList<Atmosphere>() ;
        TypedArray atmosphere_ressource = getResources().obtainTypedArray(R.array.atmosphere_liste);
        TypedArray atmosphere_song = getResources().obtainTypedArray(R.array.atmosphere_song);
        int m = listTitle.length;
        for (int i=0; i<m ; i++)
        {


                listAtmosphere.add(new Atmosphere(listTitle[i], atmosphere_ressource.getResourceId(i,0),
                        DescriptionAtmosphere.createDescription().get(i),atmosphere_song .getResourceId(i,0)));


        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    /*
    Create the menu on the toolbar
     */
    {
        getMenuInflater().inflate(R.menu.menu_select_songs,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    /*
    Add action on the icon of the toolbar
     */
    {
        int id = menuItem.getItemId();
        if(id == R.id.menu_play_selected_songs)
        {
            linkToPreviousPage();
                return true;

        }

        return super.onOptionsItemSelected(menuItem);
    }

    private void configurizeToolBar()
    /*
    Initialise the toolbar and add the backbutton actioon
     */
    {
        myToolBar = findViewById(R.id.my_toolbar_atmosphere_select);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
            myToolBar.setNavigationIcon(R.drawable.back_button);
            myToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linkToPreviousPage();
                }
            });

    }

    @Override
    public void addSong(Atmosphere myAtmopshere) {

        listToTransmit.add(myAtmopshere);
//        Toast.makeText(getApplicationContext(), myAtmopshere.getTitle()+" is added",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteSong(Atmosphere myAtmosphere) {
        listToTransmit.remove(myAtmosphere);
//        Toast.makeText(getApplicationContext(), myAtmosphere.getTitle()+"is deleted",Toast.LENGTH_SHORT).show();
    }




    private void linkToPreviousPage()
    {
        Intent intent = new Intent();
        Bundle myBundle = new Bundle();
        ArrayList<Parcelable> listToPass = new ArrayList<Parcelable>();
        for (Atmosphere x : listToTransmit)
        {
            listToPass.add((Parcelable) x  );
        }
        myBundle.putParcelableArrayList("resultForManual", listToPass);
        intent.putExtras(myBundle);
        setResult(Manuel.requestCode,intent);
        finish();

    }


    @Override
    public void updateAtmosphere() {
        String toDisplay = "";
        for (Atmosphere x : listToTransmit)
        {
            toDisplay += x.getTitle() + ", ";
        }
        listAtmospereToDisplay.setText(toDisplay);
    }
}
