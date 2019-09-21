package com.example.clement.readingmood.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.clement.readingmood.Objets.BookMyLibrary;

public class DataBaseLibrary extends SQLiteOpenHelper {



    public static final String DATABASE_NAME = "BookLibrary.db";
    public static final String TABLE_NAME = "book_table_library";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "AUTHOR";
    public static final String COL_4 = "DATE";
    public static final String COL_5 ="LINKTXT";
    public static final String COL_6 ="LINKEPUB";


    public DataBaseLibrary(Context context)
    {
        super(context,DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "NAME TEXT , AUTHOR TEXT, LINK TEXT)" );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(BookMyLibrary myBook)
    {
        long result = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues myContent = new ContentValues();

        myContent.put(COL_4, myBook.getDate());
        myContent.put(COL_2, myBook.getTitle());
        myContent.put(COL_3, myBook.getAuthor());
        myContent.put(COL_5, myBook.getUrlTxt());
        myContent.put(COL_6, myBook.getUrlEpub());
        result = db.insert(TABLE_NAME,null, myContent);
        myBook.setId(result);

        return (result!=-1);

    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+TABLE_NAME, null);
        return result;
    }

    public boolean deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_NAME,null,null);
        return (i!=-1);
    }


    public boolean delete(BookMyLibrary myBook)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_1 + "=" + myBook.getId(), null) > 0;

    }

    public int getIdFromValue(String title)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = COL_2 +" LIKE '%"+ title+"%'";

        Cursor c = db.rawQuery(
                "SELECT id  FROM"  + TABLE_NAME + "WHERE " + COL_2 + "= '"+title+"'" , null);
        if(c.getCount()>0)
            return c.getInt(0);
        else
            return 0;

    }







}
