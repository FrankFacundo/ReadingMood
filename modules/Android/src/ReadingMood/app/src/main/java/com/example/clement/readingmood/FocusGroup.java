package com.example.clement.readingmood;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clement.readingmood.Fragments.FragmentResume.FragmentPlayResume;
import com.example.clement.readingmood.Fragments.FragmentResume.FragmentPlaySmell;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Objets.DescriptionAtmosphere;
import com.example.clement.readingmood.Pages.ChooseAtmosphereManual;
import com.example.clement.readingmood.Pages.Manuel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.epub.EpubReader;

public class FocusGroup extends AppCompatActivity {

    private Toolbar myToolBar;
    private Button next;
    private TextView currentIndex, pourcentage;
    private int compteur = 0;
    private Menu myMenu;
    private FragmentPlayResume myDialogFrag;
    private FragmentPlaySmell myDialogSmell;
    private ArrayList<Atmosphere> atmosphereToDisplay = new ArrayList<>();
    private final String[] listTitle ={ "Admnistration", "Anger", "Attic", "Bathroom",
            "Bedroom", "Calm", "Car", "Castle", "Cave", "Cemetery",
            "Church", "Circus","Company", "Country Town","Desert", "Fear",
            "Field", "Fight","Fog", "Forest", "Garden", "Gunshot","Gym","Happy","Hospital", "Industry", "Jail",
            "Kitchens", "Library", "LivingRoom", "Love","Meadow", "Mine", "Mountain","Ocean",
            "Plane","Rain", "Sad", "School", "Snow","Street","Sun", "Thunder", "TrainStation", "Wind"};
    private int[] list_int_available = { 0, 2 , 3 , 6, 8,9,10,14,16,22,39,40};
    private ArrayList<Atmosphere> listAtmosphere, listAtmosphereSmell ;
    private final String[] listTitleSmell ={ "Agricultural field","Christmas", "Cook","Forest","Floral garden"
            ,"Ocean"};

    private WebView myWeb ;
    private  String[] book_url ;
    private TypedArray book_input;
    private InputStream myInpput;
    private Book myBook;
    private boolean isChristmas ;

    private boolean isAvailable(int i)
    {
        for (int j : list_int_available)
        {
            if (i == j)
            {
                return true;
            }
        }
        return false;
    }

    private void createSong()
    {

        listAtmosphere = new ArrayList<Atmosphere>() ;
        listAtmosphereSmell = new ArrayList<Atmosphere>() ;
        TypedArray atmosphere_ressource = getResources().obtainTypedArray(R.array.atmosphere_liste);
        TypedArray atmosphere_song = getResources().obtainTypedArray(R.array.atmosphere_song);
        int m = listTitle.length;
        for (int i=0; i<m ; i++)
        {
            if ( isAvailable(i) )
            {
                listAtmosphere.add(new Atmosphere(listTitle[i], atmosphere_ressource.getResourceId(i,0),
                        DescriptionAtmosphere.createDescription().get(i),atmosphere_song .getResourceId(i,0)));
            }

        }

        TypedArray atmosphere_ressource_smell = getResources().obtainTypedArray(R.array.atmosphere_liste_smell);
        int n = listTitleSmell.length;
        for (int i=0; i<n ; i++)
        {
            listAtmosphereSmell.add(new Atmosphere(listTitle[i], atmosphere_ressource.getResourceId(i,0),
                    DescriptionAtmosphere.createDescription().get(i),0));
        }


    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_group);
        configurizeToolBar();
        next = findViewById(R.id.next_button_focus);
        currentIndex = findViewById(R.id.index_page_focus);
        pourcentage = findViewById(R.id.pourcentage_resume_focus);
        myWeb = findViewById(R.id.web_view_focus);
        createSong();


