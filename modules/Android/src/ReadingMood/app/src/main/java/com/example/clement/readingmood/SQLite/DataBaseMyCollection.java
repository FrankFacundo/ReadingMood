package com.example.clement.readingmood.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.clement.readingmood.Objets.BookMyCollection;
import com.example.clement.readingmood.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBaseMyCollection extends SQLiteOpenHelper {



    public static final String DATABASE_NAME = "Books.db";
    public static final String TABLE_NAME = "book_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "AUTHOR";
    public static final String COL_4 ="LINKIMAGE";
    public static final String COL_5 ="SUMMARY";
    public static final String COL_6 ="PATHTOREAD";
    public static final String COL_7 ="CONTENT";
    public static final String COL_8 ="LISTESONG";
    public static final String COL_9 ="LISTESMELL";
    public static final String COL_10 ="INDEXCHANGEMENT";
    public static final String COL_11 ="INDEXCURRENTPAGE";


    public DataBaseMyCollection(Context context)
    {
        super(context,DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE table " + TABLE_NAME +
                "(" + COL_1 + " INTEGER PRIMARY KEY," +
                COL_2 + " TEXT," + COL_3 + " TEXT," +
                COL_4 + " INTEGER," + COL_5 + " TEXT," +
                COL_6 + " TEXT," + COL_7 + " TEXT," +
                COL_8 + " TEXT," + COL_9 + " TEXT," +
                COL_10 + " TEXT," + COL_11 + " INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);


    }

    public boolean insertData(BookMyCollection myBook)
    {
        long result = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues myContent = new ContentValues();
        String new_listTodisplay = makeArrayListToList(myBook.getListAllTextToDisplay());
        String new_atmosphere = makeArrayListToList(myBook.getListAtmosphere());
        String new_indexes = makeArrayIntegerToString(myBook.getIndexChangementAtmosphere());
        myContent.put(COL_2, myBook.getTitle());
        myContent.put(COL_3, myBook.getAuthor());
        myContent.put(COL_4, myBook.getImageRessource() );
        myContent.put(COL_5, myBook.getSummary());
        myContent.put(COL_6, myBook.getPathToRead());
        myContent.put(COL_7, new_listTodisplay);
        myContent.put(COL_8, new_atmosphere);
        myContent.put(COL_9, String.valueOf(myBook.getListSmells()));
        myContent.put(COL_10, new_indexes);
        myContent.put(COL_11,  myBook.getCurrentPage() );

        result = db.insert(TABLE_NAME,null, myContent);
        myBook.setId(result);

        Log.e("DATABASE INSERT", String.valueOf("my  book is " + myBook.getListAllTextToDisplay().size()));
        Log.e("DATABASE INSERT ", "For listAtmosphere " + myBook.getIndexChangementAtmosphere().size() +
        " and "+ myBook.getListAtmosphere().size());
        return (result!=-1);

    }

    public Cursor getData(int id)
    {
        Log.e("Get data","With id : "+ id);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]
                {
                   COL_1,COL_2,COL_3,COL_4,COL_5,COL_6,COL_7,COL_8,COL_9,COL_10,COL_11
                }, COL_1 + "=?", new String[]
                        {
                                String.valueOf(id)
                        }, null,null,null,null

                );

        if (cursor !=null)
        {
            cursor.moveToFirst();
        }else
        {
            Log.e("Get data","Error : cursor is null");
        }
        return cursor;

    }

    public int updateBook(BookMyCollection myBook) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues myContent = new ContentValues();

        try
        {
            String new_listTodisplay = makeArrayListToList(myBook.getListAllTextToDisplay());
            String new_atmosphere = makeArrayListToList(myBook.getListAtmosphere());
            String new_indexes = makeArrayIntegerToString(myBook.getIndexChangementAtmosphere());
            myContent.put(COL_2, myBook.getTitle());
            myContent.put(COL_3, myBook.getAuthor());
            myContent.put(COL_4, myBook.getImageRessource() );
            myContent.put(COL_5, myBook.getSummary());
            myContent.put(COL_6, myBook.getPathToRead());
            myContent.put(COL_7, new_listTodisplay);
            myContent.put(COL_8, new_atmosphere);
            myContent.put(COL_9, String.valueOf(myBook.getListSmells()));
            myContent.put(COL_10, new_indexes);
            myContent.put(COL_11,  myBook.getCurrentPage() );

//            Log.e("DATABASE UPDATE", String.valueOf(new_atmosphere == null) + " and indexes " +
//                    String.valueOf(new_indexes == null));
            return db.update(TABLE_NAME, myContent, COL_2+ " LIKE ?",
                    new String[]{myBook.getTitle()});
        } catch (RuntimeException e)
        {
            return  -1;
        }

    }


    private String makeArrayIntegerToString(ArrayList<Integer> listToConvert)
    {
        String result = new String();
        if (listToConvert !=null)
        {
            for (int x : listToConvert)
            {
                result+= x + ",";
            }

        }
        return result;
    }


    public static ArrayList<Integer> makeStringToArrayInteger(String listeToConvert)
    {
        ArrayList<String> result_prov = new ArrayList<String>(Arrays.asList(listeToConvert.split(",")));
        ArrayList<Integer> result = new ArrayList<>();

        if (result_prov != null)
        {
            for (String x : result_prov)
            {
                try
                {
                    result.add(Integer.parseInt(x));
                } catch (RuntimeException e )
                {

                }

            }

        }

        return result;
    }


    public Cursor getAllData()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+TABLE_NAME, null);
        Log.e("DATABASE","getAllData");
        return result;

    }


