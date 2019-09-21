package com.example.clement.readingmood.ViewHolder.HolderAtmosphere;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.Raspberry.RaspberryPi;

public class ViewHolderSmell extends AbstractViewHolderAtmosphere {

    private TextView currentAtmosphere;
    private ImageView currentImage;
    private LinearLayout currentLayout;
    private RaspberryPi rasp;
    private Atmosphere myAtmosphere;

    public ViewHolderSmell(@NonNull View itemView) {
        super(itemView);
    }


    @Override
    public void initializeDatas(View itemView) {

        currentAtmosphere = itemView.findViewById(R.id.atmosphere_title_smell);
        currentImage = itemView.findViewById(R.id.atmosphere_smell_image);
        currentLayout = itemView.findViewById(R.id.layout_recycle_view_smell_astmsophere);
    }

    @Override
    public void initialiseListener() {
        currentLayout.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 addIntent(v);
                                                 try
                                                 {
                                                     rasp = new RaspberryPi();
                                                     rasp.connect();
                                                     if (!rasp.isConnected())
                                                     {
                                                         Toast.makeText(v.getContext(),"Not connected to Raspberry Pi",Toast.LENGTH_SHORT).show();
                                                     }else
                                                     {
                                                         displayOnRP(v.getContext());
                                                     }

                                                 } catch (
                                                         NullPointerException e
                                                         ) {
                                                     Toast.makeText(v.getContext(),"Not connected to Raspberry Pi",Toast.LENGTH_SHORT).show();
                                                 }




                                             }
                                         }
        );


    }


    private void displayOnRP(Context context)
    {
        Log.e("Test atmosphere",myAtmosphere.getTitle());
        String response = new String("");
        switch (myAtmosphere.getTitle())
        {
            case "Field" :
                response =  rasp.setAgriculturalField();
                break;
            case "Snow" :
                response = rasp.setChristmas();
                break;
            case "Kitchens" :
                response = rasp.setCook();
                break;
            case "Forest" :
                response = rasp.setForest();
                break;
            case "Garden" :
                response = rasp.setFloralGarden();
                break;
            case "Ocean" :
                response = rasp.setOcean();
                break;

        }
        Toast.makeText(context, "Display : " + response, Toast.LENGTH_SHORT);

    }

    @Override
    public void addIntent(View itemView) {

    }

    public void display(Atmosphere atmosphere) {

        this.myAtmosphere = atmosphere;
        currentAtmosphere.setText(atmosphere.getTitle());
        Glide.with(currentImage.getContext()).load(atmosphere.getImageRessource()).into(currentImage);
    }


}
