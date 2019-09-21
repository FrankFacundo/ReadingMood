package com.example.clement.readingmood.Fragments.FragmentResume;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.Raspberry.RaspberryPi;

import java.util.ArrayList;

public class FragmentPlaySmell extends DialogFragment {

    private ArrayList<Atmosphere> atmosphereToDisplay;
    private ImageButton play,next,previous,shuffle,repeat;
    private TextView myTitle;
    private ImageView currentAtmosphereImage;
    private CardView myCard;
    private boolean isRepeat = true;
    private boolean isShuffle = false;

    @Override
    public void onStop() {
        this.dismiss();
        super.onStop();
    }

    private RaspberryPi rasp;
    private int currentAtmosphere = 0;
    private boolean ableToDisplay = true;




    public static FragmentPlaySmell createFrag(ArrayList<Atmosphere> atmosphereToDisplay)
    {
        Bundle myBundle = new Bundle();
        myBundle.putParcelableArrayList("resultFromManualSmell", atmosphereToDisplay);
        FragmentPlaySmell frag = new FragmentPlaySmell();
        frag.setArguments(myBundle);
        return frag;
    }


    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();

        // set "origin" to top left corner, so to speak
        window.setGravity(Gravity.TOP|Gravity.RIGHT);

        // after that, setting values for x and y works "naturally"
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 300;
        params.y = 100;
        window.setAttributes(params);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_resume_smell, container, false);

        if (ableToDisplay)
        {
            myTitle = v.findViewById(R.id.current_atmosphere_played_media_resume_smell);
            previous = v.findViewById(R.id.play_previous_resume_smell);
            next = v.findViewById(R.id.skip_next_resume_smell);
            play = v.findViewById(R.id.play_manual_resume_smell);
            shuffle = v.findViewById(R.id.shuffle_resume_smell);
            repeat = v.findViewById(R.id.repeat_resume_smell);
            myCard = v.findViewById(R.id.pop_up_play_resume_smell);
            currentAtmosphereImage = v.findViewById(R.id.image_current_atmosphere_resume_smell);
            Bundle myBundle = getArguments();
            if (myBundle != null) {
                ArrayList<? extends Atmosphere> listToAdd = getArguments().getParcelableArrayList("resultFromManualSmell");
                atmosphereToDisplay = new ArrayList<Atmosphere>();
                for (int i = 0; i < listToAdd.size(); i++) {
                    atmosphereToDisplay.add(listToAdd.get(i));

                }
                if (atmosphereToDisplay == null) {
                    onStop();
                }else
                {
                    updateDatas();
                }

                initialiseListener();

            }

        }else
        {
            Toast.makeText(getContext(),"No more smells available",Toast.LENGTH_SHORT).show();
            onStop();
        }


        return v;
    }


    private void initialiseListener()
    {
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("Atmosphere",atmosphereToDisplay.get(currentAtmosphere).getTitle());
                try{
                    if (rasp==null)
                    {
                        rasp = new RaspberryPi();
                        rasp.connect();
                    }else
                    {
                        if (rasp.isConnected())
                        {
                            String response = new String();

                            switch (atmosphereToDisplay.get(currentAtmosphere).getTitle())
                            {

                                case "Agricultural field" :
                                    response =  rasp.setAgriculturalField();
                                    break;
                                case "Christmas" :
                                    response = rasp.setChristmas();
                                    break;
                                case "Cook" :
                                    response = rasp.setCook();
                                    break;
                                case "Forest" :
                                    response = rasp.setForest();
                                    break;
                                case "Floral garden" :
                                    response = rasp.setFloralGarden();
                                    break;
                                case "Ocean" :
                                    response = rasp.setOcean();
                                    break;
                            }
                            Toast.makeText(getContext(),response + "is diffused", Toast.LENGTH_SHORT).show();

                        }else
                        {
                            Toast.makeText(getContext(),"Raspberry Pi not connected",Toast.LENGTH_SHORT).show();
                        }
                }

                } catch (NullPointerException e)
                {
                    Toast.makeText(getContext(),"Raspberry Pi not connected",Toast.LENGTH_SHORT).show();
                }



            }


        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isShuffle)
                {
                    currentAtmosphere = (int)(Math.random() * ((atmosphereToDisplay.size() ) ));
                    if (isRepeat)
                    {

                    }else
                    {
                        atmosphereToDisplay.remove(atmosphereToDisplay.get(currentAtmosphere));
                    }

                } else
                {
                    currentAtmosphere ++;

                    if (isRepeat)
                    {

                        if (currentAtmosphere >= atmosphereToDisplay.size())
                        {
                            currentAtmosphere = 0;
                        }


                    } else
                    {
                        if (currentAtmosphere >= atmosphereToDisplay.size())
                        {
                            Toast.makeText(getContext(),"No more smells",Toast.LENGTH_SHORT).show();
                            atmosphereToDisplay = new ArrayList<Atmosphere>();
                            ableToDisplay = false;

                        }

                    }

                }

                updateDatas();



            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (isShuffle)
                                            {
                                                currentAtmosphere = (int)(Math.random() * ((atmosphereToDisplay.size() ) ));

                                            } else
                                            {
                                                currentAtmosphere --;

                                                if (isRepeat)
                                                {

                                                    if (currentAtmosphere < 0)
                                                    {
                                                        currentAtmosphere = atmosphereToDisplay.size()-1;
                                                    }


                                                } else
                                                {
                                                    if (currentAtmosphere < 0 )
                                                    {
                                                        currentAtmosphere = 0;
                                                    }

                                                }


                                            }






        updateDatas();
                                        }
                                    }
        );

        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isShuffle)
                {
                    Toast.makeText(getContext(), "Shuffle mode disactived", Toast.LENGTH_SHORT).show();
                    shuffle.setImageResource(R.drawable.shuffle);
                    isShuffle = false;
                }
                else
                {
                    Toast.makeText(getContext(), "Shuffle mode", Toast.LENGTH_SHORT).show();
                    shuffle.setImageResource(R.drawable.shuffle_selected);
                    isShuffle = true;
                }

            }
        });

        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isRepeat)
                {
                    Toast.makeText(getContext(), "Repeat disactived", Toast.LENGTH_SHORT).show();
                    repeat.setImageResource(R.drawable.repeat);
                    isRepeat = false;
                }
                else
                {
                    Toast.makeText(getContext(), "Repeat actved", Toast.LENGTH_SHORT).show();
                    repeat.setImageResource(R.drawable.repeat_selected);
                    isRepeat = true;
                }



            }
        });







    }

    private void updateDatas()
    {

        try
        {
            myTitle.setText(atmosphereToDisplay.get(currentAtmosphere).getTitle());
            Glide.with(currentAtmosphereImage.getContext()).load(atmosphereToDisplay.get(currentAtmosphere).getImageRessource())
                    .asBitmap().fitCenter().into(currentAtmosphereImage);

        }catch (IndexOutOfBoundsException e)
        {
            onStop();

        }


    }



}
