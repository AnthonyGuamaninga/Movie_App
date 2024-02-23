package com.aaguamaninga.movie_app.data.network.endpoints.movie

import com.aaguamaninga.movie_app.data.network.entities.movie.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NowPlayingEndpoint {

    @GET("movie/now_playing")
    suspend fun getAllNowPlaying(@Query("api_key") apiKey: String): Response<Movie>
}