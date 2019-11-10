package com.dyasirvan.dicoding.moviecatalogue.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dyasirvan.dicoding.moviecatalogue.R;
import com.dyasirvan.dicoding.moviecatalogue.adapter.MovieAdapter;
import com.dyasirvan.dicoding.moviecatalogue.model.Movie;

import java.util.ArrayList;

public class MoviesFragment extends Fragment {
    private MovieAdapter adapter;
    private String[] dataJudul;
    private String[] dataDate;
    private String[] dataRate;
    private String[] dataDescription;
    private String[] dataImg;
    private ArrayList<Movie> movies = new ArrayList<>();
    private RecyclerView rvMovie;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movies, container, false);

        rvMovie = root.findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);
        movies.addAll(getListMovies());
        showRecyclerList();

        return root;
    }

    public ArrayList<Movie> getListMovies() {
        dataJudul = getResources().getStringArray(R.array.data_judul);
        dataDate = getResources().getStringArray(R.array.data_date);
        dataRate = getResources().getStringArray(R.array.data_rate);
        dataDescription = getResources().getStringArray(R.array.data_deskripsi);
        dataImg = getResources().getStringArray(R.array.data_photo);
        ArrayList<Movie> listMovie = new ArrayList<>();
        for (int i = 0; i < dataJudul.length; i++) {
            Movie movie = new Movie();
            movie.setJudul(dataJudul[i]);
            movie.setDate(dataDate[i]);
            movie.setRating(dataRate[i]);
            movie.setDeskripsi(dataDescription[i]);
            movie.setFoto(dataImg[i]);
            listMovie.add(movie);
        }
        return listMovie;
    }

    private void showRecyclerList(){
        rvMovie.setLayoutManager(new LinearLayoutManager(
                getActivity()));
        adapter = new MovieAdapter(movies);
        rvMovie.setAdapter(adapter);
        adapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                showSelectedMovie(data);
            }
        });
    }
    private void showSelectedMovie(Movie movie) {
        //Toast.makeText(getActivity(), "Kamu memilih " + movie.getJudul(), Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailMovie", movie);

        DetailMovieFragment detailMovieFragment = new DetailMovieFragment();

        detailMovieFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, detailMovieFragment).addToBackStack(null).commit();
    }
}