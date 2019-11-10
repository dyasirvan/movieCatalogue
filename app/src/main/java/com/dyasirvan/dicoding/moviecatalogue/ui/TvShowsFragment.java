package com.dyasirvan.dicoding.moviecatalogue.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dyasirvan.dicoding.moviecatalogue.R;
import com.dyasirvan.dicoding.moviecatalogue.adapter.TvShowAdapter;
import com.dyasirvan.dicoding.moviecatalogue.model.TvShow;

import java.util.ArrayList;

public class TvShowsFragment extends Fragment {
    private TvShowAdapter tvShowAdapter;
    private String[] dataTVJudul;
    private String[] dataTVDate;
    private String[] dataTVRate;
    private String[] dataTVDescription;
    private String[] dataTVImg;
    private ArrayList<TvShow> arrayList = new ArrayList<>();
    private RecyclerView rvTV;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tvshows, container, false);

        rvTV = root.findViewById(R.id.rv_tv_show);
        rvTV.setHasFixedSize(true);
        arrayList.addAll(getListTVShow());
        showRecyclerList();

        return root;
    }

    public ArrayList<TvShow> getListTVShow() {
        dataTVJudul = getResources().getStringArray(R.array.data_tvs_judul);
        dataTVDate = getResources().getStringArray(R.array.data_tvs_date);
        dataTVRate = getResources().getStringArray(R.array.data_tvs_rate);
        dataTVDescription = getResources().getStringArray(R.array.data_tvs_desc);
        dataTVImg = getResources().getStringArray(R.array.data_tvs_img);
        ArrayList<TvShow> listTV = new ArrayList<>();
        for (int i = 0; i < dataTVJudul.length; i++) {
            TvShow tvShow = new TvShow();
            tvShow.setShow_judul(dataTVJudul[i]);
            tvShow.setShow_date(dataTVDate[i]);
            tvShow.setShow_rating(dataTVRate[i]);
            tvShow.setShow_deskripsi(dataTVDescription[i]);
            tvShow.setShow_foto(dataTVImg[i]);
            listTV.add(tvShow);
        }
        return listTV;
    }

    private void showRecyclerList(){
        rvTV.setLayoutManager(new LinearLayoutManager(
                getActivity()));
        tvShowAdapter = new TvShowAdapter(arrayList);
        rvTV.setAdapter(tvShowAdapter);
        tvShowAdapter.setOnItemClickCallback(new TvShowAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TvShow data) {
                showSelectedTV(data);
            }
        });
    }
    private void showSelectedTV(TvShow tvShow) {
        Toast.makeText(getActivity(), "Kamu memilih " + tvShow.getShow_judul(), Toast.LENGTH_SHORT).show();
    }
}