package com.example.clement.readingmood.Fragments.FragmentResume;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Objets.AtmosphereByName;
import com.example.clement.readingmood.R;

public class MusicManager {

    static MediaPlayer mp;
    static Atmosphere myAtmosphere;

    public static void start(Context context, String title) {
        startMusic(context,  title);
    }

    public static void startMusic(Context context, String title) {

        if (mp != null) {
            if (!mp.isPlaying()) {
                mp.start();
            }
        } else {
            myAtmosphere = new AtmosphereByName(context).getAtmosphereFromTitle(title);
            int pathToSong = myAtmosphere.getPathToSong();
            mp = MediaPlayer.create(context, pathToSong);
        }

        if (! (mp == null)) {
            try {
                mp.setLooping(true);
                mp.start();
            } catch (Exception e) {

            }
        }
    }

    public static String getAtmosphere()
    {
        return myAtmosphere.getTitle();
    }

    public static boolean isPlaying()
    {
        if (mp!=null)
        {
            if (mp.isPlaying())
            {
                return true;
            }else return false;

        }else return false;
    }

    public static void pause() {
        if (mp != null) {
            if (mp.isPlaying()) {
                mp.pause();
            }
        }

    }

    public static void release() {
        try {
            if (mp != null) {
                if (mp.isPlaying()) {
                    mp.stop();
                }
                mp.release();
                mp = null;
            }
        } catch (Exception e) {
        }

    }


}
