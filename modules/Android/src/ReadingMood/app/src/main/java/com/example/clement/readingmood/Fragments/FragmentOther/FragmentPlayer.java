package com.example.clement.readingmood.Fragments.FragmentOther;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.R;

public class FragmentPlayer extends Fragment implements MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener {

    private TextView currentAtmospherePLayed, currentTime, totalTime;
    private FloatingActionButton currentPlayButton;
    private SeekBar seekbarPlayed ;
    private MediaPlayer myMedia;
    private ConverterMediaPlayer utility;
    private Handler myHandler = new Handler();
    private int pathtoSong;
    private Atmosphere myAtmosphere;
    protected long totalDuration;
    private TransmitActivity myTransmit;

    public static FragmentPlayer create(int pathToSong, Atmosphere myAtmosphere)
    {
        FragmentPlayer myFrag = new FragmentPlayer();
        Bundle myBundle = new Bundle();
        myBundle.putInt("pathToSong", pathToSong);
        myBundle.putParcelable("atmosphere",myAtmosphere);
        myFrag.setArguments(myBundle);
        Log.e("Bundle ","well transmitted");
        return myFrag;


    }


    public interface TransmitActivity
    {
        void makePlay(MediaPlayer media);
        void makePause(MediaPlayer media);
    }


    public void makeTransmit(TransmitActivity myTransmit)
    {
        this.myTransmit = myTransmit;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_media_player, container, false);

        currentAtmospherePLayed = v.findViewById(R.id.current_atmosphere_played_media);
        currentPlayButton = v.findViewById(R.id.play_button_media_player);
        seekbarPlayed = v.findViewById(R.id.seekbar_media_player);
        currentTime = v.findViewById(R.id.time_to_display);
        totalTime = v.findViewById(R.id.time_final_play_media);
        utility = new ConverterMediaPlayer();
        myMedia = new MediaPlayer();
        seekbarPlayed.setOnSeekBarChangeListener(this);
        myMedia.setOnCompletionListener(this);
        playSong(getArguments());
        initialiseListener();


        return v;

    }

    private void actionPlay()
    {
        if (myTransmit!=null)
        {
            myTransmit.makePause(myMedia);
        }
        myMedia.pause();
        currentPlayButton.setImageResource(R.drawable.play_button_atmosphere);
    }

    private void actionPause()
    {
        if (myTransmit !=null)
        {
            myTransmit.makePlay(myMedia);
        }
        myMedia.start();
        currentPlayButton.setImageResource(R.drawable.pause_atmosphere);

    }


    public void actionWhenClick()
    {
        if (myMedia.isPlaying())
        {

            actionPlay();

        }
        else
        {

            actionPause();

        }


    }


    private void initialiseListener()
    {
        currentPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                actionWhenClick();


            }
        });
    }


    public void playSong(Bundle myBundle)
    {

        try {

            if (myBundle !=null)
            {

            pathtoSong = myBundle.getInt("pathToSong");
            myAtmosphere = myBundle.getParcelable("atmosphere");
            Log.e("start media","play is clicked");
            myMedia.reset();

//            String filename = "android.resource://com.example.clement.readingmood/raw/car";
//            myMedia.setDataSource(getContext(),Uri.parse(filename));

            myMedia = MediaPlayer.create(getContext(),      pathtoSong);
            myMedia.start();
            String songTitle = myAtmosphere.getTitle();
            currentAtmospherePLayed.setText(songTitle);

            // Changing Button Image to pause image

//            btnPlay.setImageResource(R.drawable.btn_pause);
             currentPlayButton.setImageResource(R.drawable.pause_atmosphere);



            // set Progress bar values
            seekbarPlayed.setProgress(0);
            seekbarPlayed.setMax(100);

            // Updating progress bar
            updateProgressBar();

            }
            else
            {
                Log.e("My media","is not playing");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }


    }

    private void updateProgressBar() {
        myHandler.postDelayed(mUpdatedTime, 100);


    }

    private Runnable mUpdatedTime = new Runnable() {
        public void run() {

            if (myMedia != null) {

                totalDuration = myMedia.getDuration();
                long currentDuration = myMedia.getCurrentPosition();

                // Displaying time completed playing
                currentTime.setText("" + utility.milliSecondsToTimer(currentDuration));

                // Updating progress bar
                int progress = (int) (utility.getProgressPercentage(currentDuration, totalDuration));
                seekbarPlayed.setProgress(progress);

                // Running this thread after 100 milliseconds
                myHandler.postDelayed(this, 100);
                if (totalDuration !=0)
                {
                    totalTime.setText(utility.milliSecondsToTimer(totalDuration));

                }

            }
        }
    };



    @Override
    public void onCompletion(MediaPlayer mp) {

        Toast.makeText(getContext(), "This is finished",Toast.LENGTH_SHORT).show();
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
        int totalDuration = myMedia.getDuration();
        int currentPosition = utility.progressToTimer(seekBar.getProgress(), totalDuration);

        // forward or backward to certain seconds
        myMedia.seekTo(currentPosition);

        // update timer progress again
        updateProgressBar();

    }

    @Override
    public void onStop() {
        super.onStop();
        if(myMedia!=null)
        {
            myMedia.release();
            myMedia=null;
        }

    }





}
