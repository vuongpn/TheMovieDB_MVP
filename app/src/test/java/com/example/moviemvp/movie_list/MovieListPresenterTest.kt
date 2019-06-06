package com.example.moviemvp.movie_list


import com.example.moviemvp.model.Movie
import com.example.moviemvp.movie_list.MovieListContract.View
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MovieListPresenterTest {
    private lateinit var presenter: MovieListPresenter
    private lateinit var view: View
    private lateinit var model: MovieListContract.Model
    private lateinit var arrayList:List<Movie>
    private lateinit var t:Throwable
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        view= Mockito.mock(View::class.java)
        model=Mockito.mock(MovieListContract.Model::class.java)
        presenter= MovieListPresenter(view)

     }

    @Test
    fun onDestroy() {
    presenter.onDestroy()
    }

    @Test
    fun requestDataFromServer() {
        presenter.requestDataFromServer()
//        Mockito.verify(model).getMovieList(Mockito.any(MovieListContract
//                .Model.OnFinishedListener::class.java),Mockito.eq(1))
    }

    @Test
    fun onSuccess() {
        presenter.onSuccess(arrayList)
        Mockito.verify(view).onSuccess(arrayList)
    }

    @Test
    fun onFailure() {
        presenter.onFailure(t)
        view.onFailure(t)
    }
}