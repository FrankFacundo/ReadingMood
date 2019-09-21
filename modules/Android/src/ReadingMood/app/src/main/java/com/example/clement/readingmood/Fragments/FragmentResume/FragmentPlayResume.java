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
import com.example.clement.readingmood.Pages.Manuel;
import com.example.clement.readingmood.R;

import java.io.IOException;
import java.util.ArrayList;

public class FragmentPlayResume extends DialogFragment implements MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener{

    private TextView title, currentTime, totalTime;
    private ImageButton previous,next,play,shuffle, repeat;
    private SeekBar mySeekBar;
    private MediaPlayer myMedia;
    private ConverterMediaPlayer utility;
    private Handler myHandler = new Handler();
    private ImageView imageDisplay;
    private int currentAtmosphere = 0;
    protected long totalDuration, currentDuration;
    private ArrayList<Atmosphere> atmosphereToDisplay;
    private int currentSeek = 0;
    private boolean isShuffle = false;
    private boolean isRepeat = true;



    public void clearSongs()
    {
        if (myMedia!=null)
        {
            myMedia.release();
            myMedia= null;
        }

    }


    public static FragmentPlayResume createFrag(ArrayList<Atmosphere> atmosphereToDisplay, Context context)
    {
        Bundle myBundle = new Bundle();
        myBundle.putParcelableArrayList("resultFromManual", atmosphereToDisplay);
        FragmentPlayResume frag = new FragmentPlayResume();
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
        params.x = 100;
        params.y = 100;
        window.setAttributes(params);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (atmosphereToDisplay == null)
        {
            onStop();
        }

    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }

    @Override
    public void onCancel(DialogInterface dialog) {

        super.onCancel(dialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_display_media_resume, container, false);

            title = v.findViewById(R.id.current_atmosphere_played_media_resume);
            currentTime = v.findViewById(R.id.time_to_display_resume);
            totalTime = v.findViewById(R.id.time_final_play_media_resume);
            previous = v.findViewById(R.id.play_previous_resume);
            next = v.findViewById(R.id.skip_next_resume);
            play = v.findViewById(R.id.play_manual_resume);
            mySeekBar = v.findViewById(R.id.seekbar_media_player_resume);
            shuffle = v.findViewById(R.id.shuffle_resume);
            repeat = v.findViewById(R.id.repeat_resume);
            imageDisplay = v.findViewById(R.id.image_current_atmosphere_resume);

            Bundle myBundle = getArguments();
            if (myBundle != null)
            {

                atmosphereToDisplay = getArguments().getParcelableArrayList("resultFromManual");
                play.setImageResource(R.drawable.pause_resume);
                if (atmosphereToDisplay == null)
                {
                    onStop();
                }
                try
                {
                    if (atmosphereToDisplay.get(currentAtmosphere).getPathToSong() !=0 )
                    {
                        if (myMedia == null)
                        {
                            initialiseMedia();
                        }

                        initialiseSpinner();


                    }

                } catch (NullPointerException e)
                {
                    Log.e("FragmentPlayResume", "null atmopshereToDisplay");
                }

            }
            initialiseListener();


        return v;
    }


    private void initialiseSpinner()
    {
        if (currentSeek != 0)
        {
            mySeekBar.setOnSeekBarChangeListener(this);
            updateProgressBar();
            utility = new ConverterMediaPlayer();
        }
        myMedia.setOnCompletionListener(this);
        mySeekBar.setProgress(0);
        mySeekBar.setMax(100);
        updateProgressBar();
        title.setText(atmosphereToDisplay.get(currentAtmosphere).getTitle());  utility = new ConverterMediaPlayer();
        mySeekBar.setOnSeekBarChangeListener(this);
    }

