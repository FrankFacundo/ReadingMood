package com.example.clement.readingmood.ViewHolder.HolderAtmosphere;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSelectSmell;
import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSelectSong;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.R;

public class ViewHolderSelectSmell extends RecyclerView.ViewHolder   {

    private LinearLayout myLayout;
    private RadioButton myRadioButton;
    private boolean isSelected = false;
    private ImageView currentImage;
    private TextView myTitle;
    private Atmosphere myAtmosphere;
    private AdapterSelectSmell.ActionSelectSmell myAction;

    public ViewHolderSelectSmell(@NonNull View itemView) {
        super(itemView);
        initialiseDatas(itemView);
        initialiaseListener();
    }

    private void initialiseDatas(View v)
    {
        currentImage = v.findViewById(R.id.atmosphere_image_selected_smell);
        myTitle = v.findViewById(R.id.atmosphere_title_selected_smell);
        myLayout = v.findViewById(R.id.linear_atmosphere_recycle_selected_smell);
        myRadioButton = v.findViewById(R.id.radioButton_select_smell);
    }


    private void initialiaseListener()
    {

        myLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelected)
                {
                    isSelected = false;
                    myRadioButton.setChecked(false);
                    myAction.deleteSongSmell(myAtmosphere);

                } else
                {
                    myAction.addSongSmell(myAtmosphere);
                    isSelected = true;
                    myRadioButton.setChecked(true);

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

    public void setActionSelected(AdapterSelectSmell.ActionSelectSmell myAction)
    {
        this.myAction = myAction;
    }


}
