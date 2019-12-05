package com.dyasirvan.dicoding.moviecatalogue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dyasirvan.dicoding.moviecatalogue.R;
import com.dyasirvan.dicoding.moviecatalogue.api.TvShowApi;
import com.dyasirvan.dicoding.moviecatalogue.api.TvShowApiInterface;
import com.dyasirvan.dicoding.moviecatalogue.model.ResultsTvShow;
import com.dyasirvan.dicoding.moviecatalogue.model.TvShow;

import java.util.ArrayList;
import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ListViewHolder> {
    private List<ResultsTvShow> resultsTvShows;
    private Context context;

    public TvShowAdapter(List<ResultsTvShow> resultsTvShows, Context context) {
        this.resultsTvShows = resultsTvShows;
        this.context = context;
    }

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(ResultsTvShow data);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tvshow, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        ResultsTvShow tvShow = resultsTvShows.get(position);
        Glide.with(holder.itemView.getContext())
                .load(TvShowApi.poster_filename+tvShow.getPosterPath())
                .apply(new RequestOptions().override(55, 70))
                .into(holder.showImgPhoto);
        holder.tvShowJudul.setText(tvShow.getOriginalName());
        holder.tvShowDate.setText(tvShow.getFirstAirDate());
        holder.tvShowRate.setText(String.valueOf(tvShow.getVoteAverage()));
        holder.tvShowDesc.setText(tvShow.getOverview());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(resultsTvShows.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultsTvShows.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView showImgPhoto;
        TextView tvShowJudul, tvShowDate, tvShowRate, tvShowDesc;
        ListViewHolder(View itemView) {
            super(itemView);
            showImgPhoto = itemView.findViewById(R.id.img_item_tvs);
            tvShowJudul = itemView.findViewById(R.id.tv_item_tvs_judul);
            tvShowDate = itemView.findViewById(R.id.tv_item_tvs_date);
            tvShowRate = itemView.findViewById(R.id.tv_item_tvs_rate);
            tvShowDesc = itemView.findViewById(R.id.tv_item_tvs_desc);
        }
    }
}
