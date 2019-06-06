package com.example.moviemvp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviemvp.R
import com.example.moviemvp.model.Movie
import com.example.moviemvp.network.ApiClient
import java.util.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {

    private val movieList = ArrayList<Movie>()
    private var listener: OnListClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bindData(movie, position)
    }

    fun setMovies(movies: List<Movie>) {
        movieList.clear()
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

//    fun appendMovies(movies: List<Movie>) {
//        val startPos = movieList.size
//        movieList.addAll(movies)
//        notifyItemRangeInserted(startPos, movies.size)
//    }
//
//    fun clearMovies() {
//        movieList.clear()
//        notifyDataSetChanged()
//    }

    fun setOnListClickedListener(listener: OnListClickListener) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvMovieTitle: TextView = itemView.findViewById(R.id.tv_movie_title)
        private val tvMovieRatings: TextView = itemView.findViewById(R.id.tv_movie_ratings)
        private val tvReleaseDate: TextView = itemView.findViewById(R.id.tv_release_date)
        private val ivMovieThumb: ImageView = itemView.findViewById(R.id.iv_movie_thumb)

        fun bindData(movie: Movie, position: Int) {
            tvMovieTitle.text = movie.title
            tvMovieRatings.text = movie.rating.toString()
            tvReleaseDate.text = movie.releaseDate

            Glide.with(itemView.context)
                    .load(ApiClient.IMAGE_BASE_URL + movie.thumbPath)
                    .apply(RequestOptions().placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder))
                    .into(ivMovieThumb)

            itemView.setOnClickListener {
                if (listener != null) {
                    listener!!.onMovieClicked(movie, position)
                }
            }
        }
    }

    interface OnListClickListener {

        fun onMovieClicked(movie: Movie, position: Int)
    }
}
