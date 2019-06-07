package com.example.moviemvp.movie_list

import com.example.moviemvp.movie_list.MovieListContract.Model.OnFinishedListener
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

 class MovieListModelTest {

    private lateinit var model: MovieListModel
    private lateinit var listener: OnFinishedListener

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        model= MovieListModel()
        listener=Mockito.mock(OnFinishedListener::class.java)
    }

    @Test
    fun getMovieList() {

        model.getMovieList(listener, Mockito.eq(1))
    }
}