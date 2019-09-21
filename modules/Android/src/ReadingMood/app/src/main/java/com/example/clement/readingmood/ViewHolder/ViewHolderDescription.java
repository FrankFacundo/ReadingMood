package com.example.clement.readingmood.ViewHolder;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clement.readingmood.R;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ViewHolderDescription extends RecyclerView.ViewHolder   {

    private String myDescription;
    private ImageView more_datas;
    private TextView myCurrentDescription;
    private TextView myExample;
    private int number;
    private View myItem;
    private int etatButton = 0;
    private LinearLayout layoutToShow;


    public ViewHolderDescription(@NonNull View itemView) {
        super(itemView);
        initializeDatas(itemView);
        initialiseListener();
    }




    public void initializeDatas(View itemView) {

        myItem= itemView;
        more_datas = itemView.findViewById(R.id.more_description_atmosphere);
        myCurrentDescription = itemView.findViewById(R.id.text_current_description);
        myExample = itemView.findViewById(R.id.text_example_description);
        layoutToShow = itemView.findViewById(R.id.layout_description_click);


    }

    public void initialiseListener() {

        layoutToShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etatButton == 0)
                {
                    myCurrentDescription.setText(myDescription);
                    more_datas.setImageResource(R.drawable.false_spinner_2);
                    etatButton =1;
                }
                else
                {
                    myCurrentDescription.setText("");
                    more_datas.setImageResource(R.drawable.false_spinner);
                    etatButton =0;
                }


            }
        });




    }



    public void display(String myDescription, int i) {
        this.myDescription = myDescription;
        this.number = i;
        myExample.setText(myExample.getText().toString() + Integer.toString(number));


    }




}
