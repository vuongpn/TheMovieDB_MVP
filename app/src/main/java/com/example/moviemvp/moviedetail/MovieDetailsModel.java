package com.example.moviemvp.moviedetail;

import android.support.annotation.NonNull;

import com.example.moviemvp.model.Movie;
import com.example.moviemvp.network.ApiClient;
import com.example.moviemvp.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.moviemvp.util.Constants.API_KEY;
import static com.example.moviemvp.util.Constants.CREDITS;

public class MovieDetailsModel implements MovieDetailsContract.Model {
    @Override
    public void getMovieDetails(final OnFinishedListener onFinishedListener, int movieId) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        apiService.getMovieDetails(movieId, API_KEY, CREDITS).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call,@NonNull Response<Movie> response) {
                Movie movie = response.body();
                onFinishedListener.onFinished(movie);
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call,@NonNull Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }
}