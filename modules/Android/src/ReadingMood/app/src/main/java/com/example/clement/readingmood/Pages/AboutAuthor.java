package com.example.clement.readingmood.Pages;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clement.readingmood.Fragments.FragmentLibrary.BookLibraryFragment;
import com.example.clement.readingmood.Fragments.FragmentLibrary.FragmentBookVersion2;
import com.example.clement.readingmood.Objets.BookMyLibrary;
import com.example.clement.readingmood.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class AboutAuthor extends AppCompatActivity {

    private Toolbar myToolBar;
    private ArrayList<BookMyLibrary> listBook;

    private String myAuthor, myDate;

    private ImageView imageAuthor;
    private TextView textViewAuthor, textViewDate;
    private String listWeb = new String();
    private ArrayList<String> listResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_authour);
        configurizeToolBar();
        imageAuthor = findViewById(R.id.image_about_the_author);
        textViewAuthor = findViewById(R.id.about_author_title);
        textViewDate = findViewById(R.id.about_author_date);


        if (getIntent() != null) {
            myAuthor = getIntent().getExtras().getString("MyAuthor");
            myDate = getIntent().getExtras().getString("MyDate");
            textViewAuthor.setText(myAuthor);
            textViewDate.setText(myDate);

        }
        initializeList();

        FragmentBookVersion2 myFrag = FragmentBookVersion2.create1(listBook, true);

        getSupportFragmentManager().beginTransaction().
                replace(R.id.layout_to_inflate_about_author, myFrag).commit();

    }


    private void configurizeToolBar()
    /*
    Initialisation of the toolbar and add the listener
     */ {
        myToolBar = findViewById(R.id.my_toolbar_about_the_auhtor);
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

    private void initializeList()
    /*
    Initialise the list of recycleView
     */ {
        listBook = new ArrayList<>();
        getServerDatas();
        getStringsFromData();
        createListBooks();

    }


    private void getServerDatas() {

        try {
            AboutAuthor.MyTask myTask = new AboutAuthor.MyTask(listWeb, myAuthor);
            listWeb = myTask.execute(this).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void getStringsFromData() {
        if (listWeb != null) {

            listResult = new ArrayList<>();
            String inter = new String();
            int n = listWeb.length();
            int currentParentL = 0;
            int currentParentR = 0;
            for (int i = 0; i < n; i++) {

                String currentValue = String.valueOf(listWeb.charAt(i));

                if (currentValue.equals("(")) {
                    if (currentParentL == 0) {
                        inter = new String();
                        currentParentL += 1;
                    } else {
                        currentParentL = 0;
                    }
                } else {
                    if (currentValue.equals(")")) {
                        if (currentParentR == 0) {
                            listResult.add(inter);
                            currentParentR++;
                        } else {
                            currentParentR = 0;
                        }
                    } else {
                        inter = inter + currentValue;
                    }

                }

            }


        }


    }


    private ArrayList<String> getDataFromString(String singleData)
    {
        ArrayList<String> result = new ArrayList<String>();
        String inter = new String();
        int n = singleData.length();
        int compteur = 0;
        for (int i =0; i<n;i++)
        {

            String currentValue = String.valueOf(singleData.charAt(i));
            if (currentValue.equals("'") && compteur == 0)
            {
                inter = new String();
                compteur+=1;
            }else if (currentValue.equals("'") && compteur ==1)
            {
                result.add(inter);
                compteur = 0;
            }else if(currentValue.equals(","))
            {

            }else
            {
                inter = inter + currentValue;
            }

        }
        return result;

    }


    private void createListBooks()
    {
        if (listResult !=null)
        {
            for (String x : listResult)
            {

                try
                {
                    ArrayList<String> futurBook = getDataFromString(x);
                    String author = futurBook.get(0);
                    String date = futurBook.get(1);
                    String title = futurBook.get(2);
                    String urlTxt = futurBook.get(3);
                    String urlEpub = futurBook.get(4);
                    BookMyLibrary newBook = new BookMyLibrary(title, author,date, urlTxt);
                    newBook.setUrlEpub(urlEpub);
                    listBook.add(newBook);
                } catch (IndexOutOfBoundsException e)
                {
                    Log.e("BookLibraryFragment", "book does not contain everything ");
                }

            }
        }
    }


    private static class MyTask extends AsyncTask<Object, Void, String> {

        AboutAuthor activity;
        private String result;
        private String urlReceived;

        public MyTask(String result, String urlReceived)
        {
            this.urlReceived = urlReceived;
            this.result = result;
        }

        @Override
        protected String doInBackground(Object... params) {
            activity = (AboutAuthor) params[0];
            try {
                StringBuilder sb = new StringBuilder();

                String baseUrl = "https://readingmood-239210.appspot.com/searchauthor/";
                Log.e("ABoutAuthor", baseUrl + urlReceived);
                URL url = new URL(baseUrl + urlReceived);
                BufferedReader in;
                in = new BufferedReader(
                        new InputStreamReader(
                                url.openStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    sb.append(inputLine);
                in.close();

                return sb.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String str) {
            result = str;
        }

    }




}