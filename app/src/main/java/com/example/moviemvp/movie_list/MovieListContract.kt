package com.example.moviemvp.movie_list

import com.example.moviemvp.model.Movie

interface MovieListContract {

    interface Model {

        interface OnFinishedListener {

            fun onSuccess(movieArrayList: List<Movie>)

            fun onFailure(t: Throwable)
        }

        fun getMovieList(onFinishedListener: OnFinishedListener, pageNo: Int)

    }

    interface View {

        fun onSuccess(movieList: List<Movie>)

        fun onFailure(t: Throwable)
    }

    interface Presenter {

        fun onDestroy()

        fun requestDataFromServer()
    }
}
