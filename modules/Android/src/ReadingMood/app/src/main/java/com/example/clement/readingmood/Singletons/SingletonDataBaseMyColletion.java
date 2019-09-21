package com.example.clement.readingmood.Singletons;

import android.util.Log;

import com.example.clement.readingmood.SQLite.DataBaseMyCollection;

public class SingletonDataBaseMyColletion {


    private static SingletonDataBaseMyColletion instance = null;

    private DataBaseMyCollection db;

    public static SingletonDataBaseMyColletion getInstance()
    {
        if (instance == null)
        {
            instance = new SingletonDataBaseMyColletion();
        }
        return instance;
    }

    public DataBaseMyCollection getDataBase() { return db; }
    public void setDataBaseMyCollection(DataBaseMyCollection db)
    {
        this.db = db;
    }
}
