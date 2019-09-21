package com.example.clement.readingmood.Fragments.FragmentResume;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Objets.AtmosphereByName;
import com.example.clement.readingmood.R;

import java.util.ArrayList;


public class FragmentPlaySmellAuto  extends DialogFragment {

    private String atmosphereToDisplay;
    private ImageButton play;
    private TextView myTitle;
    private ImageView currentAtmosphereImage;



    // TODO: Rename and change types of parameters

    private OnFragmentToPlaySmell mListener;

    public FragmentPlaySmellAuto() {
        // Required empty public constructor
    }


    public void setFragmentPlaySmellAuto(OnFragmentToPlaySmell mListener)
    {
        Log.e("SetFraPlaySmeAuto","initialise good");
        this.mListener = mListener;
    }


    public static FragmentPlaySmellAuto createFrag(String  atmosphereToDisplay)
    {
        Bundle myBundle = new Bundle();
        myBundle.putString("resultFromManualSmell", atmosphereToDisplay);
        FragmentPlaySmellAuto frag = new FragmentPlaySmellAuto();
        frag.setArguments(myBundle);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            atmosphereToDisplay = getArguments().getString("resultFromManualSmell");
            Toast.makeText(getContext(),"Try to display", Toast.LENGTH_SHORT);
        }


    }
    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();

        // set "origin" to top left corner, so to speak
        window.setGravity(Gravity.TOP|Gravity.RIGHT);

        // after that, setting values for x and y works "naturally"
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 400;
        params.y = 100;
        window.setAttributes(params);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_fragment_play_smell_auto, container, false);

        myTitle = v.findViewById(R.id.current_atmosphere_played_media_resume_smell_auto);
        play = v.findViewById(R.id.play_manual_resume_smell_auto);
        currentAtmosphereImage = v.findViewById(R.id.image_current_atmosphere_resume_smell_auto);

        initialiseListener();
        initialiseView();

        return v;
    }


    private void initialiseView()
    {
        myTitle.setText(atmosphereToDisplay);
        AtmosphereByName atmo = new AtmosphereByName(getContext());
        Glide.with(currentAtmosphereImage.getContext()).load(atmo.getAtmosphereFromTitle(atmosphereToDisplay).getImageRessource())
                .asBitmap().fitCenter().into(currentAtmosphereImage);

    }

    private void initialiseListener()
    {
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (mListener != null)
               {

                   mListener.displaySmellAuto();
               }else
               {
                   Log.e("TryDisplaySmellAuto","mListener is null");
               }
            }
        });
    }




    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentToPlaySmell {

        void displaySmellAuto();
    }
}
