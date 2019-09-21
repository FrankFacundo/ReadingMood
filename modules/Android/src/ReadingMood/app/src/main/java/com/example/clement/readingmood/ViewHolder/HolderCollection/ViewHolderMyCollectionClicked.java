package com.example.clement.readingmood.ViewHolder.HolderCollection;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.clement.readingmood.Objets.BookMyCollection;
import com.example.clement.readingmood.Pages.Manuel;
import com.example.clement.readingmood.R;

public class ViewHolderMyCollectionClicked extends AbstractViewHolderCollection {


    private TextView currentTitle, currentAuthor;
    private ImageView currentImage;
    private BookMyCollection myBook;


    public ViewHolderMyCollectionClicked(View itemView, Context myContext)
    {
        super(itemView);

    }

    @Override
    protected void initializeDatas(View itemView) {

        currentTitle= itemView.findViewById(R.id.title_dialog_my_collection_fragment);
        currentImage = itemView.findViewById(R.id.book_dialog_image_fragment);
        currentAuthor= itemView.findViewById(R.id.author_dialog_my_collection_fragment);

    }

    @Override
    protected void initialiseListener() {


    }

    @Override
    protected void addIntent(View itemView) {
        final Context context = itemView.getContext();

        Bundle myBundle = new Bundle();
        myBundle.putString("path", myBook.getPathToRead());
        Intent intent = new Intent(context, Manuel.class);
        context.startActivity(intent);
    }

    @Override
    public void display(BookMyCollection book) {
        this.myBook = book;
        currentTitle.setText(book.getTitle());
        currentAuthor.setText(book.getAuthor());
        Glide.with(currentImage.getContext()).load(book.getImageRessource()).into(currentImage);

    }

}


