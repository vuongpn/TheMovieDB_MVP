package com.example.moviemvp.movie_detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.moviemvp.R
import com.example.moviemvp.model.Movie
import com.example.moviemvp.network.ApiClient
import com.example.moviemvp.util.Constants.KEY_MOVIE_ID

class MovieDetailsActivity : AppCompatActivity(), MovieDetailsContract.View {

    private var ivBackdrop: ImageView? = null
    private var tvMovieTitle: TextView? = null
    private var tvOverview: TextView? = null
    private var tvHomepageValue: TextView? = null
    private var movieDetailsPresenter: MovieDetailsPresenter? = null
//    private val rate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        initUI()
        val mIntent = intent
        val movieId = mIntent.getIntExtra(KEY_MOVIE_ID, 0)
        movieDetailsPresenter = MovieDetailsPresenter(this)
        movieDetailsPresenter!!.requestMovieData(movieId)
    }

    private fun initUI() {
        ivBackdrop = findViewById(R.id.iv_backdrop)
        tvMovieTitle = findViewById(R.id.tv_movie_title)
        tvOverview = findViewById(R.id.tv_movie_overview)
        tvHomepageValue = findViewById(R.id.tv_homepage_value)
//        val tvRate = findViewById<EditText>(R.id.edtRate)
//        val btn = findViewById<Button>(R.id.btn)
    }

    override fun setDataToViews(movie: Movie) {
        tvMovieTitle!!.text = movie.title
        tvOverview!!.text = movie.overview
        Glide.with(this)
                .load(ApiClient.BACKDROP_BASE_URL + movie.backdropPath)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        return false
                    }
                })
                .apply(RequestOptions().placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder))
                .into(ivBackdrop!!)
        tvHomepageValue!!.text = movie.homepage

    }

    override fun onResponseFailure(throwable: Throwable) {
        Snackbar.make(findViewById<View>(R.id.main_content), getString(R.string.error_data), Snackbar.LENGTH_LONG).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        movieDetailsPresenter!!.onDestroy()
    }
}
