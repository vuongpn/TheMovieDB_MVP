package com.example.moviemvp.moviedetail;

import com.example.moviemvp.model.Movie;

public class MovieDetailsPresenter implements MovieDetailsContract.Presenter, MovieDetailsContract.Model.OnFinishedListener {

    private MovieDetailsContract.View movieDetailView;
    private MovieDetailsContract.Model movieDetailsModel;

    MovieDetailsPresenter(MovieDetailsContract.View movieDetailView, MovieDetailsModel movieDetailsModel) {
        this.movieDetailView = movieDetailView;
        this.movieDetailsModel = movieDetailsModel;
    }

    @Override
    public void onDestroy() {
        movieDetailView = null;
    }

    @Override
    public void requestMovieData(int movieId) {
        movieDetailsModel.getMovieDetails(this, movieId);
    }

    @Override
    public void onFinished(Movie movie) {
        movieDetailView.setDataToViews(movie);
    }

    @Override
    public void onFailure(Throwable t) {
        movieDetailView.onResponseFailure(t);
    }
}
