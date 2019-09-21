package com.example.clement.readingmood.Pages;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageButton;
import android.widget.Toast;

import com.example.clement.readingmood.Objets.DescriptionAtmosphere;
import com.example.clement.readingmood.Pages.ListAtmosphere;
import com.example.clement.readingmood.R;

import android.view.Menu;

import java.io.IOException;

public class MainPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private ImageButton atmosphere_button;
    private String message = "Not implemented yet";
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar myToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        // setTitle("Main Page");
        this.configureToolBar();
        /*
        Configure the toolBar
         */
        this.configureDrawerLayout();
        /*
        Configure the drawerLayout
         */
        this.configurateNavigationView();
        /*
        Configure the navigationView
         */

    }




    private void configureToolBar()
    {
        /*
        Make navigator drawer in the toolbar
         */
        myToolBar = findViewById(R.id.my_toolbar_1);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

    }

    private void configureDrawerLayout() {
        /*
        Add the action of the drawerLayout and the color
         */
        this.drawerLayout = findViewById(R.id.drawer_layout_main_page);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,myToolBar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.setStatusBarBackground(R.color.white);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
    private void configurateNavigationView() {
        /*
        Add the navigationView to the page
         */
    this.navigationView = findViewById(R.id.navigation_menus);
    navigationView.setNavigationItemSelectedListener(this);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
     /*
     Add the information icon on the toolbar
      */
    {
        getMenuInflater().inflate(R.menu.menu_info,menu);
        return true;
    }

    public void make_list_atmosphere(View view)
    /*
    Change the page to the list of atmosphere
     */
    {
        switchToListAtmosphere();

    }

    private void displayNotImplemented()
    {
        Toast.makeText(getApplicationContext(), message,Toast.LENGTH_SHORT).show();
    }

    public void resume_click(View view) {

        switchToResume();
    }

    public void my_library_click(View view) {

        Toast.makeText(getApplicationContext(), "Library",Toast.LENGTH_SHORT).show();
        // displayNotImplemented();
    }

    public void settings_click(View view) {


        //Intent intent = new Intent(this,Settings.class);
        //startActivity(intent);
        Toast.makeText(getApplicationContext(), "Settings is clicked",Toast.LENGTH_SHORT).show();

    }

    public void my_collection_click(View view) {

        switchToListMyCollection();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        if (menuItem.getItemId() == R.id.info_menu)
        {
            Toast.makeText(getApplicationContext(), message,Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }

    private void switchToListAtmosphere()
     /*
     Switch the page to the listAtmosphere
      */
    {
        Intent intent = new Intent(this, ListAtmosphere.class);
        startActivity(intent);
    }
    private void switchToListMyCollection()
     /*
     Switch the page to the MyCollection
      */
    {
        Intent intent = new Intent(this, MyCollection.class);
        startActivity(intent);
    }

    private void switchToResume()
    {
        Intent intent = new Intent(this, Resume.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Resume is clicked",Toast.LENGTH_SHORT).show();
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
                displayNotImplemented();
                break;
            case R.id.navigation_item_resume :
                switchToResume();
                break;
            case R.id.navigation_item_smell_atmosphere :
                switchToListAtmosphere();
                break;
            case R.id.navigation_item_sound_atmosphere :
                switchToListAtmosphere();
                break;
            case R.id.navigation_item_about :
                displayNotImplemented();
                break;
            case R.id.navigation_item_settings :
                displayNotImplemented();
                break;
            default:
                break;
        }

        return false;
    }

    @Override
    public void onBackPressed()
    /*
    Add the action to leave the navigatorView
     */
    {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        }else
        {
            super.onBackPressed();
        }
    }


    public void switchToVersion2(View view) {
        Intent intent = new Intent(this,PageVersion2.class);
        startActivity(intent);

    }
}

