package com.example.clement.readingmood.Fragments.FragmentResume;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.clement.readingmood.Fragments.FragmentOther.ConverterMediaPlayer;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Objets.AtmosphereByName;
import com.example.clement.readingmood.R;

import java.util.ArrayList;

public class FragmentPlayAutomatic extends DialogFragment {

    private ImageView imageDisplay;

    private CardView myCard;
    private TextView title;
    public static String TAG = "FragmentPlayAutomatic";
    private ArrayList<Integer> indexChangement;
    private ImageButton play;
    private PlayAutomatic actionAct ;
    private Atmosphere currentAtmosphere;
    private boolean isPlaying = false;

    public void setPlaying(boolean val)
    {
        this.isPlaying = val;

    }

    public static FragmentPlayAutomatic createFrag(String currentAtmosphere, Context context)
    {
        FragmentPlayAutomatic frag = new FragmentPlayAutomatic();
        Bundle myBundle = new Bundle();
        AtmosphereByName atmosphereByName = new AtmosphereByName(context);
        myBundle.putParcelable("currentAtmosphere",atmosphereByName.getAtmosphereFromTitle(currentAtmosphere) );
        Log.e("CreateFrag", atmosphereByName.getAtmosphereFromTitle(currentAtmosphere).getTitle());
        frag.setArguments(myBundle);
        return frag;
    }

    public void setActionAct(PlayAutomatic myPLay)
    {
        this.actionAct = myPLay;
    }


    public interface PlayAutomatic
    {
        void updateSongValue();
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();

        // set "origin" to top left corner, so to speak
        window.setGravity(Gravity.TOP|Gravity.RIGHT);

        // after that, setting values for x and y works "naturally"
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 100;
        params.y = 100;
        window.setAttributes(params);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("FPA", "on create");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_display_media_automatic, container, false);
        title = v.findViewById(R.id.current_atmosphere_played_media_resume_automatic);
        myCard = v.findViewById(R.id.pop_up_play_resume_automatic);
        imageDisplay = v.findViewById(R.id.image_current_atmosphere_resume_automatic);
        play = v.findViewById(R.id.play_manual_resume_automatic);

        Log.e("FPA", "on create view");
        Bundle myBundle = getArguments();
        if (myBundle != null) {

            currentAtmosphere = myBundle.getParcelable("currentAtmosphere");
            Log.e("Bundle good", currentAtmosphere.getTitle());
            isPlaying = true;
        } else
        {
            Log.e("FragmentPlay", "bundle null");
        }

        initialiseListener();
        updateValues();


            return v;
    }


    public void updateAtmosphere(Atmosphere newAtmosphere)
    {
        this.currentAtmosphere = newAtmosphere;
        updateValues();
    }

    private void initialiseListener()
    {
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionAct.updateSongValue();
                updateValues();

            }
        });
    }




    public void updateValues()
    {

        title.setText(currentAtmosphere.getTitle());
        Glide.with(imageDisplay.getContext()).load(currentAtmosphere.getImageRessource())
                .asBitmap().fitCenter().into(imageDisplay);
        if (MusicManager.isPlaying())
        {
            play.setImageResource(R.drawable.pause_resume);
        }else
        {
            play.setImageResource(R.drawable.play_button_atmosphere);

        }

    }


}
