package com.example.clement.readingmood.Pages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.clement.readingmood.Adapters.CollectionAdapter.MyAdapterMyCollectionVersion2;
import com.example.clement.readingmood.FocusGroup;
import com.example.clement.readingmood.Fragments.FragmentNavigator.FragmentAtmospheresBottom;
import com.example.clement.readingmood.Fragments.FragmentNavigator.FragmentDiscoverPage;
import com.example.clement.readingmood.Fragments.FragmentNavigator.FragmentFavoritesBottom;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Objets.BookMyCollection;
import com.example.clement.readingmood.Objets.DescriptionAtmosphere;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.SQLite.DataBaseMyCollection;
import com.example.clement.readingmood.Singletons.Singleton;
import com.example.clement.readingmood.Singletons.SingletonDataBaseMyColletion;
import com.example.clement.readingmood.Singletons.SingletonForRead;

import java.util.ArrayList;

public class PageVersion2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MyAdapterMyCollectionVersion2.AddForFavorite, FragmentDiscoverPage.baseIntents{

    private AppBarLayout myAppbar;
    private BottomNavigationView bottom_navigator;
    private FragmentDiscoverPage pageDiscover;
    private final FragmentManager fm = getSupportFragmentManager();
    private Toolbar myToolBar;
    private NavigationView myNavigationView;
    private FragmentAtmospheresBottom atmopshereBottom;
    private FragmentFavoritesBottom favoritesBottom;
    private Fragment currentFrag;
    private FragmentTransaction transactionFrag;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.readingmood";
    private ArrayList<BookMyCollection > backLibrary ;
    private DataBaseMyCollection db;

    private final String[] listTitle ={ "Admnistration", "Anger", "Attic", "Bathroom",
            "Bedroom", "Calm", "Car", "Castle", "Cave", "Cemetery",
            "Church", "Circus","Company", "Country Town","Desert", "Fear",
            "Field", "Fight","Fog", "Forest", "Garden", "Gunshot","Gym","Happy","Hospital", "Industry", "Jail",
            "Kitchens", "Library", "LivingRoom", "Love","Meadow", "Mine", "Mountain","Ocean",
            "Plane","Rain", "Sad", "School", "Snow","Street","Sun", "Thunder", "TrainStation", "Wind"};

