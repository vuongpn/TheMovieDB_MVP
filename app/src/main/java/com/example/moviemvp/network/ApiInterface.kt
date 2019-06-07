package com.example.moviemvp.network

import com.example.moviemvp.model.Movie
import com.example.moviemvp.model.MoviesResponse
import com.example.moviemvp.model.Rate

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String, @Query("page") PageNo: Int): Call<MoviesResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String, @Query("append_to_response") credits: String): Call<Movie>

    @Headers("Content-Type:application/json;charset=utf-8")
    @POST("/movie/{movie_id}/rating")
    fun sendRate(@Field("movie_id") movieId: Int, @Field("api_key") apiKey: String): Call<Rate>
}

