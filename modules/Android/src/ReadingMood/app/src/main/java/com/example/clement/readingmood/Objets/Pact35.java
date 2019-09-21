package com.example.clement.readingmood.Objets;

import android.os.Parcel;
import android.os.Parcelable;

public class Pact35 implements Parcelable {

    private String name;
    private String module;
    private String description;
    private int photo;

    public Pact35(String name, String module, String description, int photo)
    {
        this.description = description;
        this.name = name;
        this.module = module;
        this.photo = photo;
    }

    protected Pact35(Parcel in) {
        name = in.readString();
        module = in.readString();
        description = in.readString();
        photo = in.readInt();
    }

    public static final Creator<Pact35> CREATOR = new Creator<Pact35>() {
        @Override
        public Pact35 createFromParcel(Parcel in) {
            return new Pact35(in);
        }

        @Override
        public Pact35[] newArray(int size) {
            return new Pact35[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(module);
        dest.writeString(description);
        dest.writeInt(photo);
    }
}
