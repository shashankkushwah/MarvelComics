package com.marvel.comics.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Shashank on 13/10/2017.
 */

public class ComicsData implements Parcelable {

    private int offset;
    private int limit;
    private int total;
    private int count;
    List<Comic> results;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Comic> getResults() {
        return results;
    }

    public void setResults(List<Comic> results) {
        this.results = results;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.offset);
        dest.writeInt(this.limit);
        dest.writeInt(this.total);
        dest.writeInt(this.count);
        dest.writeTypedList(this.results);
    }

    public ComicsData() {}

    protected ComicsData(Parcel in) {
        this.offset = in.readInt();
        this.limit = in.readInt();
        this.total = in.readInt();
        this.count = in.readInt();
        this.results = in.createTypedArrayList(Comic.CREATOR);
    }

    public static final Creator<ComicsData> CREATOR = new Creator<ComicsData>() {
        @Override
        public ComicsData createFromParcel(Parcel source) {return new ComicsData(source);}

        @Override
        public ComicsData[] newArray(int size) {return new ComicsData[size];}
    };
}
