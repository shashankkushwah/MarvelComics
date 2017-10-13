package com.marvel.comics.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Shashank on 13/10/2017.
 */

public class Comic implements Parcelable, Comparable<Comic> {

    private long id;
    private String title;
    private String description;
    private int pageCount;
    private List<Price> prices;
    private ComicImage thumbnail;
    private CreatorsData creators;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public float getPrice() {
        float price = 0.0f;
        if (prices != null && !prices.isEmpty()) {
            price = prices.get(0).getPrice();
        }
        return price;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public ComicImage getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ComicImage thumbnail) {
        this.thumbnail = thumbnail;
    }

    public CreatorsData getCreators() {
        return creators;
    }

    public void setCreators(CreatorsData creators) {
        this.creators = creators;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeInt(this.pageCount);
        dest.writeList(this.prices);
        dest.writeParcelable(this.thumbnail, flags);
        dest.writeParcelable(this.creators, flags);
    }

    public Comic() {}

    protected Comic(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.description = in.readString();
        this.pageCount = in.readInt();
        this.prices = new ArrayList<>();
        in.readList(this.prices, Price.class.getClassLoader());
        this.thumbnail = in.readParcelable(ComicImage.class.getClassLoader());
        this.creators = in.readParcelable(CreatorsData.class.getClassLoader());
    }

    public static final Creator<Comic> CREATOR = new Creator<Comic>() {
        @Override
        public Comic createFromParcel(Parcel source) {return new Comic(source);}

        @Override
        public Comic[] newArray(int size) {return new Comic[size];}
    };

    @Override
    public int compareTo(@NonNull Comic other) {
        Float price = getPrice();
        Float otherPrice = other.getPrice();
        return price.compareTo(otherPrice);
    }

    public static Comparator<Comic> DESC_COMPARATOR = new Comparator<Comic>() {
        @Override
        public int compare(Comic o1, Comic o2) {
            Float o1Price = o1.getPrice();
            Float o2Price = o2.getPrice();
            return o2Price.compareTo(o1Price);
        }
    };
}
