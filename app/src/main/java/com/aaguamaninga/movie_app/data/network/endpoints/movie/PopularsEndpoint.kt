package com.aaguamaninga.movie_app.data.network.endpoints.movie

import com.aaguamaninga.movie_app.data.network.entities.movie.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularsEndpoint {
    @GET("movie/popular")
    suspend fun getAllPopulars(@Query("api_key") apiKey: String): Response<Movie>
}