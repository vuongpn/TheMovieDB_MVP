package com.example.moviemvp.movielist;

import com.example.moviemvp.model.Movie;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class MovieListPresenterTest {

    private MovieListPresenter presenter;
    @Mock
    private MovieListContract.View view;
    @Mock
    private MovieListContract.Model model;
    @Mock
    private List<Movie> arrayList;
    @Mock
    private Throwable t;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new MovieListPresenter(view, model);
    }

    @Test
    public void onDestroy() {
        presenter.onDestroy();

    }

    @Test
    public void requestDataFromServer() {
        presenter.requestDataFromServer();
        Mockito.verify(model).getMovieList(Mockito.any
                (MovieListContract.Model.OnFinishedListener.class)
                , Mockito.eq(1));
    }

    @Test
    public void onSuccess() {
        presenter.onSuccess(arrayList);
        Mockito.verify(view).onSuccess(arrayList);
    }

    @Test
    public void onFailure() {
        presenter.onFailure(t);
        view.onFailure(t);
    }
}