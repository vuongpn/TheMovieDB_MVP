package com.example.moviemvp.movie_detail

import com.example.moviemvp.movie_detail.MovieDetailsContract.Model.OnFinishedListener
import com.example.moviemvp.network.ApiInterface
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MovieDetailsModelTest {

    private var model: MovieDetailsModel = MovieDetailsModel()
    private lateinit var listener: OnFinishedListener

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        listener = Mockito.mock(OnFinishedListener::class.java)
    }

    @Test
    fun getMovieDetails() {

        model.getMovieDetails(listener, Mockito.eq(1))
    }
}