        Intent myIntent = getIntent();
        if (myIntent.hasExtra("focusGroup"))
        {
            isChristmas = myIntent.getStringExtra("focusGroup").equals("c");
        }
        updatePage();
        if (isChristmas)
        {
            atmosphereToDisplay.add(listAtmosphere.get(10));
        }else
        {
            atmosphereToDisplay.add(listAtmosphere.get(9));
        }
        startDialogPlay();
        startDialogSmell();

    }


    private void configurizeToolBar()
    /*
    Initialise the toolbar and add the backbutton action
     */ {
        myToolBar = findViewById(R.id.toolbar_focus_group);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        Bundle myBundle = data.getExtras();
        atmosphereToDisplay = myBundle.getParcelableArrayList("resultFromManual");
        FragmentManager ft = getSupportFragmentManager();
//        myDialogFrag = FragmentPlayResume.createFrag(null);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void next_focus(View view) {
         compteur++;
         switch (compteur)
         {
             case 1 :
                 Intent intent = new Intent(this, Manuel.class);
                 intent.putExtra("focus","quidditch");
                 startActivityForResult(intent, 0);
                 currentIndex.setText("2 / 3");
                 pourcentage.setText("66%");
                 myDialogFrag.clearSongs();
                 break;
             case 2 :
                 currentIndex.setText("3 / 3");
                 pourcentage.setText("99%");
                 myDialogFrag.clearSongs();
                 break;

             case 3 :
                 Toast.makeText(view.getContext(), "Merci d'avoir participé à ce focus groupe ! " +
                         "Veuillez récupérer le questionnaire !", Toast.LENGTH_SHORT).show();
                 myDialogFrag.clearSongs();
                 finish();
                 compteur = 0;
                 break;
         }
         updatePage();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    /*
    Create the menu on the toolbar
     */ {
        myMenu = menu;
        getMenuInflater().inflate(R.menu.menu_resume, menu);
        return true;
    }


    private void startDialogPlay()
    {
        FragmentManager ft = getSupportFragmentManager();
//        myDialogFrag = FragmentPlayResume.createFrag(atmosphereToDisplay);
        myDialogFrag.show(ft, "Current play");
        myDialogFrag.setRetainInstance(true);

    }

    private void startDialogSmell()
    {

        FragmentManager ft = getSupportFragmentManager();
        myDialogSmell = FragmentPlaySmell.createFrag(listAtmosphereSmell);
//        myDialogSmell.show(ft, "Current play smelled");
        myDialogSmell.setRetainInstance(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    /*
    Add action on the icon of the toolbar
     */ {
        int id = menuItem.getItemId();
        switch(id )
        {
            case R.id.display_media_resume_manual :
                if (compteur !=2 )
                {
                    FragmentManager ft = getSupportFragmentManager();
                    myDialogFrag.show(ft, "Current play");
                }
                return true;

            case R.id.resume_smell :
                Toast.makeText(getApplicationContext(), "Smell selected", Toast.LENGTH_SHORT).show();
                FragmentManager ft1 = getSupportFragmentManager();
                myDialogSmell.show(ft1, "Current play Smell");
                return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }


    private void updatePage()
    {
        book_input = getResources().obtainTypedArray(R.array.list_name_book_input);
        book_url = getResources().getStringArray(R.array.list_name_book_url);
        if (!isChristmas)
        {
            myInpput = getResources().openRawResource(book_input.getResourceId(compteur+3,0));
        }else
        {
            myInpput = getResources().openRawResource(book_input.getResourceId(compteur,0));
        }

//
        try
        {
            myBook = (new EpubReader()).readEpub(myInpput);
            String baseUrl;
            if (! isChristmas)
            {
                Log.e("Test chrismtas","good");
                baseUrl = "file:///android_res/raw/"+ book_url[compteur+ 3]  +".xhtml";
            }else
            {
                Log.e("Test chrismtas","not good");
                baseUrl = "file:///android_res/raw/"+ book_url[compteur]  +".xhtml";
            }

            myWeb.loadUrl(baseUrl);

        } catch (IOException e )
        {
            Log.e("Error","Error to read book");
        }
    }


}
