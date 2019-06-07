package com.example.moviemvp.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val BASE_URL = "http://api.themoviedb.org/3/"
    private var retrofit: Retrofit? = null
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185/"
    const val BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w185/"

    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }
}