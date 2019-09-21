package com.example.clement.readingmood.Pages;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.clement.readingmood.Adapters.LibraryAdapter.AdapterListAuthor;
import com.example.clement.readingmood.Adapters.LibraryAdapter.MyAdapterMyLibraryLetter;
import com.example.clement.readingmood.Adapters.LibraryAdapter.MyAdapterMyLibraryLetter2;
import com.example.clement.readingmood.Adapters.MyAdapterFragment;
import com.example.clement.readingmood.CheckForSDCard;
import com.example.clement.readingmood.Fragments.FragmentLibrary.AuthorFragmentLetter;
import com.example.clement.readingmood.Fragments.FragmentLibrary.BookLibraryFragment;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Objets.BookMyCollection;
import com.example.clement.readingmood.Objets.BookMyLibrary;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.SQLite.DataBaseMyCollection;
import com.example.clement.readingmood.Singletons.Singleton;
import com.example.clement.readingmood.Singletons.SingletonDataBaseMyColletion;

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
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.epub.EpubReader;


public class Library extends AppCompatActivity implements AdapterListAuthor.ChangePage, MyAdapterMyLibraryLetter.CreateDownload, MyAdapterMyLibraryLetter2.CreateDownload {

    private Toolbar myToolBar;
    /*
    Toolbar
     */
    private MyAdapterFragment adapter;
    /*
    Adapter for the tabLayout
     */
    private ViewPager viewPager;
    /*
    ViewPager for the tabLayout
     */
    private TabLayout tabLayout;
    /*
    TabLayout
     */

    private String listWeb;


    private ArrayList<String> listResult = new ArrayList<>();

    private ArrayList<String> listAtmosphere = new ArrayList<>();

    private ArrayList<Atmosphere> listSongs = new ArrayList<>();

    private ArrayList<Atmosphere> listSmells = new ArrayList<>();

    private ArrayList<BookMyCollection> listToTransmit = new ArrayList<>();

    private final String[] listAllSongs ={ "Admnistration", "Anger", "Attic", "Bathroom",
            "Bedroom", "Calm", "Car", "Castle", "Cave", "Cemetery",
            "Church", "Circus","Company", "Country Town","Desert", "Fear",
            "Field", "Fight","Fog", "Forest", "Garden", "Gunshot","Gym","Happy","Hospital", "Industry", "Jail",
            "Kitchens", "Library", "LivingRoom", "Love","Meadow", "Mine", "Mountain","Ocean",
            "Plane","Rain", "Sad", "School", "Snow","Street","Sun", "Thunder", "TrainStation", "Wind"};
    private final String[] listAllSmells ={ "Agricultural field","Christmas", "Cook","Forest","Floral garden"
            ,"Ocean"};

    private BookLibraryFragment fragBook ;

    private AuthorFragmentLetter fragAuthor ;

    private BookMyCollection bookInitial ;

    private Book myEbook;

    private ArrayList<String> listAllHtml = new ArrayList<>();

    private ArrayList<String> nameCss = new ArrayList<>();

    private ArrayList<String> currentCss = new ArrayList<>();

    private String baseHtml = new String();

    private ArrayList<String> bodyHtml = new ArrayList<String>();

    private ArrayList<String> listAllTextToDisplay = new ArrayList<>();

    private ArrayList<Integer> indexChangementAtmosphere = new ArrayList<>();

    private DataBaseMyCollection db;

    private boolean downloadSuccess = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        viewPager = findViewById(R.id.view_pager_library);
        /*
        Initialise the adapter
         */
        adapter = new MyAdapterFragment(getSupportFragmentManager());
        /*
        Add the two fragments for the tabLayout
         */

        db = SingletonDataBaseMyColletion.getInstance().getDataBase();

