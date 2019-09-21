package com.example.clement.readingmood.ViewHolder.HolderAtmosphere;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.Raspberry.RaspberryPi;

import java.util.Calendar;
import java.util.Date;

public class ViewHolderSmellVersion2 extends AbstractViewHolderAtmosphere {

    private ConstraintLayout layout_recycle_smell_verion_2;
    private TextView currentAtmosphereSmellTitle_version_2;
    private ImageView currentImageSmell_version_2, currentPopUp;
    private Atmosphere myAtmosphere;
    private Context myContext;
    private RaspberryPi rasp ;

    public ViewHolderSmellVersion2(@NonNull View itemView, Context mContext) {
        super(itemView);
        myContext = mContext;
    }


    @Override
    public void initializeDatas(View itemView) {

        layout_recycle_smell_verion_2= itemView.findViewById(R.id.layout_atmosphere_smell_version_2);
        currentAtmosphereSmellTitle_version_2 = itemView.findViewById(R.id.title_atmosphere_smell_version2);
        currentImageSmell_version_2 = itemView.findViewById(R.id.image_atmosphere_smell_version_2);
        currentPopUp = itemView.findViewById(R.id.popup_smell_menu_version_2);
    }

    @Override
    public void initialiseListener() {

        layout_recycle_smell_verion_2.setOnClickListener(new View.OnClickListener()
                                                  {

                                                      @Override
                                                      public void onClick(View v) {
                                                          addIntent(v);
                                                          Toast.makeText(myContext,"Smell Atmosphere : "+
                                                                  currentAtmosphereSmellTitle_version_2.getText().toString() +"selected", Toast.LENGTH_SHORT).show();

                                                          try
                                                          {
                                                              rasp = new RaspberryPi();
                                                              rasp.connect();
                                                              if (!rasp.isConnected())
                                                              {
                                                                  Toast.makeText(myContext,"Not connected to Raspberry Pi",Toast.LENGTH_SHORT).show();
                                                              }else
                                                              {
                                                                  displayOnRP();
                                                              }

                                                          } catch (
                                                                  NullPointerException e
                                                                  ) {
                                                              Toast.makeText(myContext,"Not connected to Raspberry Pi",Toast.LENGTH_SHORT).show();
                                                          }



//                                                          TESTS

//                                                          Date currentTime = Calendar.getInstance().getTime();
//                                                          long before = currentTime.getTime();
//                                                          for (int i = 0; i<100; i++)
//                                                          {
//                                                              displayOnRP();
//                                                          }
//                                                          long after = Calendar.getInstance().getTime().getTime();
//                                                          long result = after - before;
//                                                          Log.e("Time to DISPLAY", " " + after + " and " + before + " total : " + result);




                                                      }
                                                  }
        );

        currentPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu myPopUp = new PopupMenu(myContext, v);
                myPopUp.getMenuInflater().inflate(R.menu.menu_smell, myPopUp.getMenu());
                myPopUp.show();
                myPopUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId())
                        {
                            case R.id.play_popmenu_smell_version_2:

                                try
                                {
                                    rasp = new RaspberryPi();
                                    rasp.connect();
                                    if (!rasp.isConnected())
                                    {
                                        Toast.makeText(myContext,"Not connected to Raspberry Pi",Toast.LENGTH_SHORT).show();
                                    }else
                                    {
                                        displayOnRP();
                                    }

                                } catch (
                                        NullPointerException e
                                        ) {
                                    Toast.makeText(myContext,"Not connected to Raspberry Pi",Toast.LENGTH_SHORT).show();
                                }





                                return true;

                        }

                        return false;
                    }
                });
            }
        });


    }

    private void displayOnRP()
    {
        Log.e("Test atmosphere",myAtmosphere.getTitle());
        String response = new String("");
        switch (myAtmosphere.getTitle())
        {
            case "AgriculturalField" :
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
            case "FloralGarden" :
                response = rasp.setFloralGarden();
                break;
            case "Ocean" :
                response = rasp.setOcean();
                break;

        }
        Toast.makeText(myContext,"Display "+ response,Toast.LENGTH_SHORT).show();

    }


    @Override
    public void addIntent(View itemView) {
        final Context myContext = itemView.getContext();

    }

    public void display(Atmosphere atmosphere) {

        myAtmosphere = atmosphere;
        currentAtmosphereSmellTitle_version_2.setText(atmosphere.getTitle());
        Glide.with(currentImageSmell_version_2.getContext()).load(atmosphere.getImageRessource()).asBitmap().fitCenter().into(currentImageSmell_version_2);
    }



}
