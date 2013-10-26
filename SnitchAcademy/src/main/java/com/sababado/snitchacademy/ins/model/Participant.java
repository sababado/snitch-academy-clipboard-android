package com.sababado.snitchacademy.ins.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rszabo on 10/25/13.
 */
public class Participant implements Parcelable {
    public String name;
    public DrillRating pokerface;
    public DrillRating arena;
    public DrillRating enduranceArena;
    public DrillRating noContact;
    public DrillRating blockade;
    public DrillRating chasePlus;

    public Participant() {
    }

    public Participant(final String name) {
        this.name = name;
    }

    public Participant(final Parcel in) {
        name = in.readString();
        pokerface = in.readParcelable(DrillRating.class.getClassLoader());
        arena = in.readParcelable(DrillRating.class.getClassLoader());
        enduranceArena = in.readParcelable(DrillRating.class.getClassLoader());
        noContact = in.readParcelable(DrillRating.class.getClassLoader());
        blockade = in.readParcelable(DrillRating.class.getClassLoader());
        chasePlus = in.readParcelable(DrillRating.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(pokerface, flags);
        dest.writeParcelable(arena, flags);
        dest.writeParcelable(enduranceArena, flags);
        dest.writeParcelable(noContact, flags);
        dest.writeParcelable(blockade, flags);
        dest.writeParcelable(chasePlus, flags);
    }

    public static final Creator<Participant> CREATOR = new Parcelable.Creator<Participant>() {
        @Override
        public Participant createFromParcel(Parcel source) {
            return new Participant(source);
        }

        @Override
        public Participant[] newArray(int size) {
            return new Participant[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ((Object) this).getClass() != o.getClass()) return false;

        Participant that = (Participant) o;

        if (arena != null ? !arena.equals(that.arena) : that.arena != null) return false;
        if (blockade != null ? !blockade.equals(that.blockade) : that.blockade != null)
            return false;
        if (chasePlus != null ? !chasePlus.equals(that.chasePlus) : that.chasePlus != null)
            return false;
        if (enduranceArena != null ? !enduranceArena.equals(that.enduranceArena) : that.enduranceArena != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (noContact != null ? !noContact.equals(that.noContact) : that.noContact != null)
            return false;
        if (pokerface != null ? !pokerface.equals(that.pokerface) : that.pokerface != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (pokerface != null ? pokerface.hashCode() : 0);
        result = 31 * result + (arena != null ? arena.hashCode() : 0);
        result = 31 * result + (enduranceArena != null ? enduranceArena.hashCode() : 0);
        result = 31 * result + (noContact != null ? noContact.hashCode() : 0);
        result = 31 * result + (blockade != null ? blockade.hashCode() : 0);
        result = 31 * result + (chasePlus != null ? chasePlus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name='" + name + '\'' +
                ", pokerface=" + pokerface +
                ", arena=" + arena +
                ", enduranceArena=" + enduranceArena +
                ", noContact=" + noContact +
                ", blockade=" + blockade +
                ", chasePlus=" + chasePlus +
                "} " + super.toString();
    }
}
