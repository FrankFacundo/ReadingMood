package com.example.clement.readingmood.ViewHolder.HolderLibrary;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.clement.readingmood.Adapters.CollectionAdapter.MyAdapterMyCollection;
import com.example.clement.readingmood.Adapters.LibraryAdapter.AdapterLibraryName;
import com.example.clement.readingmood.Adapters.LibraryAdapter.AdapterListAuthor;
import com.example.clement.readingmood.R;

import java.util.ArrayList;

public class ViewHolderLibraryName extends RecyclerView.ViewHolder  {

    private TextView myCurrentLetter;
    private View myItem;
    private int etatButton = 0;
    private LinearLayout layoutToShow;
    private LinearLayout layoutToInflate;
    private ImageView more;
    private String letter;
    private AdapterLibraryName.CreateDownload fragmentForDownload;
    private int layout;
    private CardView myCard;

    public ViewHolderLibraryName(@NonNull View itemView) {
        super(itemView);
        initializeDatas(itemView);
        initialiseListener();
    }




    public void initializeDatas(View itemView) {

        myItem= itemView;
        myCurrentLetter = itemView.findViewById(R.id.text_library_letter);
        layoutToInflate = itemView.findViewById(R.id.layout_library_display_name);
        layoutToShow = itemView.findViewById(R.id.layout_library_name);
        more = itemView.findViewById(R.id.more_library_name);
        layout = R.id.layout_library_display_name;
        myCard = itemView.findViewById(R.id.cardview_library_letter);

    }

    public void initialiseListener() {

        myCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etatButton == 0)
                {

                    more.setImageResource(R.drawable.false_spinner_2);
                    etatButton =1;
//                    fragmentForDownload.inflateLayout(layoutToInflate.getId(), letter);
                    layoutToInflate.setBackgroundColor(Color.RED);
                }
                else
                {

                    more.setImageResource(R.drawable.false_spinner);
                    etatButton =0;
//                    fragmentForDownload.deflateLayout(layoutToInflate.getId(), letter);
                }


            }
        });




    }

    public void display(String letter) {
        this.letter = letter;
        myCurrentLetter.setText(letter);

    }



    public void setFragmentForDownload(AdapterLibraryName.CreateDownload fragmentForDownload)
    {

        this.fragmentForDownload = fragmentForDownload;

    }





}
