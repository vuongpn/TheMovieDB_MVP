package com.example.moviemvp.movie_list

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.moviemvp.R
import com.example.moviemvp.adapter.MoviesAdapter
import com.example.moviemvp.model.Movie
import com.example.moviemvp.movie_detail.MovieDetailsActivity
import com.example.moviemvp.util.Constants.KEY_MOVIE_ID

class MovieListActivity : AppCompatActivity(), MovieListContract.View, MoviesAdapter.OnListClickListener {
    private var movieListPresenter: MovieListPresenter? = null
    private var moviesAdapter: MoviesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movie)
        initUI()
        movieListPresenter = MovieListPresenter(this)
        movieListPresenter!!.requestDataFromServer()
    }

    private fun initUI() {
        val rvMovieList = findViewById<RecyclerView>(R.id.rv_movie_list)
        moviesAdapter = MoviesAdapter()
        val mLayoutManager = GridLayoutManager(this, 3)
        rvMovieList.layoutManager = mLayoutManager
        rvMovieList.adapter = moviesAdapter

        moviesAdapter!!.setOnListClickedListener(this)
    }

    override fun onSuccess(movies: List<Movie>) {
        moviesAdapter!!.setMovies(movies)
    }

    override fun onFailure(throwable: Throwable) {
        Toast.makeText(this, getString(R.string.communication_error), Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        movieListPresenter!!.onDestroy()
    }

    override fun onMovieClicked(movie: Movie, position: Int) {
        val detailIntent = Intent(this, MovieDetailsActivity::class.java)
        detailIntent.putExtra(KEY_MOVIE_ID, movie.id)
        startActivity(detailIntent)
    }
}
