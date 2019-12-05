package com.dyasirvan.dicoding.moviecatalogue.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dyasirvan.dicoding.moviecatalogue.R;
import com.dyasirvan.dicoding.moviecatalogue.adapter.MovieAdapter;
import com.dyasirvan.dicoding.moviecatalogue.api.MovieApi;
import com.dyasirvan.dicoding.moviecatalogue.api.MovieApiInterface;
import com.dyasirvan.dicoding.moviecatalogue.model.Movie;
import com.dyasirvan.dicoding.moviecatalogue.model.Results;
import com.dyasirvan.dicoding.moviecatalogue.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesFragment extends Fragment {

    private RecyclerView rvMovie;
    private RecyclerView.LayoutManager layoutManager;
    private List<Results> results = new ArrayList<>();
    private MovieAdapter adapter;
    public static final String key="2d0d3439ec8a418841ec93b927c779ff";
    public static final String lang="en-us";
    public static final int page=1;
    private ProgressBar progressBar;
    private MovieViewModel movieViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movies, container, false);



        rvMovie = root.findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        rvMovie.setLayoutManager(layoutManager);

        progressBar = root.findViewById(R.id.progress_bar);



        movieViewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);
        movieViewModel.getMovies().observe(getActivity(), new Observer<ArrayList<Results>>() {
            @Override
            public void onChanged(ArrayList<Results> results) {
                if(results != null){
                    adapter.setData(results);
                    showLoading(false);
                }
            }
        });

        showLoading(true);
        loadJSON();
        return root;
    }

    public void loadJSON(){
        MovieApiInterface movieApiInterface = MovieApi.getApiCLient().create(MovieApiInterface.class);
        Call<Movie> resultsCall;
        resultsCall = movieApiInterface.getMovieList(lang, page, key);
        resultsCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                results = response.body().getResults();
                adapter = new MovieAdapter(results, getActivity());
                rvMovie.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                showLoading(false);

                adapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
                    @Override
                    public void onItemClicked(Results data) {
                        showSelectedMovie(data);
                    }
                });

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }

    private void showSelectedMovie(Results results) {
        Intent intent = new Intent(getActivity(), DetailMovieActivity.class);
        intent.putExtra("detail",results);
        startActivity(intent);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}