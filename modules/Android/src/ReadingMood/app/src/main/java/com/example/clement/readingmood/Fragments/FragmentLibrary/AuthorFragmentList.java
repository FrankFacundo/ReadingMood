package com.example.clement.readingmood.Fragments.FragmentLibrary;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.clement.readingmood.Adapters.LibraryAdapter.AdapterListAuthor;
import com.example.clement.readingmood.Objets.BookMyLibrary;
import com.example.clement.readingmood.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class AuthorFragmentList extends Fragment {

    private View v;
    /*
    Current view
     */
    private RecyclerView myRecycle;
    /*
    Recycle View
     */

    private AdapterListAuthor madapter1;

    private ArrayList<String> listAuthor = new ArrayList<>();
    private ArrayList<String> listDates = new ArrayList<>();

    private String letter;

    private boolean isName = true;

    protected String listWeb = new String();

    private ArrayList<String> listResult = new ArrayList<String>();


    public AuthorFragmentList() {}


    public static AuthorFragmentList create(ArrayList<String> listAuthor, ArrayList<String> listDates)
    {

        Bundle myBundle = new Bundle();
        myBundle.putStringArrayList("listAuthor",listAuthor);
        myBundle.putStringArrayList("listDate",listDates);
        AuthorFragmentList myFrag = new AuthorFragmentList();
        myFrag.setArguments(myBundle);
        return myFrag;

    }

    public static AuthorFragmentList create1(String letter)
    {

        Bundle myBundle = new Bundle();
        myBundle.putString("letter",letter);
        AuthorFragmentList myFrag = new AuthorFragmentList();
        myFrag.setArguments(myBundle);
        return myFrag;

    }
    public static AuthorFragmentList create2(String name)
    {

        Bundle myBundle = new Bundle();
        myBundle.putString("name",name);
        AuthorFragmentList myFrag = new AuthorFragmentList();
        myFrag.setArguments(myBundle);
        return myFrag;

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*
        Initialise the view
         */
        v = inflater.inflate(R.layout.library_layout_list, container, false);

        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle myBundle = getArguments();
        if (myBundle !=null)
        {
            if ( (myBundle.getStringArrayList("listAuthor") !=null) && ( myBundle.getStringArrayList("listDate") !=null) )
            {
                listAuthor = myBundle.getStringArrayList("listAuthor");
                listDates = myBundle.getStringArrayList("listDate");

            }else if (myBundle.getString("letter") !=null)
            {
                letter = myBundle.getString("letter");
                isName = true;
            } else if (myBundle.getString("name") != null)
            {
                letter = myBundle.getString("name");
                isName = false;
            }



            getServerDatas();
//

            //        For tests



//
//
//
//
////            getStringsFromData();
////            createListBooks();
//
//            ArrayList<Long> listTime = new ArrayList<>();
//            for (int i = 0 ; i < 10000; i ++)
//            {
//                long new_data = createListBooksForTest();
//                Log.e("new data", new_data + "" );
//                listTime.add(new_data);
//            }
//
//            long average = getAverage(listTime);
//            Log.e("Average is ", average + "");


            TaskAfterServor myTastServor = new TaskAfterServor(getContext());

            try {
                myTastServor.execute(this).get();

            } catch (ExecutionException e) {

                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



            myRecycle = v.findViewById(R.id.recycle_view_library_list);
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            madapter1 = new AdapterListAuthor(getContext(), listAuthor, listDates);
            madapter1.makeChange( (AdapterListAuthor.ChangePage) getActivity() );
            GridLayoutManager managerSmell1 ;
            if (v.findViewById(R.id.tablette_author) !=null)
            {

                managerSmell1 =  new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
            }else
            {
                managerSmell1 =  new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);

            }


            myRecycle.setLayoutManager(managerSmell1);
            myRecycle.setAdapter(madapter1);


        }



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

            AuthorFragmentList.MyTask myTask = new AuthorFragmentList.MyTask(listWeb, letter, isName,getContext());
            listWeb = myTask.execute(this).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    protected void getStringsFromData()
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



        }

    }




    protected ArrayList<String> getDataFromString(String singleData)
    {

        ArrayList<String> result = new ArrayList<String>();

        String[] provList = singleData.split(",");
        for (int i = 0; i<provList.length ; i++)
            {
                result.add(provList[i].replace("'","").replace("\"",""));
            }

        return result;

    }


//        Date currentTime = Calendar.getInstance().getTime();
//        long before = currentTime.getTime();
//        long after = Calendar.getInstance().getTime().getTime();
//        Log.e("After", String.valueOf(after - before));


    protected void createListBooks()
    {


        if (listResult !=null)
        {
            listAuthor.clear();
            for (String x : listResult)
            {

                try
                {

                    ArrayList<String> resutlFromString = getDataFromString(x);
                    listAuthor.add(resutlFromString.get(0));
                    listDates.add(resutlFromString.get(1));

                } catch (IndexOutOfBoundsException e)
                {
                    Log.e("BookLibraryFragment", "book does not contain everything ");
                }

            }
        }
    }


    protected long createListBooksForTest()
    {

        Date currentTime = Calendar.getInstance().getTime();
        long before = currentTime.getTime();
        if (listResult !=null)
        {


            listAuthor.clear();
            for (String x : listResult)
            {

                try
                {

                    ArrayList<String> resutlFromString = getDataFromString(x);
                    listAuthor.add(resutlFromString.get(0));
                    listDates.add(resutlFromString.get(1));
                } catch (IndexOutOfBoundsException e)
                {
                    Log.e("BookLibraryFragment", "book does not contain everything ");
                }

            }
        }
        long after = Calendar.getInstance().getTime().getTime();
        Log.e("Before", before + " and after " + after);
        return after-before;
    }




    private class TaskAfterServor extends AsyncTask<Object, Integer, Void>
    {
        AuthorFragmentList activity;
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
//            this.progressDialog.show();
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



    private static class MyTask extends AsyncTask<Object, Integer, String> {

        AuthorFragmentList activity;
        private String result;
        private String urlReceived;
        private String baseUrl;
        private Context myContext;
        private ProgressDialog progressDialog;

        public MyTask(String result, String urlReceived, boolean forUrl, Context myContext)
        {
            this.urlReceived = urlReceived;
            this.result = result;
            this.myContext = myContext;


            if (forUrl)
            {
                baseUrl = "https://readingmood-239210.appspot.com/searchfletterauthor/";
            }else
            {
                baseUrl = "https://readingmood-239210.appspot.com/searchauthor/";
            }

        }

        @Override
        protected String doInBackground(Object... params) {
            activity = (AuthorFragmentList) params[0];
            try {
                StringBuilder sb = new StringBuilder();

                URL url = new URL(baseUrl + urlReceived);
//                Log.e("Current url", baseUrl + urlReceived);

                BufferedReader in;
                in = new BufferedReader(
                        new InputStreamReader(
                                url.openStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null )
                {
                    sb.append(inputLine);

                }
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
            this.progressDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = new ProgressDialog(myContext);
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.progressDialog.setMessage("Get Data from servor");
            this.progressDialog.setCancelable(false);
//            this.progressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer ... progress) {
            progressDialog.setIndeterminate(false);
            progressDialog.setMax(100);
            progressDialog.setProgress(progress[0]);

        }



    }





}



