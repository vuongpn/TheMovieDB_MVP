package com.example.moviemvp.movie_list;

import com.example.moviemvp.model.Movie;
import com.example.moviemvp.model.MoviesResponse;
import com.example.moviemvp.network.ApiClient;
import com.example.moviemvp.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.moviemvp.util.Constants.API_KEY;

public class MovieListModel implements MovieListContract.Model {
    @Override
    public void getMovieList(final OnFinishedListener onFinishedListener, int pageNo) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        apiService.getPopularMovies(API_KEY, pageNo).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                assert response.body() != null;
                List<Movie> movies = response.body().getResults();
                onFinishedListener.onSuccess(movies);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}