package com.example.moviemvp.movie_list

import com.example.moviemvp.model.Movie

class MovieListPresenter internal constructor(private var movieListView: MovieListContract.View?)
    : MovieListContract.Presenter, MovieListContract.Model.OnFinishedListener {

    private val movieListModel: MovieListContract.Model

    init {

        movieListModel = MovieListModel()
    }

    override fun onDestroy() {

        this.movieListView = null
    }

    override fun requestDataFromServer() {

        movieListModel.getMovieList(this, 1)
    }

    override fun onSuccess(movieArrayList: List<Movie>) {

        movieListView!!.onSuccess(movieArrayList)
    }

    override fun onFailure(t: Throwable) {

        movieListView!!.onFailure(t)
    }
}