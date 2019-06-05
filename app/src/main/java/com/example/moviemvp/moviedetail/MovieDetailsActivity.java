package com.example.moviemvp.moviedetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviemvp.R;
import com.example.moviemvp.model.Movie;
import com.example.moviemvp.util.Constants;

import static com.example.moviemvp.util.Constants.KEY_MOVIE_ID;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsContract.View {

    private ImageView ivBackdrop;
    private TextView tvMovieTitle;
    private TextView tvOverview;
    private TextView tvHomepageValue;
    private MovieDetailsPresenter movieDetailsPresenter;
//    private String rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        initUI();
        Intent mIntent = getIntent();
        int movieId = mIntent.getIntExtra(KEY_MOVIE_ID, 0);
        movieDetailsPresenter = new MovieDetailsPresenter(this, new MovieDetailsModel());
        movieDetailsPresenter.requestMovieData(movieId);
    }

    private void initUI() {
        ivBackdrop = findViewById(R.id.iv_backdrop);
        tvMovieTitle = findViewById(R.id.tv_movie_title);
        tvOverview = findViewById(R.id.tv_movie_overview);
        tvHomepageValue = findViewById(R.id.tv_homepage_value);
//        EditText tvRate = findViewById(R.id.edtRate);
//        Button btn = findViewById(R.id.btn);
    }

    @Override
    public void setDataToViews(Movie movie) {
        if (movie != null) {
            tvMovieTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            Glide.with(this)
                    .load(Constants.BACKDROP_BASE_URL + movie.getBackdropPath())
                    .apply(new RequestOptions()
                    .placeholder(R.drawable.ic_place_holder)
                    .error(R.drawable.ic_place_holder))
                    .into(ivBackdrop);
            movie.getHomepage();
            tvHomepageValue.setText(movie.getHomepage());
        }

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Snackbar.make(findViewById(R.id.main_content)
                , getString(R.string.error_data)
                , Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieDetailsPresenter.onDestroy();
    }
}
