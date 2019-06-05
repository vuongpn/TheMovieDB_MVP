package com.example.moviemvp.movie_detail

import com.example.moviemvp.model.Movie
import com.example.moviemvp.network.ApiClient
import com.example.moviemvp.network.ApiInterface

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import com.example.moviemvp.util.Constants.API_KEY
import com.example.moviemvp.util.Constants.CREDITS

class MovieDetailsModel : MovieDetailsContract.Model {
    override fun getMovieDetails(onFinishedListener: MovieDetailsContract.Model.OnFinishedListener, movieId: Int) {

        val apiService = ApiClient.client!!.create(ApiInterface::class.java!!)

        apiService.getMovieDetails(movieId, API_KEY, CREDITS).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                response.body()!=null
                val movie:Movie = response.body()!!
                onFinishedListener.onFinished(movie)
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }
        })

    }
}