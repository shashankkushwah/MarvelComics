package com.marvel.comics.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shashank on 13/10/2017.
 */

public class ComicImage implements Parcelable {

    private String path;
    private String extension;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFullPath() {
        return path + '.' + extension;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeString(this.extension);
    }

    public ComicImage() {}

    protected ComicImage(Parcel in) {
        this.path = in.readString();
        this.extension = in.readString();
    }

    public static final Creator<ComicImage> CREATOR = new Creator<ComicImage>() {
        @Override
        public ComicImage createFromParcel(Parcel source) {return new ComicImage(source);}

        @Override
        public ComicImage[] newArray(int size) {return new ComicImage[size];}
    };
}
