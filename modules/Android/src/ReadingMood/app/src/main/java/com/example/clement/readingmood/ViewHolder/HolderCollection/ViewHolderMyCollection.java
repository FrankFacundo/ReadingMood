package com.example.clement.readingmood.ViewHolder.HolderCollection;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.clement.readingmood.Adapters.CollectionAdapter.MyAdapterMyCollection;
import com.example.clement.readingmood.Objets.BookMyCollection;
import com.example.clement.readingmood.Pages.CollectionClick;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.Singletons.SingletonForRead;

public class ViewHolderMyCollection extends AbstractViewHolderCollection {

    private LinearLayout layout_dialog;
    private TextView currentTitle;
    private TextView currentAuthor;
    private ImageView currentImage;
    private String summary;
    private MyAdapterMyCollection.CreateFragment fragmentForTablet;
    private BookMyCollection myBook;



    public ViewHolderMyCollection(@NonNull View itemView) {
        super(itemView);

    }

    public void setFragmentForTablet(MyAdapterMyCollection.CreateFragment fragmentForTablet)
    {
        this.fragmentForTablet = fragmentForTablet;
    }

    @Override
    protected void initializeDatas(View itemView) {
        currentTitle= itemView.findViewById(R.id.title_recycle_view_my_collection);
        currentImage = itemView.findViewById(R.id.book_image_recycle_view);
        currentAuthor= itemView.findViewById(R.id.author_recycle_view_my_collection);
        layout_dialog = itemView.findViewById(R.id.layout_recycle_view_my_collection);

    }

    @Override
    protected void initialiseListener() {
        layout_dialog.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 fragmentForTablet.addFragmentForTablet(
                                                         currentTitle.getText().toString(),currentAuthor.getText().toString()
                                                         ,summary, myBook.getImageRessource()
                                                 );

                                                 SingletonForRead.getInstance().setBooks(myBook);

                                             }
                                         }


        );
    }

    @Override
    protected void addIntent(View itemView) {

            final Context context = itemView.getContext();
            Intent intent = new Intent(context, CollectionClick.class);
            intent.putExtra("title", currentTitle.getText().toString());
            intent.putExtra("author",currentAuthor.getText().toString());
            intent.putExtra("summary", summary );
//            Log.e("AddIntent","What is added "+ myBook.getImageRessource());
            intent.putExtra("image", myBook.getImageRessource());
            SingletonForRead.getInstance().setBooks(myBook);
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

