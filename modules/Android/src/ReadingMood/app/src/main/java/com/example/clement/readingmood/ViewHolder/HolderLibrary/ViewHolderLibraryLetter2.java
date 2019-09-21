package com.example.clement.readingmood.ViewHolder.HolderLibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clement.readingmood.Adapters.LibraryAdapter.AdapterLibraryName;
import com.example.clement.readingmood.Adapters.LibraryAdapter.MyAdapterMyLibraryLetter;
import com.example.clement.readingmood.Adapters.LibraryAdapter.MyAdapterMyLibraryLetter2;
import com.example.clement.readingmood.Objets.BookMyLibrary;
import com.example.clement.readingmood.Pages.AboutAuthor;
import com.example.clement.readingmood.R;

public class ViewHolderLibraryLetter2 extends RecyclerView.ViewHolder   {


    private TextView myTitle, myAuthor, myDate, myDownload;
    private View myItem;
    private ImageButton myPopUp;
    private MyAdapterMyLibraryLetter2.CreateDownload fragmentForDownload;
    private BookMyLibrary myBook;


    public ViewHolderLibraryLetter2(@NonNull View itemView) {
        super(itemView);
        initializeDatas(itemView);
        initialiseListener();
    }




    public void initializeDatas(View itemView) {

        myItem= itemView;
        myTitle = itemView.findViewById(R.id.title_book_library_letter);
        myAuthor = itemView.findViewById(R.id.author_book_library_letter);
        myDate = itemView.findViewById(R.id.data_author_library_letter);
        myDownload = itemView.findViewById(R.id.download_library_letter);
        myPopUp = itemView.findViewById(R.id.more_book_library_letter);

    }

    public void initialiseListener() {


        myPopUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                PopupMenu myPopUp = new PopupMenu(v.getContext(), v);
                myPopUp.getMenuInflater().inflate(R.menu.menu_book_library_letter, myPopUp.getMenu());
                myPopUp.show();
                myPopUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.about_author_letter:


                                Toast.makeText(v.getContext(), "Already in this page", Toast.LENGTH_SHORT).show();



                                return true;
                            case R.id.download_menu_library_letter:

                                if (fragmentForDownload !=null)
                                {

                                    fragmentForDownload.downloadForAutomatic(myBook);
                                    Toast.makeText(v.getContext(),"Download completed. Added to My Collection",
                                            Toast.LENGTH_SHORT).show();
                                } else
                                {
                                    Toast.makeText(v.getContext(),"Download not possible",
                                            Toast.LENGTH_SHORT).show();
                                }



                                return true;
                        }

                        return false;
                    }
                });
            }
        });

        myDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentForDownload !=null)
                {

                    fragmentForDownload.downloadForAutomatic(myBook);
                    Toast.makeText(v.getContext(),"Download completed. Added to My Collection",
                            Toast.LENGTH_SHORT).show();
                } else
                {
                    Toast.makeText(v.getContext(),"Download not possible",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });





    }



    public void display(BookMyLibrary myBook) {

        this.myBook = myBook;
        myTitle.setText(myBook.getTitle());
        myAuthor.setText(myBook.getAuthor());
        myDate.setText(myBook.getDate());

    }



    public void setFragmentForDownload(MyAdapterMyLibraryLetter2.CreateDownload fragmentForDownload)
    {

        this.fragmentForDownload = fragmentForDownload;
    }






}
