package com.example.moviemvp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rate {
    @SerializedName("value")
    @Expose
    var value: Double? = null
}
