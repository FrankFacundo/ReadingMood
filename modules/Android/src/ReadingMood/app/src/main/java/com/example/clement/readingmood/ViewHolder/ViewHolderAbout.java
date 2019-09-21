package com.example.clement.readingmood.ViewHolder;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.clement.readingmood.Objets.Pact35;
import com.example.clement.readingmood.R;

public class ViewHolderAbout extends RecyclerView.ViewHolder  {

    private Pact35 myAuhtor;
    private ImageView photo;
    private TextView name, module, description;




    public ViewHolderAbout(@NonNull View itemView) {
        super(itemView);
        initializeDatas(itemView);

    }



    public void initializeDatas(View itemView) {

        photo = itemView.findViewById(R.id.photo_about);
        name = itemView.findViewById(R.id.name_about);
        module = itemView.findViewById(R.id.module_about);
        description = itemView.findViewById(R.id.description_about);

    }


    public void display(Pact35 myAuhtor) {
        this.myAuhtor = myAuhtor;
        name.setText(myAuhtor.getName());
        module.setText(myAuhtor.getModule());
        description.setText(myAuhtor.getDescription());
        Glide.with(photo.getContext()).load(myAuhtor.getPhoto()).into(photo);

    }


}
