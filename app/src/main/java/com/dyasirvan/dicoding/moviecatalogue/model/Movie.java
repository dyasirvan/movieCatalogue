package com.dyasirvan.dicoding.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    String judul;
    String rating;
    String date;
    String deskripsi;
    String foto;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(judul);
        parcel.writeString(date);
        parcel.writeString(rating);
        parcel.writeString(deskripsi);
        parcel.writeString(foto);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }
        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public  Movie(){};
    protected Movie(Parcel in) {
        judul = in.readString();
        date = in.readString();
        rating = in.readString();
        deskripsi = in.readString();
        foto = in.readString();
    }
}
