package com.sababado.snitchacademy.ins.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by rszabo on 10/25/13.
 */
public class DrillRating implements Parcelable {
    private String title;
    private ArrayList<Rating> ratingsList;

    public DrillRating() {
        ratingsList = new ArrayList<Rating>();
    }

    public DrillRating(String title) {
        this();
        this.title = title;
    }

    public ArrayList<Rating> getRatingsList() {
        return ratingsList;
    }

    /**
     * Return a rating or null if it doesn't exist
     *
     * @param key
     * @return
     */
    public Rating getRating(final String key) {
        for (final Rating rating : ratingsList) {
            if (rating.getKey().equals(key)) {
                return rating;
            }
        }
        return null;
    }

    /**
     * Put a new rating in for this drill.
     *
     * @param key
     * @param rating
     */
    public void putRating(final String key, final float rating) {
        putRating(new Rating(key, rating));
    }

    /**
     * Put a new rating in for this drill.
     *
     * @param rating
     */
    public void putRating(final Rating rating) {

        for (final Rating r : ratingsList) {
            if (r.getKey().equals(rating.getKey())) {
                r.setRating(rating.getRating());
                return;
            }
        }
        ratingsList.add(rating);
        
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DrillRating(final Parcel in) {
        title = in.readString();
        final int size = in.readInt();
        ratingsList = new ArrayList<Rating>(size);
        for (int i = 0; i < size; i++) {
            ratingsList.add((Rating) in.readParcelable(Rating.class.getClassLoader()));
        }
    }

    public static final Creator<DrillRating> CREATOR = new Parcelable.Creator<DrillRating>() {
        @Override
        public DrillRating createFromParcel(Parcel source) {
            return new DrillRating(source);
        }

        @Override
        public DrillRating[] newArray(int size) {
            return new DrillRating[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        final int size = ratingsList.size();
        dest.writeInt(size);
        for (int i = 0; i < size; i++) {
            dest.writeParcelable(ratingsList.get(i), flags);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ((Object) this).getClass() != o.getClass()) return false;

        DrillRating that = (DrillRating) o;

        if (ratingsList != null ? !ratingsList.equals(that.ratingsList) : that.ratingsList != null)
            return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (ratingsList != null ? ratingsList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DrillRating{" +
                "title='" + title + '\'' +
                ", ratingsList=" + ratingsList +
                "} " + super.toString();
    }
}
