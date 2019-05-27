package com.example.moviemvp.network;

import com.example.moviemvp.model.Movie;
import com.example.moviemvp.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("page") int PageNo);

    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetails(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("append_to_response") String credits);

//    @Headers("Content-Type:application/json;charset=utf-8")
//    @POST("/movie/{movie_id}/rating")
//    Call<Rate> sendRate(@Field("movie_id") int movieId, @Field("api_key") String apiKey);
}