//    public BookMyCollection getBookById(int id)
//    {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor data = db.rawQuery("select * from "+TABLE_NAME + " where "+ COL_1
//                + " =?" + new String[]{String.valueOf(id}, null);
//
//
//        String idd = data.getString(0);
//        String title = data.getString(1);
//        String author = data.getString(2);
//        int link_image = data.getInt(3);
//        String summary =data.getString(4);
//        String pathtoread =data.getString(5);
//        String content =data.getString(6);
//        String listesong =data.getString(7);
//        String listesmell =data.getString(8);
//        String listindexes = data.getString(9);
//        int currentPage = data.getInt(10);
//        BookMyCollection new_book = new BookMyCollection(title,author,link_image,summary,
//        pathtoread, null, null,
//        DataBaseMyCollection.makeStringToArrayInteger(listindexes),
//        DataBaseMyCollection.makeListToArray(content),currentPage);
//        return new_book;
//
//
//
//
//    }

    public boolean deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_NAME,null,null);
        Log.e("All data deleted","deleted");
        return (i!=-1);
    }

    public void delete(BookMyCollection myBook)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COL_2 + " LIKE ?";
        String[] selectionArgs = {String.valueOf(myBook.getTitle())};
        int a = db.delete(TABLE_NAME, selection, selectionArgs);
        Log.e("Id book", myBook.getId() + " ");
        Log.e("Retour","" + a);
    }



    private static String makeArrayListToList(ArrayList<String> listToConvert)
    {

        String result = new String();
        String balise = "bernardBegin&";
        if (listToConvert !=null)
        {  Log.e("DATABASEMYCOL", "Before : " + listToConvert.toString());
            for (String x : listToConvert)
            {
                result+=x + balise;
            }

        }
        Log.e("DATABASEMYCOL", "After : " + result);
        return result;

    }

    public static ArrayList<String> makeListToArray(String listeToConvert)
    {

        if (listeToConvert !=null)
        {
            String balise = "bernardBegin&";
            List<String> result =  Arrays.asList(listeToConvert.split(balise));
            ArrayList<String> new_result = new ArrayList<>();
            for (String x : result)
            {
                new_result.add(x);
            }

            Log.e("MAKELISTTOARRAY","Before : " + listeToConvert.length() );
            Log.e("MAKELISTTOARRAY","After : " + new_result.size() );

            return new_result;
        } else
        {
            return null;
        }


    }









}