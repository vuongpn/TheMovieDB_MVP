package com.example.moviemvp.MovieList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.moviemvp.Model.Movie;
import com.example.moviemvp.MovieDetail.MovieDetailsActivity;
import com.example.moviemvp.R;
import com.example.moviemvp.adapter.MoviesAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.moviemvp.util.Constants.KEY_MOVIE_ID;

public class MovieListActivity extends AppCompatActivity implements MovieListContract.View, MovieItemClickListener {
    private MovieListPresenter movieListPresenter;
    private RecyclerView rvMovieList;
    private List<Movie> moviesList;
    private MoviesAdapter moviesAdapter;
    private GridLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie);
        initUI();
        movieListPresenter = new MovieListPresenter(this);
        movieListPresenter.requestDataFromServer();
    }

    private void initUI() {
        rvMovieList = findViewById(R.id.rv_movie_list);
        moviesList = new ArrayList<>();
        moviesAdapter = new MoviesAdapter(this, moviesList);
        mLayoutManager = new GridLayoutManager(this, 3);
        rvMovieList.setLayoutManager(mLayoutManager);
        rvMovieList.setAdapter(moviesAdapter);

    }


    @Override
    public void onSuccess(List<Movie> movieArrayList) {
        moviesList.addAll(movieArrayList);
        moviesAdapter.notifyDataSetChanged();
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
    public void onMovieItemClick(int position) {
        Intent detailIntent = new Intent(this, MovieDetailsActivity.class);
        detailIntent.putExtra(KEY_MOVIE_ID, moviesList.get(position).getId());
        startActivity(detailIntent);
    }

}
