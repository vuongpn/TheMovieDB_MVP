package com.example.moviemvp.movielist;

import com.example.moviemvp.model.Movie;

import java.util.List;

public class MovieListPresenter implements MovieListContract.Presenter, MovieListContract.Model.OnFinishedListener {
    private MovieListContract.View movieListView;
    private MovieListContract.Model movieListModel;

    MovieListPresenter(MovieListContract.View movieListView, MovieListContract.Model movieListModel) {
        this.movieListView = movieListView;
        this.movieListModel = movieListModel;
    }

    @Override
    public void onDestroy() {
        this.movieListView = null;
    }

    @Override
    public void requestDataFromServer() {
        movieListModel.getMovieList(this, 1);
    }

    @Override
    public void onSuccess(List<Movie> movieArrayList) {
        movieListView.onSuccess(movieArrayList);
    }

    @Override
    public void onFailure(Throwable t) {
        movieListView.onFailure(t);
    }
}