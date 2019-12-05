package com.dyasirvan.dicoding.moviecatalogue.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dyasirvan.dicoding.moviecatalogue.R;
import com.dyasirvan.dicoding.moviecatalogue.api.MovieApi;
import com.dyasirvan.dicoding.moviecatalogue.model.Movie;
import com.dyasirvan.dicoding.moviecatalogue.model.Results;

import java.util.ArrayList;

public class DetailMovieActivity extends AppCompatActivity {
    ImageView img;
    TextView judul, date, rate, desc;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
//        Toolbar toolbar = findViewById(R.id.detail_toolbar);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        progressBar = findViewById(R.id.progress_bar_detail);

        showLoading(true);
        Results results = getIntent().getParcelableExtra("detail");

        img = findViewById(R.id.img_detail_movie);
        judul = findViewById(R.id.tv_detail_judul);
        date = findViewById(R.id.tv_detail_date);
        rate = findViewById(R.id.tv_detail_rate);
        desc = findViewById(R.id.tv_detail_desc);

        judul.setText(results.getTitle());
        date.setText(results.getReleaseDate());
        rate.setText(String.valueOf(results.getVoteAverage()));
        desc.setText(results.getOverview());
        Glide.with(this)
                .load(MovieApi.poster_filename + results.getPosterPath())
                .apply(new RequestOptions().override(170, 200))
                .into(img);
        showLoading(false);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
