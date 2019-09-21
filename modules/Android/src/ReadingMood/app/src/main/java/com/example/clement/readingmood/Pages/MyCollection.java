package com.example.clement.readingmood.Pages;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.clement.readingmood.Adapters.CollectionAdapter.MyAdapterMyCollection;
import com.example.clement.readingmood.Fragments.FragmentCollection.FragmentCollectionClick;
import com.example.clement.readingmood.Fragments.FragmentCollection.MyCollectionFragment;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.Singletons.SingletonDataBaseMyColletion;

public class MyCollection extends AppCompatActivity implements MyAdapterMyCollection.CreateFragment {
    private Toolbar myToolBar;
    /*
    Toolbar to customize the font and the actions
     */
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    /*
    To display message in the logcat
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_collection_page);
        this.configurizeToolBar();
        createFragment();

    }

    @Override
    public void addFragmentForTablet(String title, String authorView, String summaryView, int pathImage)
    {

        if (findViewById(R.id.layout_frame_collection_tablette) !=null)
        {
            FragmentCollectionClick secondFragment = FragmentCollectionClick.create(title, authorView, summaryView,pathImage,null);
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.layout_frame_collection_tablette,secondFragment).commit();

        }
        else
        {
            Intent monIntent = new Intent(this,CollectionClick.class);
            monIntent.putExtra("title",title);
            monIntent.putExtra("author",authorView);
            monIntent.putExtra("summary",summaryView);
            monIntent.putExtra("image",pathImage);
            startActivity(monIntent);
        }
    }

    private void configurizeToolBar()
    /*
    Initialise the toolbar and add the backbutton actioon
     */
    {
        myToolBar = findViewById(R.id.my_toolbar_my_collection);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        myToolBar.setNavigationIcon(R.drawable.back_button);
        myToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }


    private void createFragment()
    {
        Bundle monIntent = getIntent().getExtras();
        FragmentCollectionClick secondFragment = null;
        if (monIntent!=null) {
            /*
            Get the extra from the intent
             */
            String bookTitle = monIntent.getString("title");
            String bookAuthor = monIntent.getString("author");
            String bookSummary = monIntent.getString("summary");
            int pathImage = monIntent.getInt("image");
            secondFragment = FragmentCollectionClick.create(bookTitle, bookAuthor, bookSummary,pathImage, null);
        }

        if (findViewById(R.id.layout_frame_collection_tablette) !=null && secondFragment!=null)
        {
            getSupportFragmentManager().beginTransaction().
                    add(R.id.layout_frame_collection_tablette,secondFragment).commit();
        }

        final MyCollectionFragment fragmentListBook = new MyCollectionFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.layout_frame_collection
                ,fragmentListBook).commit();


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    /*
    Create the menu on the toolbar
     */
    {
        getMenuInflater().inflate(R.menu.menu_my_collection,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    /*
    Add action on the icon of the toolbar
     */
    {
        int id = menuItem.getItemId();
        if(id == R.id.delete_all_collection)
        {
            SingletonDataBaseMyColletion.getInstance().getDataBase().deleteAll();
            return true;

        }

        return super.onOptionsItemSelected(menuItem);
    }






}
