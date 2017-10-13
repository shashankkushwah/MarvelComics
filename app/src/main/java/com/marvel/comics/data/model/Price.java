package com.marvel.comics.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shashank on 13/10/2017.
 */

public class Price implements Parcelable {

    private String type;
    private float price; //The price (all prices in USD).

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeFloat(this.price);
    }

    public Price() {}

    protected Price(Parcel in) {
        this.type = in.readString();
        this.price = in.readFloat();
    }

    public static final Creator<Price> CREATOR = new Creator<Price>() {
        @Override
        public Price createFromParcel(Parcel source) {return new Price(source);}

        @Override
        public Price[] newArray(int size) {return new Price[size];}
    };

}
