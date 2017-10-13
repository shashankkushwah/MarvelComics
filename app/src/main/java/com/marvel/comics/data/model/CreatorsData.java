package com.marvel.comics.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Shashank on 13/10/2017.
 */

public class CreatorsData implements Parcelable {

    private int available;

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public List<CreatorDetails> getItems() {
        return items;
    }

    public void setItems(List<CreatorDetails> items) {
        this.items = items;
    }

    private int returned;
    private List<CreatorDetails> items;

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.available);
        dest.writeInt(this.returned);
        dest.writeTypedList(this.items);
    }

    public CreatorsData() {}

    protected CreatorsData(Parcel in) {
        this.available = in.readInt();
        this.returned = in.readInt();
        this.items = in.createTypedArrayList(CreatorDetails.CREATOR);
    }

    public static final Creator<CreatorsData> CREATOR = new Creator<CreatorsData>() {
        @Override
        public CreatorsData createFromParcel(Parcel source) {return new CreatorsData(source);}

        @Override
        public CreatorsData[] newArray(int size) {return new CreatorsData[size];}
    };
}
