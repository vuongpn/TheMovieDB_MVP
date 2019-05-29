package com.example.moviemvp.moviedetail;

import com.example.moviemvp.model.Movie;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MovieDetailsPresenterTest {
    private MovieDetailsPresenter presenter;
    @Mock
    private MovieDetailsModel model;
    @Mock
    private MovieDetailsActivity view;
    private int movieId;
    private Throwable t;
    private Movie movie;
    private MovieDetailsContract.Model.OnFinishedListener a;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new MovieDetailsPresenter(view, model);
    }

    @Test
    public void onDestroy() {
        presenter.onDestroy();
    }

    @Test
    public void requestMovieData() {
        presenter.requestMovieData(movieId);
        model.getMovieDetails(a, movieId);
    }

    @Test
    public void onFinished() {
        presenter.onFinished(movie);
        view.setDataToViews(movie);
    }

    @Test
    public void onFailure() {
        presenter.onFailure(t);
        view.onResponseFailure(t);
    }
}