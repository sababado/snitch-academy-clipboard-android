package com.sababado.snitchacademy.ins.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by rszabo on 10/25/13.
 */
public class Rating implements Parcelable{
    private String key;
    private float rating;

    public Rating(){
    }

    public Rating(String key, float rating) {
        this.rating = rating;
        this.key = key;
    }

    public Rating(final Parcel in) {
        key = in.readString();
        rating = in.readFloat();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public static final Creator<Rating> CREATOR = new Creator<Rating>() {
        @Override
        public Rating createFromParcel(Parcel source) {
            return new Rating(source);
        }

        @Override
        public Rating[] newArray(int size) {
            return new Rating[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeFloat(rating);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ((Object)this).getClass() != o.getClass()) return false;

        Rating rating1 = (Rating) o;

        if (rating != rating1.rating) return false;
        if (key != null ? !key.equals(rating1.key) : rating1.key != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (rating != +0.0f ? Float.floatToIntBits(rating) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "key='" + key + '\'' +
                ", rating=" + rating +
                "} " + super.toString();
    }
}
