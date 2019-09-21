package com.example.clement.readingmood.Objets;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.clement.readingmood.R;

import java.util.ArrayList;

public class Atmosphere implements Parcelable {
    private String title;
    private int imageRessource;
    private ArrayList<String> description;
    private int pathToSong;


    public Atmosphere(String title, int imageRessource, ArrayList<String> description, int pathToSong)
    /*
    Used for the recycleView
     */
    {
        this.title = title;
        this.imageRessource = imageRessource;
        this.description = description;
        this.pathToSong = pathToSong;
    }

    protected Atmosphere(Parcel in) {
        title = in.readString();
        imageRessource = in.readInt();
        description = in.createStringArrayList();
        pathToSong = in.readInt();
    }

    public static final Creator<Atmosphere> CREATOR = new Creator<Atmosphere>() {
        @Override
        public Atmosphere createFromParcel(Parcel in) {
            return new Atmosphere(in);
        }

        @Override
        public Atmosphere[] newArray(int size) {
            return new Atmosphere[size];
        }
    };

    public String getTitle()
    {
        return title;
    }
    public int getImageRessource()
    {
        return imageRessource;
    }
    public ArrayList<String> getDescription(){ return description;}
    public int getPathToSong(){return pathToSong;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(imageRessource);
        dest.writeStringList(description);
        dest.writeInt(pathToSong);
    }
}
