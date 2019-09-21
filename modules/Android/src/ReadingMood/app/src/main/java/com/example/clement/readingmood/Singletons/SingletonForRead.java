package com.example.clement.readingmood.Singletons;

import android.util.Log;

import com.example.clement.readingmood.Objets.BookMyCollection;

import java.util.ArrayList;

public class SingletonForRead {

    private static SingletonForRead instance = null;

    private BookMyCollection myBook;

    public static SingletonForRead getInstance()
    {
        if (instance == null)
        {
            instance = new SingletonForRead();
            Log.e("SINGLETON FOR R", "first instance");

        }else
        {
            Log.e("SINGLETON FOR R", "not first ");
        }
        return instance;
    }

    public void setBooks(BookMyCollection  myBook)
    {
        this.myBook = myBook;

//        Log.e("SingletonForRead", "in set " +" in myBook after "+ String.valueOf(this.myBook.getListAtmosphere() == null) + " is listAtmosphere");
//        Log.e("SingletonForRead", "in set " +" in book "+ String.valueOf(myBook.getListAtmosphere() == null) + " is listAtmosphere");

    }
    public BookMyCollection getBooks()
    {
//        Log.e("SingletonForRead", " in get " + String.valueOf(myBook.getListAtmosphere() == null) + " is listAtmosphere");
        return myBook;
    }




}
