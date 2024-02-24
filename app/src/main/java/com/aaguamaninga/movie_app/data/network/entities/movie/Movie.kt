package com.aaguamaninga.movie_app.data.network.entities.movie

data class Movie(
    val dates: Dates,
    val page: Int,
    val results: List<ResultsMovies>,
    val total_pages: Int,
    val total_results: Int
)