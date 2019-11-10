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
import com.dyasirvan.dicoding.moviecatalogue.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CardViewHolder> {
    private ArrayList<Movie> listMovie;

    public MovieAdapter(ArrayList<Movie> list) {
        this.listMovie = list;
    }

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(Movie data);
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, int position) {
        final Movie movie = listMovie.get(position);
        Glide.with(holder.itemView.getContext())
                .load(movie.getFoto())
                .apply(new RequestOptions().override(140, 200))
                .into(holder.imgPhoto);
        holder.tvJudul.setText(movie.getJudul());
        holder.tvDate.setText(movie.getDate());
        holder.tvRate.setText(movie.getRating());
        holder.tvDesc.setText(movie.getDeskripsi());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listMovie.get(holder.getAdapterPosition()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvJudul, tvDate, tvRate, tvDesc;
        CardViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item);
            tvJudul = itemView.findViewById(R.id.tv_item_judul);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            tvRate = itemView.findViewById(R.id.tv_item_rate);
            tvDesc = itemView.findViewById(R.id.tv_item_desc);
        }
    }
}
