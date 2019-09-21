package com.example.clement.readingmood.ViewHolder.HolderLibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clement.readingmood.Adapters.LibraryAdapter.AdapterLibraryName;
import com.example.clement.readingmood.Adapters.LibraryAdapter.AdapterListAuthor;
import com.example.clement.readingmood.Pages.AboutAuthor;
import com.example.clement.readingmood.Pages.Manuel;
import com.example.clement.readingmood.Pages.Settings;
import com.example.clement.readingmood.R;

public class ViewHolderListAuhtor extends RecyclerView.ViewHolder  {


    private TextView author, date, see_more;
    private View myItem;
    private ImageButton myPopUp;
    private String myAuthor,myDate;
    private AdapterListAuthor.ChangePage myChange;

    public ViewHolderListAuhtor(@NonNull View itemView) {
        super(itemView);
        initializeDatas(itemView);
        initialiseListener();
    }

    public void makeChange(AdapterListAuthor.ChangePage change)
    {
        this.myChange = change;
    }

    public void initializeDatas(View itemView) {

        myItem= itemView;
        author = itemView.findViewById(R.id.list_author_name);
        date = itemView.findViewById(R.id.list_author_date);
        see_more = itemView.findViewById(R.id.download_library_letter_author);
        myPopUp = itemView.findViewById(R.id.more_book_library_letter_author);
    }

    public void initialiseListener() {

        see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent myIntent = new Intent();
//                Toast.makeText(myItem.getContext(), author + " is selected", Toast.LENGTH_SHORT).show();
                startAct(v);

            }
        });

        myPopUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                PopupMenu myPopUp = new PopupMenu(v.getContext(), v);
                myPopUp.getMenuInflater().inflate(R.menu.menu_library_list_author, myPopUp.getMenu());
                myPopUp.show();
                myPopUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.about_author_letter_list:
                                startAct(v);
                                Toast.makeText(v.getContext(), "More books is selected", Toast.LENGTH_SHORT).show();
                                return true;
                        }

                        return false;
                    }
                });
            }
        });



    }

    private void startAct(View v)
    {

        final Context context = v.getContext();

        Intent intent = new Intent(context, AboutAuthor.class);
        Bundle myBundle = new Bundle();
        myBundle.putString("MyAuthor",myAuthor);
        myBundle.putString("MyDate",myDate);
        intent.putExtras(myBundle);
        context.startActivity(intent);

    }



    public void display(String author, String date) {
        this.myAuthor = author;
        this.myDate = date;
        this.author.setText(author);
        this.date.setText(date);


    }





}
