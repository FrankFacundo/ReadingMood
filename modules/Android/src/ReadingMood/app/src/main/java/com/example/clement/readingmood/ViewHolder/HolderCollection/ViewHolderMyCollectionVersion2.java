package com.example.clement.readingmood.ViewHolder.HolderCollection;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSong;
import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSongList;
import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSongVersion2;
import com.example.clement.readingmood.Adapters.CollectionAdapter.MyAdapterMyCollectionVersion2;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Objets.AtmosphereByName;
import com.example.clement.readingmood.Objets.BookMyCollection;
import com.example.clement.readingmood.Pages.CollectionClick;
import com.example.clement.readingmood.Pages.Resume;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.Singletons.Singleton;
import com.example.clement.readingmood.Singletons.SingletonForRead;

import java.util.ArrayList;

public class ViewHolderMyCollectionVersion2 extends AbstractViewHolderCollection {
    private TextView currentTitle;
    private TextView currentAuthor;
    private ImageView currentImage;
    private String summary;
    private FrameLayout myFrameLayout;
    private TextView read_now;
    private ImageButton favorite, popUpBook;
    private Context myContext;
    private BookMyCollection myBook;
    private MyAdapterMyCollectionVersion2.AddForFavorite myFav;
    private boolean isFav = false;
    private ImageView myImage;
    private ArrayList<String> textTxt;



    public ViewHolderMyCollectionVersion2(@NonNull View itemView, Context myContext, ArrayList<String> textTxt) {
        super(itemView);
        this.myContext = myContext;
        this.textTxt = textTxt;
    }


    @Override
    protected void initializeDatas(View itemView) {
        myFrameLayout = itemView.findViewById(R.id.frame_layout_background_book);
        read_now = itemView.findViewById(R.id.read_now_version_2);
        favorite = itemView.findViewById(R.id.favorite_book_version_2);
        popUpBook = itemView.findViewById(R.id.more_book_version_2);
        currentTitle = itemView.findViewById(R.id.title_book_recycle_view_version_2);
        currentAuthor = itemView.findViewById(R.id.author_book_recycle_view_version_2);
        myImage = itemView.findViewById(R.id.image_book_version_2);
    }



    @Override
    protected void initialiseListener() {
        myFrameLayout.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {

                                                  addIntent(v);

                                             }
                                         });

        popUpBook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                PopupMenu myPopUp = new PopupMenu(myContext, v);
                myPopUp.getMenuInflater().inflate(R.menu.menu_my_collection_version_2, myPopUp.getMenu());
                myPopUp.show();
                myPopUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.summary_popmenu_version_2:
                                addIntent(v);
//                                Toast.makeText(myContext, "Summary selected", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.play_popmemnu_collection_version_2:
//                                Toast.makeText(myContext, "Play selected", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(v.getContext(), Resume.class);
                                SingletonForRead.getInstance().setBooks(myBook);
                                Bundle myBundle = new Bundle();
                                myBundle.putBoolean("isManual", false);
                                intent.putExtras(myBundle);
                                v.getContext().startActivity(intent);
                                return true;
                            case R.id.list_atmosphere_menu:
//                                Toast.makeText(myContext, "List Atmosphere selected", Toast.LENGTH_SHORT).show();
                                createDialogAtmosphere(v.getContext());

                                return true;
                            case R.id.delete_from_collection :

                                if (myFav!=null)
                                {
                                    myFav.deleteCollection(myBook);
                                }

                                return true;

                        }

                        return false;
                    }
                });
            }
        });
        read_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Resume.class);
                SingletonForRead.getInstance().setBooks(myBook);
                Bundle myBundle = new Bundle();
                myBundle.putBoolean("isManual", false);
                intent.putExtras(myBundle);
                v.getContext().startActivity(intent);

            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFav)
                {
                    myFav.deleteOnFav(myBook);
                    favorite.setImageResource(R.drawable.ic_favorite_black_24dp);
                    isFav = false;
                }else
                {
                    myFav.addOnFav(myBook);
                    favorite.setImageResource(R.drawable.favorite_red);
                    isFav = true;
                }


            }
        });

    }

    public void setAddForFav(MyAdapterMyCollectionVersion2.AddForFavorite myFav)
    {
        this.myFav = myFav;
    }


    private void createDialogAtmosphere(Context v)
    {
        final Dialog myDialog = new Dialog((v));
        AtmosphereByName toConvert = new AtmosphereByName(v);
        myDialog.setContentView(R.layout.dialog_list_atmo);

        ArrayList<String> listAtmo = myBook.getListAtmosphere();
        ArrayList<Atmosphere> realListAtmo = new ArrayList<>();
        for (String x : listAtmo)
        {
            realListAtmo.add(toConvert.getAtmosphereFromTitle(x));
            Log.e("Atmosphere", toConvert.getAtmosphereFromTitle(x).getTitle());
        }

        RecyclerView myRecyler = myDialog.findViewById(R.id.recycler_view_dialog);
        AdapterSongList madapter = new AdapterSongList(realListAtmo,v);
        /*
        Display the recycleView with a linearLayoutManager
         */
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(v, LinearLayoutManager.VERTICAL, false);
        myRecyler.setLayoutManager(layoutManager);
        myRecyler.setAdapter(madapter);

        myDialog.show();



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
        myBundle.putString("path", myBook.getPathToRead());
        SingletonForRead.getInstance().setBooks(myBook);
        intent.putExtras(myBundle);
        context.startActivity(intent);

    }



    @Override
    public void display(BookMyCollection book) {

        Log.e("ViewHolder display", String.valueOf(" " + book.getListAtmosphere()==null)+ " is listeAtmo");
        this.myBook = book;
        currentTitle.setText(book.getTitle());
        currentAuthor.setText(book.getAuthor());
        this.summary = book.getSummary();
        Glide.with(myImage.getContext()).load(book.getImageRessource()).into(myImage);



    }






}




