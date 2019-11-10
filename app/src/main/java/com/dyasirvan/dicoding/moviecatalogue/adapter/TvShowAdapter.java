package com.dyasirvan.dicoding.moviecatalogue.adapter;

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
import com.dyasirvan.dicoding.moviecatalogue.model.TvShow;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ListViewHolder> {
    private ArrayList<TvShow> listTV;

    public TvShowAdapter(ArrayList<TvShow> list) {
        this.listTV = list;
    }

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(TvShow data);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tvshow, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        TvShow tvShow = listTV.get(position);
        Glide.with(holder.itemView.getContext())
                .load(tvShow.getShow_foto())
                .apply(new RequestOptions().override(55, 70))
                .into(holder.showImgPhoto);
        holder.tvShowJudul.setText(tvShow.getShow_judul());
        holder.tvShowDate.setText(tvShow.getShow_date());
        holder.tvShowRate.setText(tvShow.getShow_rating());
        holder.tvShowDesc.setText(tvShow.getShow_deskripsi());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listTV.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTV.size();
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
