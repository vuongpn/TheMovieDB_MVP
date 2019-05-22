package com.example.moviemvp.MovieList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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
    private int pageNo = 1;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private LinearLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie);
        initUI();
        setListeners();
        movieListPresenter = new MovieListPresenter(this);
        movieListPresenter.requestDataFromServer();
    }

    private void initUI() {
        rvMovieList = findViewById(R.id.rv_movie_list);
        moviesList = new ArrayList<>();
        moviesAdapter = new MoviesAdapter(this, moviesList);
        mLayoutManager = new LinearLayoutManager(this);
        rvMovieList.setLayoutManager(mLayoutManager);
        rvMovieList.setAdapter(moviesAdapter);

    }

    private void setListeners() {

        rvMovieList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = rvMovieList.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    movieListPresenter.getMoreData(pageNo);
                    loading = true;
                }

            }
        });
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure() {

    }

    @Override
    public void setDataToRecyclerView(List<Movie> movieArrayList) {
        moviesList.addAll(movieArrayList);
        moviesAdapter.notifyDataSetChanged();
        pageNo++;
    }


    @Override
    public void onResponseFailure(Throwable throwable) {
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
