package com.example.clement.readingmood.Pages;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.clement.readingmood.Fragments.FragmentAtmosphere.FragmentAtmosphereClick;
import com.example.clement.readingmood.Fragments.FragmentOther.FragmentDescription;
import com.example.clement.readingmood.Fragments.FragmentOther.FragmentPlayer;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.R;

import java.util.ArrayList;

public class ClickAtmospherePage extends AppCompatActivity implements FragmentPlayer.TransmitActivity {

    private Atmosphere myAtmosphere;
    private Toolbar myToolBar;
    private FloatingActionButton currentPlay;
    private FragmentAtmosphereClick fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_atmosphere_page);
        Intent monIntent = getIntent();
        FragmentDescription fragmentDescription;
        if (monIntent!=null)
        {
            /*
            Get the extra from the intent
             */
            Bundle extras = monIntent.getExtras();
            ArrayList<String> myDescriptions = extras.getStringArrayList("description");
            myAtmosphere = extras.getParcelable("atmosphere");
            fragment = FragmentAtmosphereClick.create(myDescriptions, myAtmosphere);
            fragmentDescription = FragmentDescription.create(myDescriptions);

        }else
        {
            fragment = new FragmentAtmosphereClick();
            fragmentDescription = new FragmentDescription();
        }
    /*
        Makes the fragment active in this page
         */
        getSupportFragmentManager().beginTransaction().add(R.id.layout_atmosphere_clicked
                ,fragment).commit();


        getSupportFragmentManager().beginTransaction().add(R.id.layout_to_inflate_description
                ,fragmentDescription).commit();

    }




    @Override
    public void makePlay(MediaPlayer media) {


        fragment.getPlayButton().setImageResource(R.drawable.pause_atmosphere);
    }

    @Override
    public void makePause(MediaPlayer media) {

        fragment.getPlayButton().setImageResource(R.drawable.play_button_atmosphere);

    }

}
