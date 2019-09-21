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
import android.widget.Toast;

import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSelectSmell;
import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSelectSong;
import com.example.clement.readingmood.Adapters.CollectionAdapter.MyAdapterMyCollection;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Objets.DescriptionAtmosphere;
import com.example.clement.readingmood.R;

import java.util.ArrayList;

public class ChooseAtmosphereManualSmell extends AppCompatActivity implements AdapterSelectSmell.ActionSelectSmell {

    private final String[] listTitle ={ "Agricultural field","Christmas", "Cook","Forest","Floral garden"
            ,"Ocean"};
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
    private AdapterSelectSmell madapter;

    private Context myContext;

    private Toolbar myToolBar;

    private ArrayList<Atmosphere> listToTransmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_atmosphere_manual_smell);
        myContext = ChooseAtmosphereManualSmell.this;
        myRecycle = findViewById(R.id.recycle_view_select_smells);

        initializeList();


        madapter = new AdapterSelectSmell(listAtmosphere, myContext);
        madapter.makeActionSelecteed(this);


        GridLayoutManager gridLayout = new GridLayoutManager(getApplicationContext(),
                2, GridLayoutManager.VERTICAL, false);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
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
        TypedArray atmosphere_ressource = getResources().obtainTypedArray(R.array.atmosphere_liste_smell);
        int m = listTitle.length;
        for (int i=0; i<m ; i++)
        {
            listAtmosphere.add(new Atmosphere(listTitle[i], atmosphere_ressource.getResourceId(i,0),
                    DescriptionAtmosphere.createDescription().get(i),0));
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    /*
    Create the menu on the toolbar
     */
    {
        getMenuInflater().inflate(R.menu.menu_select_smells,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    /*
    Add action on the icon of the toolbar
     */
    {
        int id = menuItem.getItemId();
        if(id == R.id.menu_play_selected_smells)
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
        myToolBar = findViewById(R.id.my_toolbar_atmosphere_select_smell);
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
    public void addSongSmell(Atmosphere myAtmopshere) {

        listToTransmit.add(myAtmopshere);
//        Toast.makeText(getApplicationContext(), myAtmopshere.getTitle()+" is added",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteSongSmell(Atmosphere myAtmosphere) {
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
        myBundle.putParcelableArrayList("resultForManualSmell", listToPass);
        intent.putExtras(myBundle);
        setResult(ManualSmell.requestCode,intent);
        finish();

    }


}
