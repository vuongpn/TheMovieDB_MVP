package com.example.moviemvp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviemvp.model.Movie;
import com.example.moviemvp.R;
import com.example.moviemvp.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> movieList = new ArrayList<>();
    private OnListClickListener listener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Movie movie = movieList.get(position);
        holder.bindData(movie, position);
    }

    public void setMovies(List<Movie> movies) {
        movieList.clear();
        movieList.addAll(movies);
        notifyDataSetChanged();
    }

//    public void appendMovies(List<Movie> movies) {
//        int startPos = movieList.size();
//        movieList.addAll(movies);
//        notifyItemRangeInserted(startPos, movies.size());
//    }
//
//    public void clearMovies() {
//        movieList.clear();
//        notifyDataSetChanged();
//    }notifyDataSetChanged

    public void setOnListClickedListener(OnListClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvMovieTitle;
        private TextView tvMovieRatings;
        private TextView tvReleaseDate;
        private ImageView ivMovieThumb;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            tvReleaseDate = itemView.findViewById(R.id.tv_release_date);
            tvMovieRatings = itemView.findViewById(R.id.tv_movie_ratings);
            ivMovieThumb = itemView.findViewById(R.id.iv_movie_thumb);
        }
        void bindData(final Movie movie, final int position) {
            tvMovieTitle.setText(movie.getTitle());
            tvMovieRatings.setText(String.valueOf(movie.getRating()));
            tvReleaseDate.setText(movie.getReleaseDate());
            Glide.with(itemView.getContext())
                    .load(Constants.IMAGE_BASE_URL + movie.getThumbPath())
                    .apply(new RequestOptions().placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder))
                    .into(ivMovieThumb);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onMovieClicked(movie, position);
                    }
                }
            });
        }
    }

    public interface OnListClickListener {

        void onMovieClicked(Movie movie, int position);
    }
}
