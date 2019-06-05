package com.example.moviemvp.movielist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.example.moviemvp.R;
import com.example.moviemvp.adapter.MoviesAdapter;
import com.example.moviemvp.model.Movie;
import com.example.moviemvp.moviedetail.MovieDetailsActivity;

import java.util.List;

import static com.example.moviemvp.util.Constants.KEY_MOVIE_ID;

public class MovieListActivity extends AppCompatActivity implements MovieListContract.View, MoviesAdapter.OnListClickListener {
    private MovieListPresenter movieListPresenter;
    private MoviesAdapter moviesAdapter;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie);
        initUI();
        movieListPresenter = new MovieListPresenter(this, new MovieListModel());
        movieListPresenter.requestDataFromServer();
    }

    private void initUI() {
        RecyclerView rvMovieList = findViewById(R.id.rv_movie_list);
        moviesAdapter = new MoviesAdapter();
        rvMovieList.setLayoutManager(new GridLayoutManager(this, 3));
        rvMovieList.setAdapter(moviesAdapter);
        moviesAdapter.setOnListClickedListener(this);
    }

    @Override
    public void onSuccess(List<Movie> movies) {
        moviesAdapter.setMovies(movies);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Toast.makeText(this, getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieListPresenter.onDestroy();
    }

    @Override
    public void onMovieClicked(Movie movie, int position) {
        Intent detailIntent = new Intent(this, MovieDetailsActivity.class);
        detailIntent.putExtra(KEY_MOVIE_ID, movie.getId());
        startActivity(detailIntent);
    }
}
