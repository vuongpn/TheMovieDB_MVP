package com.example.moviemvp.movielist;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MovieListModelTest {
    private MovieListModel model;
    private MovieListContract.Model.OnFinishedListener a;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        model = new MovieListModel();
    }

    @Test
    public void getMovieList() {
        model.getMovieList(a, Mockito.eq(1));

    }
}