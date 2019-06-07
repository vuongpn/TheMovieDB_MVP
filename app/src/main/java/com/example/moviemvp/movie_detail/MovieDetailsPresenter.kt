package com.example.moviemvp.movie_detail

import com.example.moviemvp.model.Movie

class MovieDetailsPresenter internal constructor(private var movieDetailView: MovieDetailsContract.View?) : MovieDetailsContract.Presenter, MovieDetailsContract.Model.OnFinishedListener {
    private val movieDetailsModel: MovieDetailsContract.Model

    init {
        this.movieDetailsModel = MovieDetailsModel()
    }

    override fun onDestroy() {
        movieDetailView = null
    }

    override fun requestMovieData(movieId: Int) {
        movieDetailsModel.getMovieDetails(this, movieId)
    }

    override fun onFinished(movie: Movie) {
        movieDetailView!!.setDataToViews(movie)
    }

    override fun onFailure(t: Throwable) {
        movieDetailView!!.onResponseFailure(t)
    }
}
