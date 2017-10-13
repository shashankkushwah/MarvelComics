package com.marvel.comics.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shashank on 13/10/2017.
 */

public class CreatorDetails implements Parcelable {

    private String name;
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role + ": " + name;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.role);
    }

    public CreatorDetails() {}

    protected CreatorDetails(Parcel in) {
        this.name = in.readString();
        this.role = in.readString();
    }

    public static final Creator<CreatorDetails> CREATOR = new Creator<CreatorDetails>() {
        @Override
        public CreatorDetails createFromParcel(Parcel source) {return new CreatorDetails(source);}

        @Override
        public CreatorDetails[] newArray(int size) {return new CreatorDetails[size];}
    };
}