    private void initialiseListener()
    {
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (myMedia == null)
                {
                    dismiss();
                }else
                {
                    try
                    {
                        if (myMedia.isPlaying())
                        {
                            myMedia.pause();
                            play.setImageResource(R.drawable.play_button_atmosphere);
                        }else
                        {
                            myMedia.start();
                            play.setImageResource(R.drawable.pause_resume);
                        }

                    } catch (IllegalStateException e)
                    {

                    }

                }

            }


        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myMedia !=null)
                {
                    myMedia.stop();
                }


                    if (isShuffle)
                    {
                        currentAtmosphere = (int)(Math.random() * ((atmosphereToDisplay.size() ) ));
                        myMedia.release();
                        initialiseMedia();
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
                                myMedia.release();
                                myMedia.stop();
                                myMedia= null;
                                Toast.makeText(getContext(),"No more songs",Toast.LENGTH_SHORT).show();
                                atmosphereToDisplay = new ArrayList<Atmosphere>();
                            }

                        }

                        if (myMedia !=null)
                        {
                            myMedia.release();
                            Log.e("Error media","current atmosphere : " + currentAtmosphere + " atmo " + atmosphereToDisplay);
                            myMedia = MediaPlayer.create(getContext(), atmosphereToDisplay.get(currentAtmosphere).getPathToSong());
                            myMedia.start();
                            mySeekBar.setProgress(0);
                            mySeekBar.setMax(100);
                            updateProgressBar();
                            title.setText(atmosphereToDisplay.get(currentAtmosphere).getTitle());
                            utility = new ConverterMediaPlayer();
//                            initialiseMedia();
                        }

                    }





            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                                if (isShuffle)
                                                {
                                                    currentAtmosphere = (int)(Math.random() * ((atmosphereToDisplay.size() ) ));
                                                    myMedia.release();
                                                    initialiseMedia();


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
                                                    if (myMedia !=null)
                                                    {
                                                        myMedia.release();
                                                        myMedia = MediaPlayer.create(getContext(), atmosphereToDisplay.get(currentAtmosphere).getPathToSong());
                                                        myMedia.start();
                                                        mySeekBar.setProgress(0);
                                                        mySeekBar.setMax(100);
                                                        updateProgressBar();
                                                        title.setText(atmosphereToDisplay.get(currentAtmosphere).getTitle());  utility = new ConverterMediaPlayer();
//                                                        initialiseMedia();
                                                    }

                                                }


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



    private void initialiseMedia()
    {

        if (currentSeek != 0)
        {
            mySeekBar.setOnSeekBarChangeListener(this);
            updateProgressBar();
            utility = new ConverterMediaPlayer();
        } else
        {
            utility = new ConverterMediaPlayer();
//            MusicManager.start(getContext(), atmosphereToDisplay.get(currentAtmosphere).getPathToSong());
            if (myMedia ==null)
            {
                myMedia = new MediaPlayer();
            }

                myMedia.release();
                Log.e("Error media","current atmosphere : " + currentAtmosphere + " atmo " + atmosphereToDisplay.get(currentAtmosphere));
                myMedia = MediaPlayer.create(getContext(), atmosphereToDisplay.get(currentAtmosphere).getPathToSong());
                myMedia.start();
                myMedia.setOnCompletionListener(this);
                mySeekBar.setProgress(0);
                mySeekBar.setMax(100);
                updateProgressBar();
                title.setText(atmosphereToDisplay.get(currentAtmosphere).getTitle());  utility = new ConverterMediaPlayer();
                mySeekBar.setOnSeekBarChangeListener(this);


        }

    }





    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        myHandler.removeCallbacks(mUpdatedTime);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        myHandler.removeCallbacks(mUpdatedTime);
        if (myMedia!=null)
        {
            int totalDuration = myMedia.getDuration();
            int currentPosition = utility.progressToTimer(seekBar.getProgress(), totalDuration);

            // forward or backward to certain seconds
            myMedia.seekTo(currentPosition);

            // update timer progress again
            updateProgressBar();
        }

    }


    private void updateProgressBar() {
        myHandler.postDelayed(mUpdatedTime, 100);


    }

    private Runnable mUpdatedTime = new Runnable() {
        public void run() {

            if (myMedia != null && atmosphereToDisplay!=null) {

                try
                {
                    totalDuration = myMedia.getDuration();
                    currentDuration = myMedia.getCurrentPosition();

                    // Displaying time completed playing
                    currentTime.setText("" + utility.milliSecondsToTimer(currentDuration));

                    // Updating progress bar
                    int progress = (int) (utility.getProgressPercentage(currentDuration, totalDuration));
                    mySeekBar.setProgress(progress);

                    // Running this thread after 100 milliseconds
                    myHandler.postDelayed(this, 100);
                    if (totalDuration !=0)
                    {
                        totalTime.setText(utility.milliSecondsToTimer(totalDuration));

                    }

                } catch (IllegalStateException e)
                {

                }
                try {
                    Glide.with(imageDisplay.getContext()).load(atmosphereToDisplay.get(currentAtmosphere).getImageRessource())
                            .asBitmap().fitCenter().into(imageDisplay);

                } catch (IllegalArgumentException e)
                {

                } catch (IndexOutOfBoundsException e)
                {

                }
            }
        }
    };






}