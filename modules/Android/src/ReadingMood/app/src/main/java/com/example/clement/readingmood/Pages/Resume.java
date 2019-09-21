package com.example.clement.readingmood.Pages;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clement.readingmood.CheckForSDCard;
import com.example.clement.readingmood.Fragments.FragmentOther.SwipePageFragment;
import com.example.clement.readingmood.Fragments.FragmentResume.FragmentPlayAutomatic;
import com.example.clement.readingmood.Fragments.FragmentResume.FragmentPlayResume;
import com.example.clement.readingmood.Fragments.FragmentResume.FragmentPlaySmell;
import com.example.clement.readingmood.Fragments.FragmentResume.FragmentPlaySmellAuto;
import com.example.clement.readingmood.Fragments.FragmentResume.MusicManager;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Objets.AtmosphereByName;
import com.example.clement.readingmood.Objets.BookMyCollection;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.Raspberry.RaspberryPi;
import com.example.clement.readingmood.Singletons.Singleton;
import com.example.clement.readingmood.Singletons.SingletonForRead;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.epub.EpubReader;

public class Resume extends AppCompatActivity implements SwipePageFragment.OnFragmentInteractionListener, FragmentPlayAutomatic.PlayAutomatic, FragmentPlaySmellAuto.OnFragmentToPlaySmell {
    private Toolbar myToolBar;
    private WebView myWebView;
    private int currentPage = 0;
    private String listWeb;
    private boolean isManual = false;
    private Menu myMenu;
    private MenuItem modeDisplayed;
    private FragmentPlayResume myDialogFrag;
    private FragmentPlaySmell myDialogSmell;
    private FragmentPlaySmellAuto myDialoSmellAuto;
    private ArrayList<String> listAllTextToDisplay = new ArrayList<>();
    private String[] style ={"amatic","jahr","kalam","kalamlight","karla",
            "karlaitalic","montserrat","mukta","opensansregular","oxygen","ptserifweb",
            "roboto","sarabun","sofia","sourcesanspro","Test"};

    private String current_style = "Test";

    private float current_size = (float) 1.5;

    private String newCss ;

