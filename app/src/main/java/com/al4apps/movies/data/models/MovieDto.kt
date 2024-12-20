package com.al4apps.movies.data.models

import com.al4apps.movies.domain.models.Movie
import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id")
    override val id: Int,
    @SerializedName("localized_name")
    override val localizedName: String,
    @SerializedName("name")
    override val name: String,
    @SerializedName("year")
    override val year: Int,
    @SerializedName("rating")
    override val rating: Float?,
    @SerializedName("image_url")
    override val imageUrl: String?,
    @SerializedName("description")
    override val description: String?,
    @SerializedName("genres")
    override val genres: List<String>
) : Movie