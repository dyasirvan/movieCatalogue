package com.dyasirvan.dicoding.moviecatalogue.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dyasirvan.dicoding.moviecatalogue.R;
import com.dyasirvan.dicoding.moviecatalogue.model.Movie;

import java.util.ArrayList;

public class DetailMovieFragment extends Fragment {
    ImageView img;
    TextView judul, date, rate, desc;
    ArrayList<Movie> movies;
    Movie movie;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_movie, container, false);

        img = view.findViewById(R.id.img_detail_movie);
        judul = view.findViewById(R.id.tv_detail_judul);
        date = view.findViewById(R.id.tv_detail_date);
        rate = view.findViewById(R.id.tv_detail_rate);
        desc = view.findViewById(R.id.tv_detail_desc);

        Bundle bundle = this.getArguments();
        movie = bundle.getParcelable("detailMovie");
        judul.setText(movie.getJudul());
        date.setText(movie.getDate());
        rate.setText(movie.getRating());
        desc.setText(movie.getDeskripsi());
        Glide.with(getContext())
                .load(movie.getFoto())
                .apply(new RequestOptions().override(140, 200))
                .into(img);

        Toast.makeText(getContext(),movie.getJudul(),Toast.LENGTH_LONG).show();
        return view;
    }
}
