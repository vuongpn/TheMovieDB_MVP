package com.example.moviemvp.movie_detail

import com.example.moviemvp.model.Movie

interface MovieDetailsContract {

    interface Model {

        interface OnFinishedListener {
            fun onFinished(movie: Movie)

            fun onFailure(t: Throwable)
        }

        fun getMovieDetails(onFinishedListener: OnFinishedListener, movieId: Int)
    }

    interface View {

        fun setDataToViews(movie: Movie)

        fun onResponseFailure(throwable: Throwable)
    }

    interface Presenter {
        fun onDestroy()

        fun requestMovieData(movieId: Int)
    }
}