        fragBook = new BookLibraryFragment();
        fragAuthor = new AuthorFragmentLetter();
        adapter.addFragment(fragBook,"Books by name");
        adapter.addFragment(fragAuthor,"Books by author");
        viewPager.setAdapter(adapter);
        tabLayout = findViewById(R.id.tab_layout_id_library);
        tabLayout.setupWithViewPager(viewPager);
        this.configurizeToolBar();


    }

    @Override
    protected void onStop() {
        super.onStop();
        listWeb = new String();

    }

    private void configurizeToolBar()
    /*
    Initialisation of the toolbar and add the listener
     */
    {
        myToolBar = findViewById(R.id.my_toolbar_library);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        myToolBar.setNavigationIcon(R.drawable.back_button);
        myToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle myBundle = new Bundle();
                Intent intent = new Intent();
//                BookMyCollection newBook = new BookMyCollection("Test","KFC",R.drawable.logo_reading_mood,"nothing","nothing");
//                myBundle.putParcelableArrayList("backLibrary",listToTransmit);
//                Singleton.getInstance().setBooks(listToTransmit);
//                myBundle.putInt("testtest",sync);
                myBundle.putBoolean("addedDownload",true);
                intent.putExtras(myBundle);
                setResult(1, intent);
                finish();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String textResearch = data.getStringExtra("research");

        if (resultCode == 0)
        {
            fragBook.getBooksFromName(textResearch);

        } else if (resultCode == 1)
        {
            fragAuthor.getAuthorFromName(textResearch);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    /*
    Add the menu
     */
    {
        getMenuInflater().inflate(R.menu.menu_atmosphere,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    /*
    Add action to a click on an item of the toolbar
     */
    {
        int id = menuItem.getItemId();
        if ( id == R.id.menu_research)
        {
            Intent newIntent = new Intent(this, Research.class);
//            fragBook = null;
//            fragAuthor = null;
            newIntent.putExtra("code",tabLayout.getSelectedTabPosition());
            newIntent.setAction(String.valueOf(tabLayout.getSelectedTabPosition()));
            startActivityForResult(newIntent, 0);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }


    @Override
    public void changePage(String author, String date) {
//        onStop();
        Intent myIntent = new Intent(this, AboutAuthor.class);
        Bundle myBundle = new Bundle();
        myBundle.putString("MyAuthor",author);
        myBundle.putString("MyDate",date);
        myIntent.putExtras(myBundle);
        startActivity(myIntent);
    }




    @Override
    public void downloadForAutomatic(BookMyLibrary myBook, Context context) {

//        For tests
//        Date currentTime = Calendar.getInstance().getTime();
//        long before = currentTime.getTime();
//        for (int i = 0; i<40; i ++)
//        {
//            BookMyCollection nbook = convertLibToCollection(myBook, context);
//
//        }
//        long after = Calendar.getInstance().getTime().getTime();
//        long result = after - before;
//        Log.e("Time to download", " " + after + " and " + before + " total : " + result);


        BookMyCollection nbook = convertLibToCollection(myBook, context);
        if (downloadSuccess)
        {
            Log.e("DForAuto before",nbook.getIndexChangementAtmosphere().size() + " and " + nbook.
                    getListAtmosphere().size());
//        listToTransmit.add(nbook);
            for (BookMyCollection x : listToTransmit )
            {
                Log.e("DForAuto",x.getIndexChangementAtmosphere().size() + " and " + x.
                        getListAtmosphere().size());
            }

            db.insertData(nbook);
            Singleton.getInstance().setBooks(listToTransmit);
        } else
        {
            Toast.makeText(getApplicationContext(),"Download failed", Toast.LENGTH_LONG).show();
        }


    }




    private BookMyCollection convertLibToCollection(BookMyLibrary oldBook, Context context)
    {

        listAllHtml.clear();
        nameCss.clear();
        currentCss.clear();
        baseHtml = new String();
        bodyHtml.clear();
        listAllTextToDisplay.clear();
        listAtmosphere.clear();
        indexChangementAtmosphere.clear();
        BookMyCollection book_new  = new BookMyCollection(oldBook.getTitle(),oldBook.getAuthor(),R.drawable.logo_reading_mood,
                null,oldBook.getUrlTxt(), null,null,null,
                null,0);
        String linkToDownload = oldBook.getUrlEpub();
        getAllHtmlCss(linkToDownload);
        try {
            Library.TaskAfterServor myTastAfterServor = new TaskAfterServor(context,book_new);
            myTastAfterServor.execute(this).get();
        } catch (ExecutionException e) {
//            Toast.makeText(getApplicationContext(), "Download failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (InterruptedException e) {
//            Toast.makeText(getApplicationContext(), "Download failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (RuntimeException e)
        {
//            Toast.makeText(getApplicationContext(), "Download failed", Toast.LENGTH_SHORT).show();
        }
//        Log.e("In Conversion"," is " + bookInitial.getListAtmosphere().size() + " and " +
//        bookInitial.getIndexChangementAtmosphere().size());

        return book_new;
    }

    private boolean isBookAvailable()
    {
        if (  (bookInitial.getListAllTextToDisplay() !=null) && (bookInitial.getListAtmosphere() !=null ||
                bookInitial.getListForManual() !=null ) && (bookInitial.getIndexChangementAtmosphere() !=null) )
        {
            return true;
        }else
        {
            return false;
        }
    }

    private void addToBook(BookMyCollection myBook)
    {
        myBook.setListAtmosphere(listAtmosphere);
        myBook.setListForManual(null);
        myBook.setIndexChangementAtmosphere(indexChangementAtmosphere);
        myBook.setListAtmosphere(listAtmosphere);
        myBook.setListAllTextToDisplay(listAllTextToDisplay);
    }

    private void customizeCss()
    {
        String  newCss = "body \n" +
                "{\n" +
                "line-height :"+ 1.8 + ";\n" +
                "font-size:" + 1.6 + "rem;\n" +
                " text-align : center;" +
                "\n" +
                "}\n" +
                "body { font-family : " + "Times" +";" +
                "}" +
                ".bernardClass p\n" +
                "{\n" +
                "  margin : auto;\n" +
                "  text-align : center;\n" +
                "}";

        ArrayList<String> new_list = new ArrayList<>();
        for (String x : listAllTextToDisplay)
        {
            String new_string = x.replace("</style>", newCss + "</style>");
            new_list.add(new_string);
        }
        listAllTextToDisplay = new_list;
    }



    private void makeOtherPagesDisplayed()
    {

        ArrayList<String> new_list = parseOtherPages();
        int i = 0;
        for (String x : new_list)
        {
            if (i!=0)
            {
                listAllTextToDisplay.add(addCss(x));
            }
            i++;
        }

    }

    private String addCss(String html)
    {
        String result = html;
        if (!html.contains(baseHtml))
        {
            if (!html.contains("<body>"))
            {
                result = baseHtml + "<body>"+ result;
            }else
            {
                result = baseHtml + result;
            }
        }
        if (!html.contains("</body>"))
        {
            result += "</body></html>";
        }
        return result;

    }

    private ArrayList<String> parseOtherPages()
    {
        ArrayList<String> currentBodyPara = new ArrayList<>();
        indexChangementAtmosphere.clear();
        int indexB = 0;
        int indexE = 0;
        int indexBF = 0;
        int indexBE = 0;
        String new_string = new String();
        boolean begin = true;
        String base ;
        ArrayList<String> listBase = new ArrayList<>();
        int j = 0;
        for (String x : bodyHtml)
        {
            indexChangementAtmosphere.add(j);
            base = getBetweenBody(x);
            listBase.add(base);
            indexB = x.indexOf("<p");
            indexE = x.indexOf("</p>");
            indexBF = x.lastIndexOf("<p");
            indexBE = x.lastIndexOf("</p");
            while (indexB != indexBF)
            {
                if (begin)
                {
                    currentBodyPara.add(base);
                    new_string = x.substring(0, indexE + 4 );
                    begin = false;
                    j++;
                }else if (indexE == indexBE)
                {
                    new_string = x.substring(indexE + 4);
                }else
                {
                    new_string = x.substring(indexB, indexE + 4);
                }
                currentBodyPara.add(new_string);
                indexB = x.indexOf("<p", indexB + 1);
                indexE = x.indexOf("</p>", indexE + 1);
                j++;
            }
            j++;
        }
        ArrayList<String> new_currentBody = new ArrayList<>();
        int i = 0;
        int previous ;
        while( i < currentBodyPara.size() - 5 )
        {
            previous = i;
            if (indexChangementAtmosphere.contains(previous))
            {
//                Log.e("Library boucle", indexChangementAtmosphere.toString() + " and " + previous + " and " + new_currentBody.size());
                int index = indexChangementAtmosphere.indexOf(previous);
                indexChangementAtmosphere.set(index,new_currentBody.size());
            }
            String x = currentBodyPara.get(i);
            while (  (x.length() < 400) && i< (currentBodyPara.size() - 5)  )
            {
                i++;
                previous = i;
                if (indexChangementAtmosphere.contains(previous))
                {
//                    Log.e("Library boucle", indexChangementAtmosphere.toString() + " and " + previous + " and " + new_currentBody.size());
                    int index = indexChangementAtmosphere.indexOf(previous);
                    indexChangementAtmosphere.set(index,new_currentBody.size());
                }
                x += currentBodyPara.get(i);
            }
            i++;
            new_currentBody.add(x);

        }

        return new_currentBody;

    }

    private String getBetweenBody(String html)
    {
        int begin = html.indexOf("<p");
        String result = new String();
        if (begin !=-1)
        {
            result =html.substring(0,begin) ;
        }

        return result;
    }

    private void parseFirstPage()
    {
        String balise1 = "Contents";
        String balise2 = "CONTENT";
        String balise3 = "content";
        String myHtml = listAllHtml.get(0);
        int indexContent ;
        indexContent = myHtml.indexOf(balise1);
        if (indexContent == -1)
        {
            indexContent = myHtml.indexOf(balise2);
            if (indexContent == -1)
            {
                indexContent = myHtml.indexOf(balise3);
                if (indexContent == -1)
                {
                    indexContent = myHtml.indexOf("</tbody>") + 9  ;
                }
            }
        }

        String proHtml = myHtml.substring(0, indexContent);
        int last = getLastIndex("<",proHtml    );
        String content = proHtml.substring(0,  last);
        int last1 = getLastIndex("<",proHtml    );
        String other = myHtml.substring(  last1);
        listAllTextToDisplay.add(addCss(content));
        listAllTextToDisplay.add(addCss(other));

    }


    private int getLastIndex(String balise, String html)
    {
        int index = html.lastIndexOf(balise);
        return index;
    }


    private void getBody()
    {

        for (String x : listAllHtml)
        {
            int indexB = x.indexOf("< body");
            if (indexB == -1)
            {
                indexB = x.indexOf("<body");
            }
            int indexE = x.indexOf("</body>");
            if (indexE==-1)
            {
                indexE = x.indexOf("</ body>");
            }
            String toAdd = new String();
            if ( (indexB == -1) && (indexE !=-1))
            {
                toAdd =  x.substring( 0, indexE);
            }else if ( (indexB == -1) && (indexE ==-1)  )
            {
                toAdd = x ;
            }else if (  (indexB != -1) && (indexE !=-1))
            {
                toAdd = x.substring(indexB, indexE);

            }else if ( (indexB != -1) && (indexE ==-1) )
            {
                toAdd = x.substring(indexB);
            }
            bodyHtml.add(toAdd);

        }

    }

    private void getBase()
    {
        int index;
        String x = listAllHtml.get(0);
        index = x.indexOf("<body");
        baseHtml = x.substring(0,index);
    }

    private void treatHtmlToCss()
    {

        String newString = addCssToFirstHtml(currentCss, listAllHtml.get(0));
        ArrayList<String> newHtml = new ArrayList<>();
        newHtml.add(newString);
        for (int i = 1; i<listAllHtml.size(); i++)
        {
            newHtml.add(listAllHtml.get(i));
        }
        listAllHtml = newHtml;

    }


    private String addCssToFirstHtml(ArrayList<String> css, String html)
    {
        html += "</body><html>";
        String balise1 = "</head>";
        String addCss = "<style type=\"text/css\">";
        String toDelete = "<div class=\"pgmonospaced pgheader\">";
        int index1 = html.indexOf(toDelete);
        String balise = html.substring(index1);
        int indice = balise.indexOf("</div>");
        balise = balise.substring(0, indice);
        html = html.replace(balise, "");
        for (String x : css)
        {
            addCss += x;
        }
        addCss += "</style>\n";
        String newHtml = html.replace(balise1, addCss + balise1 );
        return newHtml;

    }


    private void getCss()
    {
        String balise1 = "LLLBERNARD-ALLCSS-BEGIN-LLL";
        String balise2 = "LLLBERNARD-ALLCSS-END-LLL";
        String balise3 = "LLLBERNARD-MIDCSS-NAME:";
        String balise4 = "-BEGIN-LLL";
        String balise5 = "LLLBERNARD-MIDCSS-END-LLL";
        int indexB = listWeb.indexOf(balise1);
        int indexE = listWeb.indexOf(balise2);
        String css = listWeb.substring(indexB,indexE).replace(balise1,"");
        nameCss.clear();
        currentCss.clear();
        int index3 = css.indexOf(balise3);
        int index4 = css.indexOf(balise4);
        int index5 = css.indexOf(balise5);
        while (index3 >= 0)
        {
            nameCss.add(css.substring(index3, index4).replace(balise3,""));
            currentCss.add(css.substring(index4, index5).replace(balise4,""));
            index3 = css.indexOf(balise3, index3 + 1);
            index4 = css.indexOf(balise4, index4 + 1);
            index5 = css.indexOf(balise5, index5 + 1 );

        }

    }



    private void getHtmlWithAtmosphere()
    {
        listAtmosphere = new ArrayList<>();
        ArrayList<String> listHtml = new ArrayList<>();
        ArrayList<String> listBefore = getHtmlWithoutAtmosphere();
        String balise1 = "LLLBERNARD-BEGINAMBIANCE-";
        String baliseI = "-LLL";
        String balise2 = "LLLBERNARD-ENDOFAMBIANCE-LLL";

        try
        {
            for (String x : listBefore)
            {

                try
                {
                    int indexB = x.indexOf(balise1);
                    int indexInter = x.indexOf(baliseI);
                    int indexE = x.indexOf(balise2);
                    String currentAtmosphere = x.substring(indexB,indexInter).replace(balise1,"");
                    String currentHtml = x.substring(indexInter,indexE).replace(baliseI, "");
                    listAtmosphere.add(currentAtmosphere);
                    listHtml.add(currentHtml);
//                listRealAtmosphere.add(atmosphereByName.getAtmosphereFromTitle(currentAtmosphere));

                } catch (RuntimeException e)
                {
                    Log.e("Erreur here", x);
                }

            }

        } catch (RuntimeException e)
        {
//            Toast.makeText(getApplicationContext(), "Not available to download",Toast.LENGTH_LONG).show();
            Log.e("Download async","Not available to download");
        }

        listAllHtml = listHtml;

    }


    private ArrayList<String> getHtmlWithoutAtmosphere()
    {

        ArrayList<String> listHtml = new ArrayList<>();
        ArrayList<String> oldHtml = getHtmlBeforeAtmosphere();
        String myHtml = new String();
        try
        {
            myHtml = oldHtml.get(0);

        } catch (IndexOutOfBoundsException e)
        {
            return null;
        }
        String balise1 = "LLLBERNARD-MIDHTML-BEGIN-LLL";
        String balise2 = "LLLBERNARD-MIDHTML-END-LLL";
        int indexB = myHtml.indexOf(balise1);
        int indexE = myHtml.indexOf(balise2);

        while (  (indexB >= 0) && (indexE >= 0) )
        {
            String currentHtml = myHtml.substring(indexB , indexE);
            currentHtml =    currentHtml.replaceAll(balise1, "");
            listHtml.add(currentHtml);
            indexB = myHtml.indexOf(balise1, indexB + 1);
            indexE = myHtml.indexOf(balise2 , indexE +1);
        }

        return listHtml;


    }

    private ArrayList<String> getHtmlBeforeAtmosphere()
    {
        ArrayList<String> pageHtml = new ArrayList<>();
        String balise1 = "LLLBERNARD-ALLHTML-BEGIN-LLL";
        String balise2 = "LLLBERNARD-ALLHTML-END-LLL";
        int indexB = listWeb.indexOf(balise1);
        int indexE = listWeb.indexOf(balise2);

        while (  (indexB >= 0) && (indexE >= 0) )
        {
            String currentHtml = listWeb.substring(indexB , indexE);
            currentHtml =    currentHtml.replaceAll(balise1, "");
            pageHtml.add(currentHtml);
            indexB = listWeb.indexOf(balise1, indexB + 1);
            indexE = listWeb.indexOf(balise2 , indexE +1);
        }

        return pageHtml;
    }



    private void getAllHtmlCss(String linkToDownload)
    {

        try {
            Library.DownloadEpubWithAtmosphere downloadEpubWithAtmosphere = new
                    Library.DownloadEpubWithAtmosphere(linkToDownload);
            listWeb = downloadEpubWithAtmosphere.execute(this).get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void getEbookFromLink(String linkToDownload)
    {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(linkToDownload);
            myEbook = (new EpubReader()).readEpub(fileInputStream);
            Log.e("IMAGE", " " + (myEbook.getCoverImage()==null));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void downloadEpubReaderFromLink(String url)
    {

        if (new CheckForSDCard().isSDCardPresent())
        {

            Library.DownloadFile downloadFile = new Library.DownloadFile();
            String result = new String();
            try {
                result = downloadFile.execute(url).get();
                bookInitial.setPathToRead( result );
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else
        {
            Toast.makeText(getApplicationContext(), "Permission denied for SD Card",Toast.LENGTH_SHORT);
        }

    }

    @Override
    public void downloadForAutomatic(BookMyLibrary myBook) {

        BookMyCollection nbook = convertLibToCollection(myBook, getApplicationContext());
        Log.e("DownloadAuto","before");
        if (downloadSuccess)
        {
            Log.e("DownloadAuto","success");
            db.insertData(nbook);
            Singleton.getInstance().setBooks(listToTransmit);
            SingletonDataBaseMyColletion.getInstance().setDataBaseMyCollection(db);
            Log.e("DownloadAuto","setSingeltonDb");

        } else
        {
            Toast.makeText(getApplicationContext(),"Download failed", Toast.LENGTH_LONG).show();
        }


    }


    private class DownloadEpubWithAtmosphere extends AsyncTask<Object, String, String> {

        private ProgressDialog progressDialog;
        private String link;
        private Library activity;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            this.progressDialog = new ProgressDialog(Library.this);
//            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            this.progressDialog.setMessage("Download link");
//            this.progressDialog.setCancelable(false);
////            this.progressDialog.show();
        }
        public DownloadEpubWithAtmosphere (String link){ this.link= link;}


        @Override
        protected String doInBackground(Object... params) {
            activity = (Library) params[0];
            try {
                StringBuilder sb = new StringBuilder();
                String begin = "https://readingmood-239210.appspot.com/treatepub/https://";
                URL url = new URL(begin + link.substring(1));
                BufferedReader in;
                in = new BufferedReader(
                        new InputStreamReader(
                                url.openStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    sb.append(inputLine);
                in.close();
//                Log.e("Download", "Url : "+ link + " new Url : "+ url + " result : "+ sb.toString());
                return sb.toString();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return "Something went wrong";
        }

        protected void onProgressUpdate(String... progress) {
//            progressDialog.setIndeterminate(false);
//            progressDialog.setMax(100);
//            progressDialog.setProgress(Integer.parseInt(progress[0]));

        }


        @Override
        protected void onPostExecute(String message) {
//            this.progressDialog.dismiss();

//            Toast.makeText(getApplicationContext(),
//                    message, Toast.LENGTH_LONG).show();
        }
    }



    private class DownloadFile extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;
        private String fileName;
        private String folder;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            this.progressDialog = new ProgressDialog(Library.this);
//            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            this.progressDialog.setMessage("Download File");
//            this.progressDialog.setCancelable(false);
//            this.progressDialog.show();
        }
        public DownloadFile(){}


        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                int lengthOfFile = connection.getContentLength();
                InputStream input = new BufferedInputStream(url.openStream(), 8192);
                fileName = bookInitial.getTitle();
                folder = Environment.getExternalStorageDirectory() + File.separator + "ReadingMood/";
                File directory = new File(folder);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                OutputStream output = new FileOutputStream(folder + fileName);
                byte data[] = new byte[1024];
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress("" + (int) ((total * 100) / lengthOfFile));
                    output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();

                return  folder + fileName;

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return "Something went wrong";
        }

        protected void onProgressUpdate(String... progress) {
//            progressDialog.setProgress(Integer.parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String message) {
//            this.progressDialog.dismiss();

//            Toast.makeText(getApplicationContext(),
//                    message, Toast.LENGTH_LONG).show();
        }
    }

    private void displayIndexes()
    {
        for (BookMyCollection x : listToTransmit )
        {
            Log.e("DForAuto",x.getIndexChangementAtmosphere().size() + " and " + x.
                    getListAtmosphere().size());
        }
    }


    private class TaskAfterServor extends AsyncTask<Object, Integer, Void>
    {

        private ProgressDialog progressDialog;
        private  Context myContext;
        BookMyCollection myBook;

        public TaskAfterServor(Context myContext, BookMyCollection new_book)
        {
            this.myBook = new_book;
            this.myContext = myContext;

        }

        @Override
        protected Void doInBackground(Object... objects) {
            try
            {

//                displayIndexes();
                getHtmlWithAtmosphere();
//                displayIndexes();
                getCss();
//                displayIndexes();
                treatHtmlToCss();
//                displayIndexes();
                getBase();
//                displayIndexes();
                getBody();
//                displayIndexes();
                parseFirstPage();
//                displayIndexes();
                makeOtherPagesDisplayed();
//                displayIndexes();
                customizeCss();
//                displayIndexes();
                addToBook(myBook);
//                displayIndexes();
                downloadSuccess = true;
            } catch (StringIndexOutOfBoundsException e)
            {
                downloadSuccess = false;
                Log.e("Donwload","error ");
            } catch (IndexOutOfBoundsException e)
            {
                downloadSuccess = false;
                Log.e("Donwload ", "error");
            } catch (RuntimeException e)
            {
                downloadSuccess = false;
                Log.e("Donwload ", "error");
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            this.progressDialog = new ProgressDialog(myContext);
//            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            this.progressDialog.setMessage("Get Data from servor");
//            this.progressDialog.setCancelable(false);
//            this.progressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer ... progress) {
//            progressDialog.setIndeterminate(false);
//            progressDialog.setMax(100);
//            progressDialog.setProgress(progress[0]);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
//            progressDialog.dismiss();
            super.onPostExecute(aVoid);
        }
    }





}