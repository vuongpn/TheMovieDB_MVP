package com.example.moviemvp.MovieList;

import com.example.moviemvp.Model.Movie;
import com.example.moviemvp.Model.MoviesResponse;
import com.example.moviemvp.network.ApiClient;
import com.example.moviemvp.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListModel implements MovieListContract.Model {


    @Override
    public void getMovieList(final OnFinishedListener onFinishedListener, int pageNo) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MoviesResponse> call = apiService.getPopularMovies("f7d99ad4fc2e4fff54e36188dcc15467", pageNo);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                List<Movie> movies = response.body().getResults();
                onFinishedListener.onFinished(movies);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}
