package com.example.moviemvp.MovieDetail;

import com.example.moviemvp.Model.Movie;

public class MovieDetailsPresenter implements MovieDetailsContract.Presenter, MovieDetailsContract.Model.OnFinishedListener {
    private MovieDetailsContract.View movieDetailView;
    private MovieDetailsContract.Model movieDetailsModel;

    MovieDetailsPresenter(MovieDetailsContract.View movieDetailView) {
        this.movieDetailView = movieDetailView;
        this.movieDetailsModel = new MovieDetailsModel();
    }

    @Override
    public void onDestroy() {

        movieDetailView = null;
    }

    @Override
    public void requestMovieData(int movieId) {

        if (movieDetailView != null) {
            movieDetailView.onSuccess();
        }
        movieDetailsModel.getMovieDetails(this, movieId);
    }

    @Override
    public void onFinished(Movie movie) {

        if (movieDetailView != null) {
            movieDetailView.onFailure();
        }
        movieDetailView.setDataToViews(movie);
    }

    @Override
    public void onFailure(Throwable t) {
        if (movieDetailView != null) {
            movieDetailView.onFailure();
        }
        movieDetailView.onResponseFailure(t);
    }
}
