package com.example.moviemvp.movie_list

import com.example.moviemvp.model.MoviesResponse
import com.example.moviemvp.network.ApiClient
import com.example.moviemvp.network.ApiInterface
import com.example.moviemvp.util.Constants.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListModel : MovieListContract.Model {

    override fun getMovieList(onFinishedListener: MovieListContract.Model.OnFinishedListener, pageNo: Int) {

        val apiService = ApiClient.client!!.create(ApiInterface::class.java)

        apiService.getPopularMovies(API_KEY, pageNo).enqueue(object : Callback<MoviesResponse> {

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {

                assert(response.body() != null)
                val movies = response.body()!!.results
                onFinishedListener.onSuccess(movies!!)
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {

                onFinishedListener.onFailure(t)
            }
        })
    }
}
