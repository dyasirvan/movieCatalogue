package com.dyasirvan.dicoding.moviecatalogue.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dyasirvan.dicoding.moviecatalogue.R;
import com.dyasirvan.dicoding.moviecatalogue.adapter.TvShowAdapter;
import com.dyasirvan.dicoding.moviecatalogue.api.TvShowApi;
import com.dyasirvan.dicoding.moviecatalogue.api.TvShowApiInterface;
import com.dyasirvan.dicoding.moviecatalogue.model.ResultsTvShow;
import com.dyasirvan.dicoding.moviecatalogue.model.TvShow;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowsFragment extends Fragment {
    private TvShowAdapter tvShowAdapter;
    private List<ResultsTvShow> arrayList = new ArrayList<>();
    private RecyclerView rvTV;
    private RecyclerView.LayoutManager layoutManager;
    public static final String key="2d0d3439ec8a418841ec93b927c779ff";
    public static final String lang="en-us";
    public static final int page=1;
    private ProgressBar progressBar;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tvshows, container, false);

        rvTV = root.findViewById(R.id.rv_tv_show);
        rvTV.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        rvTV.setLayoutManager(layoutManager);

        progressBar = root.findViewById(R.id.progress_bar);

        showLoading(true);
        loadJSON();

        return root;
    }

    public void loadJSON(){
        TvShowApiInterface tvShowApiInterface = TvShowApi.getApiCLient().create(TvShowApiInterface.class);
        Call<TvShow> call;
        call = tvShowApiInterface.getTvList(lang, page, key);
        call.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                arrayList = response.body().getResultsTvShows();
                tvShowAdapter = new TvShowAdapter(arrayList, getActivity());
                rvTV.setAdapter(tvShowAdapter);
                tvShowAdapter.notifyDataSetChanged();
                showLoading(false);

                tvShowAdapter.setOnItemClickCallback(new TvShowAdapter.OnItemClickCallback() {
                    @Override
                    public void onItemClicked(ResultsTvShow data) {
                        showSelectedTV(data);
                    }
                });
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {

            }
        });
    }
    private void showSelectedTV(ResultsTvShow resultsTvShow) {
        Intent intent = new Intent(getActivity(), DetailTvShowActivity.class);
        intent.putExtra("detail_tvshow",resultsTvShow);
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