package com.example.moviemvp.movie_list


import com.example.moviemvp.model.Movie
import com.example.moviemvp.movie_list.MovieListContract.Model.OnFinishedListener
import com.example.moviemvp.movie_list.MovieListContract.View
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import java.util.*

class MovieListPresenterTest {

    private lateinit var presenter: MovieListPresenter
    private lateinit var view: View
    @Mock
    private lateinit var model: MovieListContract.Model
    @Mock
    private lateinit var listener: OnFinishedListener
    private var arrayList: List<Movie> = ArrayList()
    private var t = Throwable()

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        view = mock(View::class.java)
        presenter = MovieListPresenter(view)
        listener= mock(OnFinishedListener::class.java)
    }

    @Test
    fun onDestroy() {

        presenter.onDestroy()
    }

    @Test
    fun requestDataFromServer() {

        presenter.requestDataFromServer()
        model.getMovieList(listener,1)
    }

    @Test
    fun onSuccess() {

        presenter.onSuccess(arrayList)
        Mockito.verify(view).onSuccess(arrayList)
    }

    @Test
    fun onFailure() {

        presenter.onFailure(t)
        Mockito.verify(view).onFailure(t)
    }
}