package com.dyasirvan.dicoding.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShow implements Parcelable {
    String show_judul;
    String show_rating;
    String show_date;
    String show_deskripsi;
    String show_foto;

    public String getShow_judul() {
        return show_judul;
    }

    public void setShow_judul(String show_judul) {
        this.show_judul = show_judul;
    }

    public String getShow_rating() {
        return show_rating;
    }

    public void setShow_rating(String show_rating) {
        this.show_rating = show_rating;
    }

    public String getShow_date() {
        return show_date;
    }

    public void setShow_date(String show_date) {
        this.show_date = show_date;
    }

    public String getShow_deskripsi() {
        return show_deskripsi;
    }

    public void setShow_deskripsi(String show_deskripsi) {
        this.show_deskripsi = show_deskripsi;
    }

    public String getShow_foto() {
        return show_foto;
    }

    public void setShow_foto(String show_foto) {
        this.show_foto = show_foto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(show_judul);
        parcel.writeString(show_date);
        parcel.writeString(show_rating);
        parcel.writeString(show_deskripsi);
        parcel.writeString(show_foto);
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }
        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    public  TvShow(){}
    protected TvShow(Parcel in) {
        show_judul = in.readString();
        show_date = in.readString();
        show_rating = in.readString();
        show_deskripsi = in.readString();
        show_foto = in.readString();
}}
