package com.al4apps.movies.data.models

import com.google.gson.annotations.SerializedName

data class MoviesResponseModel(
    @SerializedName("films")
    val films: List<MovieDto>
)