    private ArrayList<Atmosphere> listSongs = new ArrayList<>();
    private ArrayList<Atmosphere> listSmells = new ArrayList<>();
    private FragmentPlayAutomatic newFragAuto;
    private SwipePageFragment mySwipe;
    private BookMyCollection bookInitial ;
    private LinearLayout myLayout;
    private RaspberryPi rasp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_test_1);
        configurizeToolBar();
        myWebView = findViewById(R.id.web_view_resume_test_1);
        myLayout = findViewById(R.id.layout_resume);
        bookInitial = SingletonForRead.getInstance().getBooks();

        Bundle myBundle = getIntent().getExtras();
        if (myBundle !=null)
        {
            isManual = myBundle.getBoolean("isManual");

            if (isManual)
            {
                Log.e("Manual Mode","good");
                listSongs = myBundle.getParcelableArrayList("resultFromManual");
                listSmells = myBundle.getParcelableArrayList("resultFromManualSmell");
                Log.e("List songs : ", listSongs.toString());
                Log.e("List smell : ", listSmells.toString());
            } else
            {
                 rasp = new RaspberryPi();
            }


        }

        try
        {
            initialiseListener();
            displayPage();
            startDialogPlay();

        } catch (RuntimeException e)
        {
            Log.e("Resume","unable to start");
            this.finish();
        }

    }

    private void displayPage()
    {

            myWebView.loadDataWithBaseURL("file:///android_asset/",bookInitial.getListAllTextToDisplay().get(bookInitial.getCurrentPage()), "text/html", "utf-8",null);

    }

    private void changePageWithPop(int newPage)
    {
        if ( (newPage >= 0) && (newPage< bookInitial.getListAllTextToDisplay().size()))
        {

            if (!isManual)
            {
                int a = bookInitial.getCurrentPage();
                bookInitial.setCurrentPage(newPage);
                Log.e("Swipe Worked", "New value : " +newPage);
                updateCurrentPage();
                displayPage();
                updateAutomatic(a);
            }


        }
    }


    @Override
    public void changePage(int newPage) {
        // Functions used by SwipePageFragment

        if ( (newPage >= 0) && (newPage< bookInitial.getListAllTextToDisplay().size()))
        {

            if (isManual)
            {
                bookInitial.setCurrentPage(newPage);
                updateCurrentPage();
                displayPage();
            }else
            {
                int a = bookInitial.getCurrentPage();
                bookInitial.setCurrentPage(newPage);
                updateCurrentPage();
                displayPage();
                updateAutoWPop(a);
            }


        }


    }



    private void configurizeToolBar()
    /*
    Initialise the toolbar and add the backbutton actioon
     */ {
        myToolBar = findViewById(R.id.toolbar_resume_1);
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


    private void displayContent(String message)
    {
        Log.e("Display message ", message);
//        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void initialiseListener()
    {

        myWebView.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext())
        {
            @Override
            public void onSwipeLeft() {
                goToNext();
            }
            @Override
            public void onSwipeRight() {
                goToPrevious();
            }

        });



    }


    private void updateCurrentPage()
    {
        String new_string = "<div class = \"bernardClass \" > <p> " + " - "+bookInitial.getCurrentPage() + " / " +
                (bookInitial.getListAllTextToDisplay().size()-1)  + " - " + " </p> </div>";
        String value = bookInitial.getListAllTextToDisplay().get(bookInitial.getCurrentPage());
        String new_text;
        if (value.contains("<div class = \"bernardClass \" > <p>"))
        {
            int a = value.indexOf("<div class = \"bernardClass \" > <p> ");
            String new_s = value.substring(a);
            new_text = value.replace( new_s, new_string + "</body>"   );
        }else
        {
            new_text =  value.replace("</body>", new_string+"</body>" );
        }
        currentPage = bookInitial.getCurrentPage();
        listAllTextToDisplay = bookInitial.getListAllTextToDisplay();
        listAllTextToDisplay.set(currentPage, new_text);
        bookInitial.setListAllTextToDisplay(listAllTextToDisplay);

    }


    private void goToNext()
    {

        if (bookInitial.getCurrentPage() < bookInitial.getListAllTextToDisplay().size()-1)
        {
            bookInitial.setCurrentPage(bookInitial.getCurrentPage()+ 1);
            bookInitial.getCurrentPage();
            updateCurrentPage();
            displayPage();
            changePage(bookInitial.getCurrentPage());

            if (!isManual)
            {
                updateAutoWPop(bookInitial.getCurrentPage());
            }


        }else
        {
            Toast.makeText(getApplicationContext(),"Last Page", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToPrevious()
    {
        if (bookInitial.getCurrentPage() > 0 )
        {
            bookInitial.setCurrentPage(bookInitial.getCurrentPage() - 1);
            updateCurrentPage();
            displayPage();
            changePage(bookInitial.getCurrentPage());
        }else
        {
            Toast.makeText(getApplicationContext(),"First Page", Toast.LENGTH_SHORT).show();
        }
    }

    private void modifyStyle(int i )
    {
        try
        {
            this.current_style = style[i];
        }catch (IndexOutOfBoundsException e)
        {        }

        customizeCss();
    }

    private void customizeCssByNumber(float i )
    {
        this.current_size = i;
        customizeCss();
    }

    private void customizeCss()
    {
        newCss ="@font-face { font-family: amatic; src: url('fonts/amatic.ttf'); } "+
                "@font-face { font-family: jahr; src: url('fonts/jahr.ttf'); } "+
                "@font-face { font-family: kalam; src: url('fonts/kalam.ttf'); } "+
                "@font-face { font-family: kalamlight; src: url('fonts/kalamlight.ttf'); } "+
                "@font-face { font-family: karla; src: url('fonts/karla.ttf'); } "+
                "@font-face { font-family: karlaitalic; src: url('fonts/karlaitalic.ttf'); } "+
                "@font-face { font-family: montserrat; src: url('fonts/montserrat.ttf'); } "+
                "@font-face { font-family: mukta; src: url('fonts/mukta.ttf'); } "+
                "@font-face { font-family: opensansregular; src: url('fonts/opensansregular.ttf'); } "+
                "@font-face { font-family: oxygen; src: url('fonts/oxygen.ttf'); } "+
                "@font-face { font-family: ptserifweb; src: url('fonts/ptserifweb.ttf'); } "+
                "@font-face { font-family: roboto; src: url('fonts/roboto.ttf'); } "+
                "@font-face { font-family: sarabun; src: url('fonts/sarabun.ttf'); } "+
                "@font-face { font-family: sofia; src: url('fonts/sofia.ttf'); } "+
                "@font-face { font-family: sourcesanspro; src: url('fonts/sourcesanspro.ttf'); } "+

                "p\n" +
                "{\n" +
//                "line-height :"+ 1.8 + ";\n" +
                " width : 70%;"+
                "margin : auto;"+
// " text-align : center;" +
//                                "\n" +
                "}\n" +
//                "body { font-family : " + current_style +";" +
                "body { font-family :" +current_style+ ";" +
                "font-size:" + current_size + "rem;\n" +
                "}" +
                ".bernardClass p\n" +
                "{\n" +
                "  margin : auto;\n" +
                "  text-align : center;\n" +
                "}  " +         "" +
                "" +
                "" +
                "";

        ArrayList<String> new_list = new ArrayList<>();
        for (String x : bookInitial.getListAllTextToDisplay())
        {
            String new_string = x.replace("</style>", newCss + "</style>");
            new_list.add(new_string);
        }
        bookInitial.setListAllTextToDisplay(new_list);

    }


    @Override
    protected void onDestroy() {
        SingletonForRead.getInstance().setBooks(bookInitial);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    /*
    Create the menu on the toolbar
     */ {
        myMenu = menu;
        getMenuInflater().inflate(R.menu.menu_resume, menu);
        MenuItem chooseFamily = menu.findItem(R.id.resume_size);
        Spinner mySpinner = (Spinner) chooseFamily.getActionView();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.font_array, R.layout.spinner_font);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                modifyStyle(position);
               try
               {
                   updateCurrentPage();
                   displayPage();
               }catch (IndexOutOfBoundsException e)
               {
                   Log.e("Spiner off", bookInitial.getListAllTextToDisplay().size() + "");
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mySpinner.setSelection(style.length-1);
        MenuItem chooseSize = menu.findItem(R.id.resume_font);
        Spinner spinnerSize = (Spinner) chooseSize.getActionView();
        ArrayAdapter<CharSequence> adapterSize = ArrayAdapter.createFromResource(this,
                R.array.size_array, R.layout.spinner_font);
        adapterSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterSize.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerSize.setAdapter(adapterSize);
        spinnerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                float newint = (float) (6.0 + position) / 10;
                customizeCssByNumber(  newint );
                try
                {
                    updateCurrentPage();
                    displayPage();
                }catch (IndexOutOfBoundsException e)
                {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerSize.setSelection(4);

        modeDisplayed = myMenu.findItem(R.id.manual_mode_resume);
        if (isManual)
        {
            modeDisplayed.setIcon(R.drawable.automatic_mode_resume);
        } else
        {
            modeDisplayed.setIcon(R.drawable.manual_resume_mode);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    /*
    Add action on the icon of the toolbar
     */ {
        int id = menuItem.getItemId();
        switch(id )
        {
            case R.id.display_media_resume_manual :

                if (isManual)
                {
                    displayManualPop();

                }else
                {
                    displayAutomaticPop();
                }
                return true;
            case R.id.manual_mode_resume :
                displayContent("Switch mode");
                switchMode();
                return true;
            case R.id.resume_smell :
                displayContent("Smell selected");
                if (isManual)
                {
                    if (myDialogSmell == null)
                    {
                        startDialogSmell();
                    }else
                    {

                        FragmentManager ft1 = getSupportFragmentManager();
                        myDialogSmell.show(ft1, "Current play Smell");

                    }
                } else
                {
                    if (myDialoSmellAuto == null)
                    {
                        startDialogSmellAuto();
                    }else
                    {

                        FragmentManager ft1 = getSupportFragmentManager();
                        myDialoSmellAuto.setFragmentPlaySmellAuto((FragmentPlaySmellAuto.OnFragmentToPlaySmell) this);
                        myDialoSmellAuto.show(ft1, "Current play Smell");

                    }
                }


                return true;
            case R.id.resume_font :

                return true;
            case R.id.resume_size :

                return true;
            case R.id.resume_page :
                startSwipeFrag();
                return true;

        }

        return super.onOptionsItemSelected(menuItem);
    }

    private ArrayList<Atmosphere> convertStringToAtmosphere(ArrayList<String> listToChange)
    {
        ArrayList<Atmosphere> result = new ArrayList<>();
        AtmosphereByName atmo = new AtmosphereByName(getApplicationContext());
        for (String x : listToChange)
        {
            result.add(atmo.getAtmosphereFromTitle(x));
        }
        return result;
    }


    private void startDialogPlay()
    {
        FragmentManager ft = getSupportFragmentManager();
        if (isManual)
        {
            if (myDialogFrag == null)
            {


                myDialogFrag = FragmentPlayResume.createFrag(listSongs, getApplicationContext());
            }

            myDialogFrag.show(ft, "Current play");
            myDialogFrag.setRetainInstance(true);
        }else
        {

            displayAutomaticPop();

        }




    }


    private void displayManualPop()
    {
        FragmentManager ft = getSupportFragmentManager();
        initialiseManualIfNotSelected();
        if (myDialogFrag == null)
        {
            myDialogFrag = FragmentPlayResume.createFrag(listSongs, getApplicationContext());
        }
        myDialogFrag.show(ft, "Current play");
        myDialogFrag.setRetainInstance(true);

    }

    private void startSwipeFrag()
    {
        FragmentManager ft = getSupportFragmentManager();
        mySwipe = SwipePageFragment.newInstance(bookInitial.getCurrentPage(), bookInitial.getListAllTextToDisplay().size());
        mySwipe.show(ft,"Current Swipe");
        mySwipe.setRetainInstance(true);
    }


    private void startDialogSmellAuto()
    {
        FragmentManager ft = getSupportFragmentManager();
//        myDialogSmell = FragmentPlaySmell.createFrag(atmosphereToDisplaySmell);
        myDialoSmellAuto = FragmentPlaySmellAuto.createFrag(bookInitial.getListAtmosphere().get(getIndexCloser(bookInitial.getCurrentPage())));
        myDialoSmellAuto.setFragmentPlaySmellAuto((FragmentPlaySmellAuto.OnFragmentToPlaySmell) this);
        myDialoSmellAuto.show(ft, "Current play Smell");
        myDialoSmellAuto.setRetainInstance(true);
    }


    private void startDialogSmell()
    {
        initialiseManualIfNotSelected();
        FragmentManager ft = getSupportFragmentManager();
//        myDialogSmell = FragmentPlaySmell.createFrag(atmosphereToDisplaySmell);
        myDialogSmell = FragmentPlaySmell.createFrag(listSmells);
        myDialogSmell.show(ft, "Current play Smell");
        myDialogSmell.setRetainInstance(true);

    }

    @Override
    protected void onStop() {
        if (myDialogFrag !=null)
        {
            myDialogFrag.clearSongs();
        }
        MusicManager.release();
        super.onStop();
    }

    private void switchMode()
    {


        {
            if (isManual)
            {
                modeDisplayed.setIcon(R.drawable.manual_resume_mode);
                Toast.makeText(getApplicationContext(), "Automatic Mode switched", Toast.LENGTH_SHORT).show();
                isManual=false;
                if (myDialogFrag != null)
                {
                    myDialogFrag.onDestroy();
                }
            }else
            {
                modeDisplayed.setIcon(R.drawable.automatic_mode_resume);
                Toast.makeText(getApplicationContext(), "Manual Mode switched", Toast.LENGTH_SHORT).show();
                isManual=true;
                MusicManager.release();
                initialiseManualIfNotSelected();


            }

        }

    }

    private void initialiseManualIfNotSelected()
    {
        Log.e("InitManualIfNotSelec", "try to pass");
        AtmosphereByName atmo = new AtmosphereByName(getApplicationContext());
        if (listSmells == null ||listSmells.size() == 0)
        {

            listSmells = atmo.getAllSmell();
        }
        if (listSongs == null ||listSongs.size() == 0)
        {
            listSongs = atmo.getAllSongs();
        }
    }


    @Override
    public void displaySmellAuto() {

        Toast.makeText(getApplicationContext(), "Try to display : " +
                bookInitial.getListAtmosphere().get(getIndexCloser(bookInitial.getCurrentPage())), Toast.LENGTH_SHORT).show();

        try
        {
            if (rasp != null)
            {
                rasp.connect();
                if (!rasp.isConnected())
                {
                    Toast.makeText(getApplicationContext(),"Not connected to Raspberry Pi",Toast.LENGTH_SHORT).show();
                }else
                {
                    displayOnRP(rasp);
                }

            }

        } catch (
                NullPointerException e
                ) {
            Toast.makeText(getApplicationContext(),"Not connected to Raspberry Pi",Toast.LENGTH_SHORT).show();
        }

    }

    private void displayOnRP(RaspberryPi rasp )
    {
        String response = new String("");
        switch (bookInitial.getListAtmosphere().get(getIndexCloser(bookInitial.getCurrentPage())))
        {
            case "Field" :
                response =  rasp.setAgriculturalField();
                Toast.makeText(getApplicationContext() ,"Display "+ response,Toast.LENGTH_SHORT).show();
                break;
            case "Snow" :
                response = rasp.setChristmas();
                Toast.makeText(getApplicationContext() ,"Display "+ response,Toast.LENGTH_SHORT).show();
                break;
            case "Kitchens" :
                response = rasp.setCook();
                Toast.makeText(getApplicationContext() ,"Display "+ response,Toast.LENGTH_SHORT).show();
                break;
            case "Forest" :
                response = rasp.setForest();
                Toast.makeText(getApplicationContext() ,"Display "+ response,Toast.LENGTH_SHORT).show();
                break;
            case "Garden" :
                response = rasp.setFloralGarden();
                Toast.makeText(getApplicationContext() ,"Display "+ response,Toast.LENGTH_SHORT).show();
                break;
            case "Ocean" :
                response = rasp.setOcean();
                Toast.makeText(getApplicationContext() ,"Display "+ response,Toast.LENGTH_SHORT).show();
                break;
            default :
                Toast.makeText(getApplicationContext() ,"No sound available for " +bookInitial.getListAtmosphere().get(getIndexCloser(bookInitial.getCurrentPage()))  ,Toast.LENGTH_SHORT).show();
                break;

        }



    }


    public class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener(Context context) {
            gestureDetector = new GestureDetector(context, new GestureListener());
        }

        public void onSwipeLeft() {
        }

        public void onSwipeRight() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_DISTANCE_THRESHOLD = 50;
            private static final int SWIPE_VELOCITY_THRESHOLD = 50;

            @Override
            public boolean onDown(MotionEvent e) {
                return super.onDown(e);
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float distanceX = e2.getX() - e1.getX();
                float distanceY = e2.getY() - e1.getY();
                if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (distanceX > 0)
                        onSwipeRight();
                    else
                        onSwipeLeft();
                    return true;
                }
                return false;
            }
        }
    }


    /*
     *
     * For Playing in automatic mode
     *
     */

    @Override
    public void updateSongValue() {

        if (!MusicManager.isPlaying())
        {
            if (newFragAuto !=null)
            {
                newFragAuto.setPlaying(MusicManager.isPlaying());
            }
            playNewSong();
        } else
        {
            MusicManager.pause();
            Log.e("UpdateSongVal", "AlreadyPlaying");
        }
//        newFragAuto.updateValues(MusicManager.isPlaying());

    }



    private void playNewSong()
    {
        MusicManager.release();
        MusicManager.start(getApplicationContext(), bookInitial.getListAtmosphere().get(
              getIndexCloser(bookInitial.getCurrentPage()) ));
        displayAutomaticPop();
        displayAutomaticPopSmell();
    }


    private void updateAutoWPop(int new_page)
    {
        int a = getIndexCloser(new_page);

        if (! bookInitial.getListAtmosphere().get(a).equals(MusicManager.getAtmosphere()))
        {
            playNewSong();
        } else
        {
            Log.e("Update Automatic", " no changes");
        }

    }

    private void updateAutomatic(int new_page)
    {

        int a = getIndexCloser(new_page);
        ArrayList<String > atmosphereToDisplay = bookInitial.getListAtmosphere();
        int index_current = getIndexCloser(bookInitial.getCurrentPage());
        if (! bookInitial.getListAtmosphere().get(a).equals(MusicManager.getAtmosphere()))
        {
            playNewSong();
            displayAutomaticPop();
        } else
        {
            Log.e("Update Automatic", " no changes");
        }

    }


    private void displayAutomaticPop()
    {


        FragmentManager ft = getSupportFragmentManager();
//        Log.e("RESUME", bookInitial.getListAtmosphere() + " and " + bookInitial.getIndexChangementAtmosphere());
        int index_current = getIndexCloser(bookInitial.getCurrentPage());
        newFragAuto = FragmentPlayAutomatic.createFrag( bookInitial.getListAtmosphere().get(index_current),
                getApplicationContext());
        newFragAuto.setActionAct((FragmentPlayAutomatic.PlayAutomatic) this);
        newFragAuto.show(ft,"Current play automatic");
        newFragAuto.setRetainInstance(false);
        newFragAuto.setPlaying(MusicManager.isPlaying());
        if (!MusicManager.isPlaying())
        {
            playNewSong();
        }

    }

    private void displayAutomaticPopSmell()
    {
        FragmentManager ft = getSupportFragmentManager();
//        Log.e("RESUME", bookInitial.getListAtmosphere() + " and " + bookInitial.getIndexChangementAtmosphere());
        int index_current = getIndexCloser(bookInitial.getCurrentPage());
        myDialoSmellAuto = FragmentPlaySmellAuto.createFrag( bookInitial.getListAtmosphere().get(index_current));
        myDialoSmellAuto.setFragmentPlaySmellAuto((FragmentPlaySmellAuto.OnFragmentToPlaySmell) this);
        myDialoSmellAuto.show(ft,"Current play automatic");
        myDialoSmellAuto .setRetainInstance(false);
        displaySmellAuto();

    }


    private int getIndexCloser(int nbe)
    {
        ArrayList<Integer> indexChangement = bookInitial.getIndexChangementAtmosphere();

        for (int i = 0; i< indexChangement.size() ; i++)
        {
            if (nbe < indexChangement.get(i) )
            {
                return i-1;
            }
        }
        return indexChangement.size() - 1;

    }

}