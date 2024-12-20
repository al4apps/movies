package com.al4apps.movies.domain.models

interface Movie {
    val id: Int
    val localizedName: String
    val name: String
    val year: Int
    val rating: Float?
    val imageUrl: String?
    val description: String?
    val genres: List<String>
}

data class MovieModel(
    override val id: Int,
    override val localizedName: String,
    override val name: String,
    override val year: Int,
    override val rating: Float?,
    override val imageUrl: String?,
    override val description: String?,
    override val genres: List<String>
) : Movie

data class MovieItem(
    val id: Int,
    val localizedName: String,
    val imageUrl: String?,
    val genres: List<String>
)