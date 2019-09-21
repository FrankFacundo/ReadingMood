package com.example.clement.readingmood.ViewHolder.HolderAtmosphere;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Pages.ClickAtmospherePage;
import com.example.clement.readingmood.R;

public class ViewHolderSongList extends AbstractViewHolderAtmosphere {

    private LinearLayout layout_recycle;
    private TextView currentAtmosphereTitle;
    private ImageView currentImage;
    private Atmosphere myAtmosphere;

    public ViewHolderSongList(@NonNull View itemView) {
        super(itemView);
    }


    @Override
    public void initializeDatas(View itemView) {

        layout_recycle= itemView.findViewById(R.id.linear_atmosphere_recycle_list);
        currentAtmosphereTitle = itemView.findViewById(R.id.atmosphere_title_list);
        currentImage = itemView.findViewById(R.id.atmosphere_image_list);

    }

    @Override
    public void initialiseListener() {

        layout_recycle.setOnClickListener(new View.OnClickListener()
                                          {

                                              @Override
                                              public void onClick(View v) {
                                                  addIntent(v);
                                              }
                                          }
        );


    }

    @Override
    public void addIntent(View itemView) {
        final Context myContext = itemView.getContext();
        Intent myIntent = new Intent(myContext, ClickAtmospherePage.class);
        Bundle myBundle = new Bundle();


        myBundle.putStringArrayList("description",myAtmosphere.getDescription());
        myBundle.putParcelable("atmosphere", myAtmosphere);
        myIntent.putExtras(myBundle);
        myContext.startActivity(myIntent);
    }

    public void display(Atmosphere atmosphere) {

        myAtmosphere = atmosphere;
        currentAtmosphereTitle.setText(atmosphere.getTitle());
        Glide.with(currentImage.getContext()).load(myAtmosphere.getImageRessource()).into(currentImage);

    }


}
