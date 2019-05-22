package com.example.moviemvp.MovieDetail;

import com.example.moviemvp.Model.Movie;
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

        Call<Movie> call = apiService.getMovieDetails(movieId, API_KEY, CREDITS);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                onFinishedListener.onFinished(movie);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

        apiService.getMovieDetails(movieId, API_KEY, CREDITS).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });

    }
}