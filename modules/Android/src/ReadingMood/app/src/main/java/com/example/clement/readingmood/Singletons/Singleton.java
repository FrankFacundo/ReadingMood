package com.example.clement.readingmood.Singletons;

import android.util.Log;

import com.example.clement.readingmood.Objets.BookMyCollection;

import java.util.ArrayList;

public class Singleton {

    private static Singleton instance = null;

    private ArrayList<BookMyCollection> myBooks = new ArrayList<>();

    public static Singleton getInstance()
    {
        if (instance == null)
        {
            instance = new Singleton();
            Log.e("Instance","first instance");
        }           else
        {
            Log.e("Instance","Not the first one");
        }
        return instance;
    }

    public void setBooks(ArrayList<BookMyCollection> myBooks)
    {
        this.myBooks = myBooks;
    }

    public ArrayList<BookMyCollection> getBooks()
    {
        return myBooks;

    }


}
