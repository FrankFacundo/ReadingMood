package com.example.clement.readingmood.Objets;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

public class BookMyCollection implements Parcelable{
    private String title;
    private String author;
    private int imageRessource;
    private String summary;
    private String pathToRead;
    private ArrayList<Atmosphere> listSongs;
    private long id ;
    private ArrayList<String> listAtmosphere ;
    private ArrayList<String> listForManual ;
    private ArrayList<Integer> indexChangementAtmosphere;
    private ArrayList<String> listAllTextToDisplay ;
    private int currentPage;

    protected BookMyCollection(Parcel in) {
        title = in.readString();
        author = in.readString();
        imageRessource = in.readInt();
        summary = in.readString();
        pathToRead = in.readString();
        listSongs = in.createTypedArrayList(Atmosphere.CREATOR);
        id = in.readLong();
        listAtmosphere = in.createStringArrayList();
        listForManual = in.createStringArrayList();
        listAllTextToDisplay = in.createStringArrayList();
        listSmells = in.createTypedArrayList(Atmosphere.CREATOR);
        currentPage = in.readInt();
    }

    public static final Creator<BookMyCollection> CREATOR = new Creator<BookMyCollection>() {
        @Override
        public BookMyCollection createFromParcel(Parcel in) {
            return new BookMyCollection(in);
        }

        @Override
        public BookMyCollection[] newArray(int size) {
            return new BookMyCollection[size];
        }
    };

    public ArrayList<String> getListAtmosphere() {
        return listAtmosphere;
    }

    public void setListAtmosphere(ArrayList<String> listAtmosphere) {
        this.listAtmosphere = listAtmosphere;
    }

    public ArrayList<String> getListForManual() {
        return listForManual;
    }

    public void setListForManual(ArrayList<String> listForManual) {
        this.listForManual = listForManual;
    }

    public ArrayList<Integer> getIndexChangementAtmosphere() {
        return indexChangementAtmosphere;
    }

    public void setIndexChangementAtmosphere(ArrayList<Integer> indexChangementAtmosphere) {
        this.indexChangementAtmosphere = indexChangementAtmosphere;
    }

    public ArrayList<String> getListAllTextToDisplay() {
        return listAllTextToDisplay;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setListAllTextToDisplay(ArrayList<String> listAllTextToDisplay) {

        this.listAllTextToDisplay = listAllTextToDisplay;
        Log.e("Change list", " size : "+ listAllTextToDisplay.size() );
    }

    public void setListSongs(ArrayList<Atmosphere> listSongs) {
        this.listSongs = listSongs;
    }

    public void setListSmells(ArrayList<Atmosphere> listSmells) {
        this.listSmells = listSmells;
    }

    private ArrayList<Atmosphere> listSmells;

    public ArrayList<Atmosphere> getListSongs() {
        return listSongs;
    }

    public ArrayList<Atmosphere> getListSmells() {
        return listSmells;
    }



    public BookMyCollection(String title, String author, int imageRessource, String summary, String pathToRead,
                            ArrayList<String> listAtmosphere ,ArrayList<String> listForManual ,
                            ArrayList<Integer> indexChangementAtmosphere,
                            ArrayList<String> listAllTextToDisplay , int currentPage)
    /*
    Used for the recycle View
     */ {
        this.title = title;
        this.author = author;
        this.imageRessource = imageRessource;
        this.summary = summary;
        this.pathToRead = pathToRead;
        this.listAtmosphere= listAtmosphere;
        this.listForManual= listForManual;
        this.indexChangementAtmosphere = indexChangementAtmosphere;
        this.listAllTextToDisplay = listAllTextToDisplay;
        this.currentPage = currentPage;
    }



    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getImageRessource() {
        return imageRessource;
    }

    public String getSummary() {
        return summary;
    }

    public String getPathToRead() {
        return pathToRead;
    }

    public void setPathToRead(String pathToRead) {
        this.pathToRead = pathToRead;
    }

    public void setId(long newId) {this.id = newId;}

    public long getId(){return this.id;}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeInt(imageRessource);
        dest.writeString(summary);
        dest.writeString(pathToRead);
        dest.writeTypedList(listSongs);
        dest.writeLong(id);
        dest.writeStringList(listAtmosphere);
        dest.writeStringList(listForManual);
        dest.writeStringList(listAllTextToDisplay);
        dest.writeTypedList(listSmells);
        dest.writeInt(currentPage);
    }
}