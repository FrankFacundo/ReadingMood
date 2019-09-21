package com.example.clement.readingmood.Fragments.FragmentLibrary;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
import android.util.TimingLogger;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.clement.readingmood.Adapters.CollectionAdapter.MyAdapterMyCollection;
import com.example.clement.readingmood.Adapters.LibraryAdapter.MyAdapterMyLibraryLetter;
import com.example.clement.readingmood.Fragments.FragmentAtmosphere.FragmentAtmosphereClick;
import com.example.clement.readingmood.Objets.BookMyCollection;
import com.example.clement.readingmood.Objets.BookMyLibrary;
import com.example.clement.readingmood.Pages.Library;
import com.example.clement.readingmood.R;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class FragmentLibraryLetter extends Fragment {

    private ArrayList<BookMyLibrary> list_displayed = new ArrayList<BookMyLibrary>();


    private View v;

    private RecyclerView myRecycle;
    /*
    Recycle View
     */
    private MyAdapterMyLibraryLetter madapter;
    /*
    Adapter for the recycle view
     */
    private String myLetter;

    protected String listWeb = new String();
    private ArrayList<String> listResult = new ArrayList<String>();
    private ArrayList<BookMyLibrary> listDisplay = new ArrayList<>();
    private boolean isName = true;



    public static FragmentLibraryLetter createFragment(ArrayList<BookMyLibrary> list_displayed)
    {
        Bundle args = new Bundle();

        ArrayList<Parcelable> listToPass = new ArrayList<>();
        for (BookMyLibrary x : list_displayed)
        {
            listToPass.add(x);
        }
        args.putParcelableArrayList("bookByLetter",listToPass);
        FragmentLibraryLetter fragment = new FragmentLibraryLetter();
        fragment.setArguments(args);
        return fragment;


    }

    public static FragmentLibraryLetter createFragment1( String letter)
    {

        Bundle args = new Bundle();
        args.putString("myLetter",letter);
        FragmentLibraryLetter fragment = new FragmentLibraryLetter();
        fragment.setArguments(args);
        return fragment;

    }


    public static FragmentLibraryLetter createFragment2( String name)
    {

        Bundle args = new Bundle();
        args.putString("name",name);
        FragmentLibraryLetter fragment = new FragmentLibraryLetter();
        fragment.setArguments(args);
        return fragment;

    }





    public FragmentLibraryLetter() {    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_my_library_letter, container, false);

        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);


        Bundle myBundle = getArguments();
        if (myBundle!=null)
        {
            if (myBundle.getParcelableArrayList("bookByLetter") != null)
            {
                ArrayList<Parcelable> listPassed = getArguments().getParcelableArrayList("bookByLetter");
                list_displayed = new ArrayList<>();
                for (Parcelable x : listPassed)
                {
                    list_displayed.add((BookMyLibrary) x);
                }
            }else if (myBundle.getString("myLetter") !=null)
            {
                myLetter = myBundle.getString("myLetter");
                isName = true;
            } else if (myBundle.getString("name") !=null)
            {

                myLetter = myBundle.getString("name");
                isName = false;

            }


        }


        getServerDatas();
        FragmentLibraryLetter.TaskAfterServor myTastServor = new FragmentLibraryLetter.TaskAfterServor(getContext());

        try {
            myTastServor.execute(this).get();

        } catch (ExecutionException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        getStringsFromData();



//        createListBooks();


//        For tests


//        ArrayList<Long> listTime = new ArrayList<>();
//        for (int i = 0 ; i < 40; i ++)
//        {
//            Log.e("This is ", " " + i);
//            listTime.add(createListBooksForTest());
//        }
//
//        long average = getAverage(listTime);
//        Log.e("Average is ", average + "");


        myRecycle = view.findViewById(R.id.recycler_view_letter);
        madapter = new MyAdapterMyLibraryLetter(listDisplay, getContext());
        madapter.makeCreation((MyAdapterMyLibraryLetter.CreateDownload) getContext()  );

        GridLayoutManager managerSmell1 ;

        managerSmell1 = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);

        //        myRecycle.setLayoutManager(new LinearLayoutManager(getActivity()))

        myRecycle.setLayoutManager(managerSmell1);
        myRecycle.setAdapter(madapter);
    }


    private long getAverage(ArrayList<Long> liste)
    {
        long a = 0;
        int n = liste.size();
        for (long x : liste)
        {
            a+= x;
        }
        Log.e("Average"," is "+ a);
        return a / n;
    }

    private void getServerDatas()
    {

        try {

            FragmentLibraryLetter.MyTask myTask = new FragmentLibraryLetter.MyTask(listWeb,myLetter, isName,getContext());
            listWeb = myTask.execute(this).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void getStringsFromData()
    {
        if (listWeb !=null)
        {

            listResult = new ArrayList<>();
            int indexB = listWeb.indexOf("(");
            int indexE= listWeb.indexOf(")");
            while (indexB != -1 && indexE != -1)
            {

                listResult.add(listWeb.substring(indexB+1,indexE));

                indexB = listWeb.indexOf("(", indexB + 1);
                indexE = listWeb.indexOf(")", indexE + 1);

            }

        Log.e("GetStringFromData", listResult.get(0));
        Log.e("GetStringFromData", listResult.get(1));
        }

    }

    private ArrayList<String> getDataFromStringV0(String singleData)
    {
        ArrayList<String> result = new ArrayList<>();
        String[] prov = singleData.split(",");
        for (int i = 0; i<prov.length ; i++)
        {
            result.add(prov[i].replace("'","").replace("\"",""));
        }

        return result;
    }

    private ArrayList<ArrayList<String>> getDataFromString(String singleData)
    {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        String baliseB = "(";
        String baliseE = ")";
        int indexB =  singleData.indexOf(baliseB);
        int indexE = singleData.indexOf(baliseE);

        while ((indexB !=-1) && (indexE !=-1))
        {
            String toAdd =singleData.substring(indexB+ 1,indexE);
            String[] prov = toAdd.split(",");
            ArrayList<String> provList = new ArrayList<>();
                for (int i = 0; i<prov.length ; i++)
                {
                    provList.add(prov[i].replace("'","").replace("\"",""));
                }

            result.add(provList);
            indexB =  singleData.indexOf(baliseB, indexB + 1);
            indexE = singleData.indexOf(baliseE, indexE + 1);
        }

        return result;

    }


    private long createListBooksForTest() {

        long before= 0;
        long after = 0;
        if (listResult != null) {
            Date currentTime = Calendar.getInstance().getTime();
            before = currentTime.getTime();
            for (String x : listResult) {
                try {


                    ArrayList<ArrayList<String>> futurBook = getDataFromString(x);
                    for (ArrayList<String> y : futurBook) {
                        try {
                            String author = y.get(0);
                            String date = y.get(1);
                            String title = y.get(2);
                            String urlTxt = y.get(3);
                            BookMyLibrary newBook = new BookMyLibrary(title, author, date, urlTxt);
                            newBook.setUrlTxt(urlTxt);
                            newBook.setUrlEpub(y.get(4));
                            listDisplay.add(newBook);
                        } catch (NullPointerException e) {

                        }

                    }


                } catch (IndexOutOfBoundsException e) {
//                    Log.e("FragmentLibraryLetter", "book does not contain everything ");
                }

            }
            after = Calendar.getInstance().getTime().getTime();
            Log.e("After", String.valueOf(after - before));

        }
        return after - before;

    }


    private void createListBooks()
    {

        if (listResult !=null)
        {
            Date currentTime = Calendar.getInstance().getTime();
            long before = currentTime.getTime();
//            Log.e("Before", String.valueOf(before));

            for (String x : listResult)
            {
                try
                {
                    ArrayList<String > resultBook = getDataFromStringV0(x);
//                    ArrayList<ArrayList<String>> futurBook = getDataFromString(x);
//                    Log.e("In create",futurBook.toString());
//                    for (ArrayList<String> y : futurBook)
//                    {
                        try
                        {
                            String author = resultBook .get(0);
                            String date = resultBook .get(1);
                            String title = resultBook .get(2);
                            String urlTxt = resultBook .get(3);
                            BookMyLibrary newBook = new BookMyLibrary(title,author,date,urlTxt);
                            newBook.setUrlTxt(urlTxt);
                            newBook.setUrlEpub(resultBook .get(4));
                            listDisplay.add(newBook);
                        } catch (NullPointerException e)
                        {

                        }

//                    }


                } catch (IndexOutOfBoundsException e)
                {
//                    Log.e("FragmentLibraryLetter", "book does not contain everything ");
                }

            }
            long after = Calendar.getInstance().getTime().getTime();
            Log.e("After", String.valueOf(after - before));

        }
    }


    private class TaskAfterServor extends AsyncTask<Object, Integer, Void>
    {

        private ProgressDialog progressDialog;
        private  Context myContext;

        public TaskAfterServor(Context myContext)
        {
            this.myContext = myContext;

        }

        @Override
        protected Void doInBackground(Object... objects) {
            getStringsFromData();
            createListBooks();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = new ProgressDialog(myContext);
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.progressDialog.setMessage("Get Data from servor");
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer ... progress) {
            progressDialog.setIndeterminate(false);
            progressDialog.setMax(100);
            progressDialog.setProgress(progress[0]);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            super.onPostExecute(aVoid);
        }
    }



    private static class MyTask extends AsyncTask<Object, String, String> {

        FragmentLibraryLetter activity;
        private String result;
        private String urlReceived;
        private String baseUrl ;
        private boolean forUrl;
        private ProgressDialog progressDialog;
        private Context myContext;

        public MyTask(String result, String urlReceived, boolean forUrl, Context myContext)
        {


            this.urlReceived = urlReceived;
            this.result = result;
            this.forUrl = forUrl;
            this.myContext = myContext;
            if (forUrl)
            {
                baseUrl = "https://readingmood-239210.appspot.com/searchfletterbook/";
            }else
            {
                baseUrl = "https://readingmood-239210.appspot.com/searchbook/";
            }

        }

        @Override
        protected String doInBackground(Object... params) {
            activity = (FragmentLibraryLetter) params[0];
            try {
                StringBuilder sb = new StringBuilder();
                URL url = new URL(baseUrl + urlReceived);
                Log.e("Letter", "this is : "+ baseUrl + urlReceived);
                BufferedReader in;
                in = new BufferedReader(
                        new InputStreamReader(
                                url.openStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    sb.append(inputLine);
                in.close();
                String res;
                res = sb.toString();
                Log.e("FragmentLibraryLetter",res);
                return res;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String str) {
            result = str;
            this.progressDialog.dismiss();
        }



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = new ProgressDialog(myContext);
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.progressDialog.setMessage("Get Data from servor");
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        @Override
        protected void onProgressUpdate(String... progress) {
            progressDialog.setIndeterminate(false);
            progressDialog.setMax(100);
            progressDialog.setProgress(Integer.parseInt(progress[0]));

        }

    }


        }



