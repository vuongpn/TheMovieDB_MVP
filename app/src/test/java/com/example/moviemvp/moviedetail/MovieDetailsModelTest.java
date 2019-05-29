package com.example.moviemvp.moviedetail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MovieDetailsModelTest {
    @Mock
    private MovieDetailsModel model;
    private MovieDetailsContract.Model.OnFinishedListener a;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        model = new MovieDetailsModel();
    }

    @Test
    public void getMovieDetails() {
        model.getMovieDetails(a, Mockito.eq(1));
    }
}