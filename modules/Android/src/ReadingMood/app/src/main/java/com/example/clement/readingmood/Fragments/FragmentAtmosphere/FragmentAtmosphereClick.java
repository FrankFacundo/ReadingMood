package com.example.clement.readingmood.Fragments.FragmentAtmosphere;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.clement.readingmood.Fragments.FragmentOther.FragmentPlayer;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.R;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class FragmentAtmosphereClick extends Fragment {


    private View v;
    private TextView textTitleAtmosphere, descripitonTitle;
    private LinearLayout layout_for_description;
    private ImageView atmosphereImage;
    private FloatingActionButton playButton,stopButton;
    private FrameLayout layoutToPlayString;
    private MediaPlayer myMedia;
    private Toolbar myToolBar;
    private int pathToSong;
    private Atmosphere myAtmosphere;
    private FragmentPlayer myFragmentPlayer=null;

    public static FragmentAtmosphereClick create(ArrayList<String> description, Atmosphere myAtmosphere)
    /*
    static factory method
     */
    {
        Bundle args = new Bundle();
        args.putStringArrayList("description",description);
        args.putParcelable("atmosphere", myAtmosphere);
        FragmentAtmosphereClick fragment = new FragmentAtmosphereClick();
        fragment.setArguments(args);
        return fragment;

    }
    public FragmentAtmosphereClick(){}


    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        Bundle argument = getArguments();
        this.v = inflater.inflate(R.layout.fragment_atmosphere_click, container, false);
        configurizeToolBar();
        textTitleAtmosphere = v.findViewById(R.id.fragment_title_atmosphere);
        atmosphereImage = v.findViewById(R.id.atmosphere_click_fragment);
        playButton = v.findViewById(R.id.play_button_clicked_atmosphere);
        layoutToPlayString = v.findViewById(R.id.play_media_center_atmosphere);
        layout_for_description = v.findViewById(R.id.layout_to_inflate_description);
        stopButton = v.findViewById(R.id.stop_button_clicked_atmosphere);
        descripitonTitle = v.findViewById(R.id.description);

        stopButton.setVisibility(View.GONE);
        addOnClicks();
        ArrayList<String> myDescriptions = new ArrayList<String>();
        if (argument!=null)
        {
            myAtmosphere = argument.getParcelable("atmosphere");
            textTitleAtmosphere.setText(myAtmosphere.getTitle());
            Glide.with(atmosphereImage.getContext()).load( myAtmosphere.getImageRessource()).into(atmosphereImage);
            myDescriptions = argument.getStringArrayList("description");
            pathToSong = myAtmosphere.getPathToSong();
        }
        descripitonTitle.setText("Texts related to " + myAtmosphere.getTitle());



        return v;
    }


    private void addOnClicks()
    {
        playButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                if (pathToSong !=0)
                {


//                    myMedia = MediaPlayer.create(getContext(), pathToSong);
//                myMedia.start();
//                Log.e("start media","play is clicked");
                    Toast.makeText(getContext(), "Play " + textTitleAtmosphere.getText().toString() +" is playing",Toast.LENGTH_SHORT).show();

                    playButton.setImageResource(R.drawable.pause_atmosphere);
                    if (myFragmentPlayer !=null)
                    {
                        myFragmentPlayer.actionWhenClick();
                    }else
                    {

                        myFragmentPlayer = FragmentPlayer.create(pathToSong, myAtmosphere);
                        myFragmentPlayer.makeTransmit((FragmentPlayer.TransmitActivity) getActivity());
                        getFragmentManager().beginTransaction().add(R.id.play_media_center_atmosphere
                                ,myFragmentPlayer).commit();

                    }
                stopButton.setVisibility(View.VISIBLE);

                }
                else
                {


                    final Dialog myDialog = new Dialog(getContext());

                    myDialog.setContentView(R.layout.dialog_song_not_found);

                    TextView okNotFounded = myDialog.findViewById(R.id.ok_song_not_found);

                    okNotFounded .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                          myDialog.hide();
                        }
                    });


                    myDialog.show();

                }
            }

                });


        stopButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {

                stopButton.setVisibility(View.GONE);

                if (myFragmentPlayer!=null)
                {
                    getFragmentManager().beginTransaction().remove(myFragmentPlayer).commit();
                    playButton.setImageResource(R.drawable.play_button_atmosphere);
                    myFragmentPlayer = null;
                }
            }
        });


    }






    private void configurizeToolBar()
    /*
    Initialise the toolbar and add the backbutton actioon
     */
    {
        myToolBar = v.findViewById(R.id.toolbar_header_clicked_version_2);
        myToolBar.setNavigationIcon(R.drawable.back_button);
        myToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });



    }

    public FloatingActionButton getPlayButton()
    {
        return playButton;
    }

}