    private final String[] listTitleSmell ={ "Agricultural field","Christmas", "Cook","Forest","Floral garden"
            ,"Ocean"};


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag("My tag");
        if (fragment != null)
        {

            getSupportFragmentManager().putFragment(outState, "key", fragment);
        }
        getSupportFragmentManager().putFragment(outState,"discover",pageDiscover);
        getSupportFragmentManager().putFragment(outState,"favorites",favoritesBottom);
        getSupportFragmentManager().putFragment(outState,"atmosphere",atmopshereBottom);


    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_version2);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        if (SingletonDataBaseMyColletion.getInstance().getDataBase() == null)
        {
            Log.e("onCreate pv2","is not null");
            db = new DataBaseMyCollection(this);
            SingletonDataBaseMyColletion.getInstance().setDataBaseMyCollection(db);

        } else
        {
            Log.e("onCreate pv2","is null");
            db = SingletonDataBaseMyColletion.getInstance().getDataBase();
        }

        if (savedInstanceState ==null)
        {
            pageDiscover = new FragmentDiscoverPage();
            atmopshereBottom = new FragmentAtmospheresBottom();
            favoritesBottom = new FragmentFavoritesBottom();
            initialiseFragments();
        }else
        {
            Fragment fragment =  getSupportFragmentManager().getFragment(savedInstanceState,"key");
            pageDiscover = (FragmentDiscoverPage) getSupportFragmentManager().getFragment(savedInstanceState,"discover");
//            atmopshereBottom = (FragmentAtmospheresBottom) getSupportFragmentManager().getFragment(savedInstanceState,"atmosphere");
            favoritesBottom = (FragmentFavoritesBottom) getSupportFragmentManager().getFragment(savedInstanceState,"favorites");
            pageDiscover.addFragmentManager(fm);
//            fm.beginTransaction().replace(R.id.layout_main_page_version_2, atmopshereBottom,"2" ).hide(atmopshereBottom).commit();
//            fm.beginTransaction().replace(R.id.layout_main_page_version_2, favoritesBottom,"3" ).hide(favoritesBottom).commit();
//            fm.beginTransaction().replace(R.id.layout_main_page_version_2,pageDiscover, "1" ).commit();
            currentFrag = pageDiscover;
            if (fragment !=null)
            {
                loadFragment(fragment);

            }else
            {
                loadFragment(pageDiscover);
            }
        }
        bottom_navigator = findViewById(R.id.bottom_navigator);
        configureNavigationBottom();
        myAppbar = findViewById(R.id.appbar_to_collapse_version_2);
        initialiseNavigatorView();
        pageDiscover.addBaseIntent( (FragmentDiscoverPage.baseIntents) this);

    }


    private void initialiseNavigatorView()
    {
        myToolBar = findViewById(R.id.toolbar_header_version_2);
        setSupportActionBar(myToolBar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout_main_page_v_2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, myToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        myNavigationView= findViewById(R.id.navigation_menus_v_2);
        myNavigationView.setNavigationItemSelectedListener(this);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle("");
        actionbar.setHomeAsUpIndicator(R.drawable.navigator_view_button);

    }

    private void initialiseFragments()
    {

        pageDiscover.addFragmentManager(fm);

        fm.beginTransaction().add(R.id.layout_main_page_version_2, atmopshereBottom,"2" ).hide(atmopshereBottom).commit();
        fm.beginTransaction().add(R.id.layout_main_page_version_2, favoritesBottom,"3" ).hide(favoritesBottom).commit();
        fm.beginTransaction().add(R.id.layout_main_page_version_2,pageDiscover, "1" ).commit();
        currentFrag = pageDiscover;


    }


    private void loadFragment(Fragment myFragment)
    {
        transactionFrag = fm.beginTransaction();
        transactionFrag.hide(currentFrag);
        transactionFrag.show(myFragment);
        transactionFrag.addToBackStack(null);
        transactionFrag.commit();
        currentFrag = myFragment;

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        Log.e("onAttach","on attach");
    }

    private void configureNavigationBottom()
    {
        bottom_navigator.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        switch(menuItem.getItemId())
                        {
                            case R.id.navigation_bottom_discover :
                                //initialiseFragments();


                                loadFragment(pageDiscover);
//                                Toast.makeText(getApplicationContext(), "Discover is clicked",Toast.LENGTH_SHORT).show();
                                return true;
//                            case R.id.navigation_bottom_my_atmopsheres :
//
//                                loadFragment(atmopshereBottom);
//
//                                Toast.makeText(getApplicationContext(), "My Atmospheres is clicked",Toast.LENGTH_SHORT).show();
//                                return true;
                            case R.id.navigation_bottom_my_favorite :
                                loadFragment(favoritesBottom);
//                                Toast.makeText(getApplicationContext(), "Favorites is clicked",Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.navigation_bottom_resume :

                                Intent intent = new Intent(getApplicationContext(), Resume.class);
                                Bundle myBundle = new Bundle();
                                myBundle.putBoolean("isManual", false);
                                intent.putExtras(myBundle);
                                startActivity(intent);
                                return true;
                        }

                        return false;
                    }
                }

        );
    }


    private void displayNotImplemented()
    {
        Toast.makeText(getApplicationContext(), "Not implemented yet !",Toast.LENGTH_SHORT).show();
    }

    public void resume_click(View view) {

        switchToResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== 1)
        {
            Bundle myBundle = data.getExtras();
            if (myBundle.getBoolean("addedDownload"))
            {

                if (pageDiscover !=null)
                {
                    Log.e("onCreate pv2","result update");
                    pageDiscover.update();
                }else
                {
                    Log.e("onCreate pv2","result initialise");
                    initialiseFragments();
                }

            }


        }else
        {
            Log.e("onCreate pv2","result not 1");
        }

    }

    @Override
    protected void onStop() {
        ArrayList<BookMyCollection> listToSave = Singleton.getInstance().getBooks();
        for (BookMyCollection x : listToSave)
        {
            db.updateBook(x);
        }

        super.onStop();
    }

    public void my_library_click() {

        Intent myIntent = new Intent(this, Library.class);
        startActivityForResult(myIntent, 1);

    }

    public void settings_click() {


        Intent intent = new Intent(this,Settings.class);
        startActivity(intent);

    }

    public void my_collection_click(View view) {

        switchToListMyCollection();
    }


    private void switchToListAtmosphere(boolean isSmell)
     /*
     Switch the page to the listAtmosphere
      */
    {
        Intent intent = new Intent(this, ListAtmosphere.class);
        intent.putExtra("isSmell", isSmell);
        startActivity(intent);
    }
    private void switchToListMyCollection()
     /*
     Switch the page to the MyCollection
      */
    {
        Intent intent = new Intent(this, MyCollection.class);
        intent.putExtra("added",true);
        startActivity(intent);
    }

    private void switchToResume()
    {
        Intent intent = new Intent(getApplicationContext(), Resume.class);
        Bundle myBundle = new Bundle();
        myBundle.putBoolean("isManual", false);
        intent.putExtras(myBundle);
        startActivity(intent);

    }

    private void switchToAbout()
    {
        Intent intent = new Intent(getApplicationContext(), About.class);
        startActivity(intent);
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    /*
    Just make a message when this is not implemented and do the action when this is available
     */
    {
        int id = menuItem.getItemId();
        switch(id)
        {
            case R.id.navigation_item_my_collection :
                switchToListMyCollection();
                break;
            case R.id.navigation_item_my_library :
                my_library_click();
                break;
            case R.id.navigation_item_resume :
                switchToResume();
                break;
            case R.id.navigation_item_smell_atmosphere :
                switchToListAtmosphere(true);
                break;
            case R.id.navigation_item_sound_atmosphere :
                switchToListAtmosphere(false);
                break;
            case R.id.navigation_item_about :
                switchToAbout();
                break;
            case R.id.navigation_item_settings :
                settings_click();
                break;
            default:
                break;
        }

        return false;
    }


    @Override
    public void addOnFav(BookMyCollection myBook) {
        favoritesBottom.addItem(myBook);

    }

    @Override
    public void deleteOnFav(BookMyCollection myBook) {

        favoritesBottom.deleteItem(myBook);


    }

    @Override
    public void deleteCollection(BookMyCollection myBook) {
        if (pageDiscover !=null)
        {
            pageDiscover.deleteBook(myBook);
        }
    }

    public void focusGroupChristmas(View v)
    {
        Intent myIntent = new Intent(this, FocusGroup.class);
        myIntent.putExtra("focusGroup", "c");
        startActivity(myIntent);
    }

    public void focusGroupQuidditch(View v)
    {
        Intent myIntent = new Intent(this, FocusGroup.class);
        myIntent.putExtra("focusGroup", "q");
        startActivity(myIntent);
    }

    @Override
    public void add(BookMyCollection book) {

    }

    @Override
    public void delete(String title) {




    }

    @Override
    public void displayAll()
    {
        Cursor data = db.getAllData();
        if (data.getCount() ==0)
        {
            showMessage("Error","No data found");
        }
        else
        {
            StringBuffer buffer = new StringBuffer();
            while (data.moveToNext())
            {
                buffer.append("ID : "+data.getString(0) +"\n");
                buffer.append("NAME : "+data.getString(1) +"\n");
                buffer.append("TITLE : "+data.getString(2) +"\n");
                buffer.append("LINK : "+data.getString(3) +"\n");
            }
            showMessage("Data", buffer.toString());
        }

    }

    @Override
    public void deleteAll() {
        db.deleteAll();
    }


    private void showMessage(String title, String message)
    {
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}