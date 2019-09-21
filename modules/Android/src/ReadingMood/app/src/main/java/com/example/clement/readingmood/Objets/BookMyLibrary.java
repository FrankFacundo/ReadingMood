package com.example.clement.readingmood.Objets;

import android.os.Parcel;
import android.os.Parcelable;

public class BookMyLibrary implements Parcelable {
    private String title;
    private String author;
    private String date;
    private String url;
    private long id;
    private String urlEpub;
    private String urlTxt;


    public BookMyLibrary(String title, String author, String date, String url)

    {
        this.title = title;
        this.author = author;
        this.date = date;
        this.url = url;
    }


    protected BookMyLibrary(Parcel in) {
        title = in.readString();
        author = in.readString();
        date = in.readString();
        url = in.readString();
        id = in.readLong();
        urlEpub = in.readString();
        urlTxt = in.readString();
    }

    public static final Creator<BookMyLibrary> CREATOR = new Creator<BookMyLibrary>() {
        @Override
        public BookMyLibrary createFromParcel(Parcel in) {
            return new BookMyLibrary(in);
        }

        @Override
        public BookMyLibrary[] newArray(int size) {
            return new BookMyLibrary[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }
    public void setId(long id)
    {
        this.id = id;
    }
    public long getId()
    {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrlEpub(String urlEpub)
    {
        this.urlEpub = urlEpub;
    }
    public String getUrlEpub()
    {
        return urlEpub;
    }

    public void setUrlTxt(String urlTxt)
    {
        this.urlTxt = urlTxt;
    }
    public String getUrlTxt()
    {
        return urlTxt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(date);
        dest.writeString(url);
        dest.writeLong(id);
        dest.writeString(urlEpub);
        dest.writeString(urlTxt);
    }
}
