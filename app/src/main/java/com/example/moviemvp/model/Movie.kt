package com.example.moviemvp.model

import com.google.gson.annotations.SerializedName

data class Movie(val id: Int, val title: String, @field:SerializedName("release_date")
                 val releaseDate: String, @field:SerializedName("vote_average")
                 val rating: Float, @field:SerializedName("poster_path")
                 val thumbPath: String, @field:SerializedName("overview")
                 val overview: String, @field:SerializedName("backdrop_path")
                 val backdropPath: String, @field:SerializedName("runtime")
                 val runTime: String, @field:SerializedName("tagline")
                 private val tagline: String, @field:SerializedName("homepage")
                 val homepage: String) {

    override fun toString(): String {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\''.toString() +
                ", releaseDate='" + releaseDate + '\''.toString() +
                ", rating=" + rating +
                ", thumbPath='" + thumbPath + '\''.toString() +
                ", overview='" + overview + '\''.toString() +
                ", backdropPath='" + backdropPath + '\''.toString() +
                ", runTime='" + runTime + '\''.toString() +
                ", tagline='" + tagline + '\''.toString() +
                ", homepage='" + homepage + '\''.toString() +
                '}'.toString()
    }
}