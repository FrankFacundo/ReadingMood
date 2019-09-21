package com.example.clement.readingmood.Pages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.clement.readingmood.R;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    /*
     * Used to display message with Log.d()
     */
    private Toolbar toolBar;
    /*
    Toolbar with a customized font
     */
    private Button start_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);
        super.onCreate(savedInstanceState);
        if (isFirstRun) {

            setContentView(R.layout.first_page);
            this.configureToolBar();
            Log.d(LOG_TAG, "-------");
            Log.d(LOG_TAG, "onCreate");
            start_button = findViewById(R.id.button_send);

            Toast.makeText(MainActivity.this, "First Run", Toast.LENGTH_LONG)
                    .show();
        } else
        {

            startActivity(new Intent(MainActivity.this, PageVersion2.class));
        }


        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();


    }

    private void configureToolBar() {
        /*
        Toolbar of the mainPage
         */
        toolBar = findViewById(R.id.my_first_toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onStart(){
        super.onStart();
        try{
            start_button.setEnabled(true);
            Log.d(LOG_TAG, "onStart");

        }catch(RuntimeException e)
        {

        }
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }


    public void pageListAtmosphere(View view) {
        /*
        To switch to the next page
         */
        start_button.setEnabled(false);
        Intent intent = new Intent(getApplicationContext(), PageVersion2.class);
        startActivity(intent);

    }
}
