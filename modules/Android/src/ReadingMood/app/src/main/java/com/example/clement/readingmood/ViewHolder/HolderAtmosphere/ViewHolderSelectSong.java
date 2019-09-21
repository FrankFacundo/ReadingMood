package com.example.clement.readingmood.ViewHolder.HolderAtmosphere;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSelectSong;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.R;

public class ViewHolderSelectSong extends RecyclerView.ViewHolder   {

    private LinearLayout myLayout;
    private boolean isSelected = false;
    private ImageView currentImage;
    private TextView myTitle;
    private Atmosphere myAtmosphere;
    private AdapterSelectSong.ActionSelect myAction;
    private AdapterSelectSong.ActionSelectToDisplay mySecondAction;

    public ViewHolderSelectSong(@NonNull View itemView) {
        super(itemView);
        initialiseDatas(itemView);
        initialiaseListener();
    }

    private void initialiseDatas(View v)
    {
        currentImage = v.findViewById(R.id.atmosphere_image_selected);
        myTitle = v.findViewById(R.id.atmosphere_title_selected);
        myLayout = v.findViewById(R.id.linear_atmosphere_recycle_selected);

    }

    public void setMySecondAction(AdapterSelectSong.ActionSelectToDisplay myfrag)
    {
        this.mySecondAction = myfrag;
    }


    private void initialiaseListener()
    {

        myLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelected)
                {
                    isSelected = false;

                    myAction.deleteSong(myAtmosphere);
                    mySecondAction.updateAtmosphere();

                } else
                {
                    myAction.addSong(myAtmosphere);
                    mySecondAction.updateAtmosphere();
                    isSelected = true;


                }
            }
        });


    }


    public void display(Atmosphere myAtmosphere)
    {

        this.myAtmosphere = myAtmosphere;
        myTitle.setText(myAtmosphere.getTitle());
        Glide.with(currentImage.getContext()).load(myAtmosphere.getImageRessource()).into(currentImage);
    }

    public void setActionSelected(AdapterSelectSong.ActionSelect myAction)
    {
        this.myAction = myAction;
    }


}
