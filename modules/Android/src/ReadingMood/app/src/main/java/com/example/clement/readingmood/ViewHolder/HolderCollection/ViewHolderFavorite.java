package com.example.clement.readingmood.ViewHolder.HolderCollection;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.PopupMenu;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.clement.readingmood.Adapters.CollectionAdapter.MyAdapterMyCollection;
import com.example.clement.readingmood.Objets.BookMyCollection;
import com.example.clement.readingmood.Pages.CollectionClick;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.Singletons.SingletonForRead;

public class ViewHolderFavorite extends AbstractViewHolderCollection {



    private ConstraintLayout layout_dialog;
    private TextView currentTitle;
    private TextView currentAuthor;
    private ImageView currentImage, currentPopUp;
    private String summary = null;
    private Context myContext;
    private BookMyCollection myBook;

    public ViewHolderFavorite(@NonNull View itemView, Context myContext) {
        super(itemView);
        this.myContext = myContext;

    }

    @Override
    protected void initializeDatas(View itemView) {
        currentTitle= itemView.findViewById(R.id.title_favorite_recycle);
        currentImage = itemView.findViewById(R.id.image_book_favorite);
        currentAuthor= itemView.findViewById(R.id.author_my_favorite);
        layout_dialog = itemView.findViewById(R.id.favorite_linear_layout);
        currentPopUp = itemView.findViewById(R.id.popup_menu_favorite);

    }


    @Override
    protected void initialiseListener() {
        layout_dialog.setOnClickListener(new View.OnClickListener() {
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
                myPopUp.getMenuInflater().inflate(R.menu.menu_favorite_books, myPopUp.getMenu());
                myPopUp.show();
                myPopUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId())
                        {
                            case R.id.more_information_favorite:
                                addIntent(v);
//                                Toast.makeText(myContext,"You want information", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.delete_favorite:
//                                Toast.makeText(myContext,"You want to delete", Toast.LENGTH_SHORT).show();
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
    protected void addIntent(View itemView) {

        final Context context = itemView.getContext();
        Intent intent = new Intent(context, CollectionClick.class);
        Bundle myBundle = new Bundle();
        myBundle.putString("title", currentTitle.getText().toString());
        myBundle.putString("author",currentAuthor.getText().toString());
        myBundle.putString("summary", summary );
        myBundle.putString("image", String.valueOf(myBook.getImageRessource()));
        SingletonForRead.getInstance().setBooks(myBook);
        intent.putExtras(myBundle);
        context.startActivity(intent);

    }

    @Override
    public void display(BookMyCollection book) {

        this.myBook = book;
        currentTitle.setText(book.getTitle());
        currentAuthor.setText(book.getAuthor());
        this.summary = book.getSummary();
        if (currentImage!=null)
            /*
            It can be null for the tablet mode
             */
        {

            Glide.with(currentImage.getContext()).load(book.getImageRessource()).into(currentImage);
        }

    }


}
