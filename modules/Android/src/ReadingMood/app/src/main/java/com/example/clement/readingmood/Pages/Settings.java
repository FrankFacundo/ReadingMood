package com.example.clement.readingmood.Pages;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clement.readingmood.Fragments.FragmentResume.SettingsFragment;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.Raspberry.RaspberryPi;

public class Settings extends AppCompatActivity {

    private Toolbar myToolBar;
    private Switch mySwitch, switchRP;
    private TextView switchLicence;
    private boolean isCheck = false;
    private Button test_connexion ;
    private final String licence = "Titre:  Tender Remains \n" +
            "Auteur: Myuu \n" +
            "Source: https://myuu.bandcamp.com\n" +
            "Licence: https://creativecommons.org/licenses/by/3.0/deed.fr\n" +
            "Téléchargement (11MB): https://www.auboutdufil.com/index.php?id=492\n";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        configurizeToolBar();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_settings, new SettingsFragment()).commit();
        mySwitch = findViewById(R.id.bluetooth_connection);
        switchRP = findViewById(R.id.connection_rp);
        switchLicence = findViewById(R.id.licence);
        test_connexion = findViewById(R.id.test_connexion_rp);

        initialiseListener();
        initialiseBluetooth();



    }

    private void initialiseBluetooth()
    {


    }

    private void initialiseListener()
    {

        isCheck = mySwitch.isChecked();
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (!isChecked)
                {
                    BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
                    bAdapter.disable();
                }
                else
                {

                    Intent intentOpenBluetoothSettings = new Intent();
                    intentOpenBluetoothSettings.setAction(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
                    startActivity(intentOpenBluetoothSettings);

                }


            }
        });

        test_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try
                {
                    RaspberryPi rasp = new RaspberryPi();
                    rasp.connect();
                    if (rasp.isConnected())
                    {
                        Toast.makeText(v.getContext(),"Is connected", Toast.LENGTH_SHORT).show();
                        switchRP.setChecked(true);
                    }
                    else
                    {
                        Toast.makeText(v.getContext(),"No connexion", Toast.LENGTH_SHORT).show();
                        switchRP.setChecked(false);
                    }
                } catch (NullPointerException e)
                {
                    Toast.makeText(v.getContext(),"No connexion", Toast.LENGTH_SHORT).show();
                    switchRP.setChecked(false);
                }




            }
        });

        switchLicence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog myDialog = new Dialog(v.getContext());
                myDialog.setContentView(R.layout.dialog_licence);
                TextView myText = myDialog.findViewById(R.id.send_dialog);
                myText.setText(licence);
                myDialog.show();

            }
        });



    }


    private void configurizeToolBar()
    /*
    Initialisation of the toolbar and add the listener
     */ {
        myToolBar = findViewById(R.id.settings_toolbar1);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        myToolBar.setNavigationIcon(R.drawable.back_button);
        myToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //Intent intent = new Intent(getApplicationContext(), MainPage.class);
                //startActivity(intent);

            }
        });

    }

}
