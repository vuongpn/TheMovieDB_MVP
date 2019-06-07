package com.example.moviemvp.movie_detail

import com.example.moviemvp.model.Movie
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MovieDetailsPresenterTest {

    private lateinit var presenter: MovieDetailsPresenter
    @Mock
    private lateinit var view: MovieDetailsContract.View
    private var t: Throwable = Throwable()
    private var movie=Movie(1,"","",1F,"","",""
    ,"","","")
    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        presenter = MovieDetailsPresenter(view)
    }

    @Test
    fun onDestroy() {

        presenter.onDestroy()
    }

    @Test
    fun requestMovieData() {

        presenter.requestMovieData(1)
    }

    @Test
    fun onFinished() {

        presenter.onFinished(movie)
    }

    @Test
    fun onFailure() {

        presenter.onFailure(t)
        Assert.assertEquals(view.onResponseFailure(t), Unit)
    }
}