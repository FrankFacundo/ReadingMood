package com.example.clement.readingmood.ViewHolder.HolderAtmosphere;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.R;

public class ViewHolderManualSmell extends RecyclerView.ViewHolder   {

    private Context mContext;
    private TextView titleAtmosphere;
    private ImageView currentImage;
    private Atmosphere myAtmosphere;

    public ViewHolderManualSmell(@NonNull View itemView, int i) {
        super(itemView);
        mContext = itemView.getContext();
        initializeDatas(itemView);
    }


    public void display(Atmosphere atmopshere, int i) {
        myAtmosphere = atmopshere;
        titleAtmosphere.setText(atmopshere.getTitle());

        Glide.with(currentImage.getContext()).load(myAtmosphere.getImageRessource()).into(currentImage);
    }

    protected void initializeDatas(View itemView) {

        titleAtmosphere= itemView.findViewById(R.id.title_manual_smell);
        currentImage = itemView.findViewById(R.id.image_manual_smell);

    }


}
