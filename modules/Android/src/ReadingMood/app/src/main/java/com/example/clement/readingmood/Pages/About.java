package com.example.clement.readingmood.Pages;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.clement.readingmood.Adapters.AdapterAbout;
import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSong;
import com.example.clement.readingmood.Objets.Pact35;
import com.example.clement.readingmood.R;

import java.util.ArrayList;

public class About extends AppCompatActivity {

    private Toolbar myToolBar;
    private RecyclerView myRecyler ;
    private ArrayList<String> descriptions = new ArrayList<>();
    private ArrayList<Pact35> pact_auhtor = new ArrayList<>();
    private AdapterAbout myAdapter ;
    private LinearLayout isTablet ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        configurizeToolBar();
        myRecyler = findViewById(R.id.recycler_view_about);
        initialyseRecycler();


    }

    private void initialyseRecycler()
    {
        initialyzeAll();


        myAdapter = new AdapterAbout(pact_auhtor,getApplicationContext());

        if (findViewById(R.id.about_tablette) !=null)
        {
            GridLayoutManager managerGrid = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
            myRecyler.setLayoutManager(managerGrid);

        } else
        {
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            myRecyler.setLayoutManager(layoutManager);

        }
        myRecyler.setAdapter(myAdapter);




    }

    private void initialyzeAll()
    {
        String[] names = {"Thierry Bécart","Clément Bernard","Thomas Chêne", "Frank Facundo","Khaled Grira","Thiziri Nait Saada"};
        String[] modules = {"Modules Android et Classification","Modules Android et Test et Intégration",
        "Modules Base de Données et Raspberry Pi","Module Communication Client Serveur et Raspberry Pi", "Modules Base de Données et Communication Client Serveur",
        "Modules Classification et Test et Intégration"};

        descriptions.add("Pour le module Android : il s'est occupé de gérer la lecture des fichiers audios. \n" +
                "Pour le module classification : il s'est chargé de la compression de données PCA, d'outils de machine learning (Naive Bayes, KNN, Decision Tree, SVC) et de sérialisation de données.");
        descriptions.add("Pour le module Android : il s'est occupé de la création du rendu visuel général, de la sauvegarde" +
                "des livres et du téléchargement, du traitement des formats epub et du mode tablette. \n" +
                "Pour le module Test et Intégration, il s'est occupé de l'intégration avec la base de données. Quant aux tests," +
                "il les a réalisé sur le module Android, Base de Données et Raspberry Pi.");
        descriptions.add("Pour le module Base de Données : il s'est occupé de la création de tous les sons et des odeurs. \n" +
                "Pour le module Raspberry Pi : il s'est occupé de l'intégration de celle-ci dans la Magic Box, et de la conception de la boîte en elle-même");
        descriptions.add("Pour le module Base de Données : il s'est occupé de récupérer tous les livres disponibles au téléchargement. \n" +
                "Pour le module Communication Client Serveur : il s'est occupé d'établir le lien entre la base de données et le module Android.");
        descriptions.add("Pour le module Communication Client Serveur : il s'est occupé du lien avec la Raspeberry Pi afin de diffuser les odeurs." +
                "Pour le module Raspberry Pi : il s'est occupé de la configuration de celle-ci et de la création de la Magic Box");
        descriptions.add("Pour le module Classification : elle s'est occupée de la compression des données PCA, d'outils de machine learning (Naive Bayes, KNN, Decicion Tree, Linear SVC) et de la sérialisation de données. \n" +
                "Pour le module Test et Intégration : elle s'est occupée de l'intégration du module classification avec la base de données. Pour " +
                "les tests, elle s'est occupée des tests sur le classifieur.");

        TypedArray my_photo = getApplicationContext().getResources().obtainTypedArray(R.array.photo_pact);
        for (int i = 0; i<names.length; i ++)
        {
            Pact35 author = new Pact35(names[i],modules[i], descriptions.get(i),my_photo.getResourceId(i,0));
            pact_auhtor.add(author);
        }

    }


    private void configurizeToolBar()
    /*
    Initialisation of the toolbar
     */ {
        myToolBar = findViewById(R.id.toolbar_about);
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






}
