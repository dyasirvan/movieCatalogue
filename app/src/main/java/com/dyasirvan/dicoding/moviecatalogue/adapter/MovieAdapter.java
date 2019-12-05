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
import com.dyasirvan.dicoding.moviecatalogue.api.MovieApi;
import com.dyasirvan.dicoding.moviecatalogue.model.Movie;
import com.dyasirvan.dicoding.moviecatalogue.model.Results;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CardViewHolder> {
    private List<Results> resultsList;
    private Context context;

    public void setData(ArrayList<Results> items) {
        resultsList.clear();
        resultsList.addAll(items);
        notifyDataSetChanged();
    }

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(Results data);
    }

    public MovieAdapter(List<Results> resultsList, Context context) {
        this.resultsList = resultsList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, int position) {
        Results results = resultsList.get(position);
        holder.tvJudul.setText(results.getTitle());
        holder.tvDate.setText(results.getReleaseDate());
        holder.tvRate.setText(String.valueOf(results.getVoteAverage()));
        holder.tvDesc.setText(results.getOverview());
        Glide.with(holder.itemView.getContext())
                .load(MovieApi.poster_filename + results.getPosterPath())
                .apply(new RequestOptions().override(140, 200))
                .into(holder.imgPhoto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(resultsList.get(holder.getAdapterPosition()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvJudul, tvDate, tvRate, tvDesc;
//        OnItemClickListener onItemClickListener;
        public CardViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item);
            tvJudul = itemView.findViewById(R.id.tv_item_judul);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            tvRate = itemView.findViewById(R.id.tv_item_rate);
            tvDesc = itemView.findViewById(R.id.tv_item_desc);

        }


    }
}
