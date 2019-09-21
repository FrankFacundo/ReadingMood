package com.example.clement.readingmood.ViewHolder.HolderAtmosphere;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.clement.readingmood.Fragments.FragmentOther.ConverterMediaPlayer;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.R;

public class ViewHolderManual extends RecyclerView.ViewHolder   {

    private Context mContext;
    private TextView titleAtmosphere, timeAtmosphere;
    private ImageView currentImage;
    private Atmosphere myAtmosphere;


    public ViewHolderManual(@NonNull View itemView, int i) {
        super(itemView);
        mContext = itemView.getContext();
        initializeDatas(itemView);
      }


    public void display(Atmosphere atmopshere, int i) {
        myAtmosphere = atmopshere;
        titleAtmosphere.setText(atmopshere.getTitle());

        if (atmopshere.getPathToSong() != 0)
        {
            MediaPlayer myMedia = MediaPlayer.create(mContext,      atmopshere.getPathToSong());
            myMedia.start();
            long currentDuration = myMedia.getDuration();
            ConverterMediaPlayer utility = new ConverterMediaPlayer();
            timeAtmosphere.setText(utility.milliSecondsToTimer(currentDuration));
            myMedia.release();
            myMedia = null;
        }else
        {
            timeAtmosphere.setText("0:00");
        }





        Glide.with(currentImage.getContext()).load(myAtmosphere.getImageRessource()).into(currentImage);
        }

    protected void initializeDatas(View itemView) {

        titleAtmosphere= itemView.findViewById(R.id.title_manual);
        timeAtmosphere = itemView.findViewById(R.id.time_atmosphere_manual);
        currentImage = itemView.findViewById(R.id.image_manual);

    }


}
