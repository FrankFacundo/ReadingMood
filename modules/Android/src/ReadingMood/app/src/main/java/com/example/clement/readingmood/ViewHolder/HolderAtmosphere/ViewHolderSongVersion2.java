package com.example.clement.readingmood.ViewHolder.HolderAtmosphere;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Pages.ClickAtmospherePage;
import com.example.clement.readingmood.R;

public class ViewHolderSongVersion2 extends AbstractViewHolderAtmosphere {
    private ConstraintLayout layout_recycle_verion_2;
    private TextView currentAtmosphereTitle_version_2;
    private ImageView currentImage_version_2;
    private ImageButton currentPopUp ;
    private Context myContext;
    private Atmosphere myAtmosphere;

    public ViewHolderSongVersion2(@NonNull View itemView, Context mContext) {
        super(itemView);
        myContext = mContext;
    }


    @Override
    public void initializeDatas(View itemView) {

        layout_recycle_verion_2= itemView.findViewById(R.id.layout_atmosphere_version_2);
        currentAtmosphereTitle_version_2 = itemView.findViewById(R.id.title_atmosphere_version2);
        currentImage_version_2 = itemView.findViewById(R.id.image_atmosphere_version_2);
        currentPopUp =  itemView.findViewById(R.id.popup_menu_version_2);
            }

    @Override
    public void initialiseListener() {

        currentImage_version_2.setOnClickListener(new View.OnClickListener()
                                          {

                                              @Override
                                              public void onClick(View v) {
                                                  addIntent(v);
                                              }
                                          }
        );

        currentPopUp.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(final View v) {
                PopupMenu myPopUp = new PopupMenu(myContext, v);
                myPopUp.getMenuInflater().inflate(R.menu.menu_atmosphere_version_2, myPopUp.getMenu());
                myPopUp.show();
                myPopUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId())
                        {
                            case R.id.description_popmenu_version_2 :
                                addIntent(v);
//                                Toast.makeText(myContext,"Description selected", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.play_popmemnu_version_2 :
                                addIntent(v);
//                                Toast.makeText(myContext,"Play selected", Toast.LENGTH_SHORT).show();
                                return true;
                            default:

                        }
                        return false;
                    }
                });
            }
        });

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
        currentAtmosphereTitle_version_2.setText(myAtmosphere.getTitle());
        Glide.with(currentImage_version_2.getContext()).load(myAtmosphere.getImageRessource()).asBitmap().fitCenter().into(currentImage_version_2);
    }
}
