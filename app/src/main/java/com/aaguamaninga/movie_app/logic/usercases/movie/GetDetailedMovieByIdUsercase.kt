package com.aaguamaninga.movie_app.logic.usercases.movie

import android.util.Log
import com.aaguamaninga.movie_app.core.Constant
import com.aaguamaninga.movie_app.data.network.endpoints.movie.DetailedMovieEndpoint
import com.aaguamaninga.movie_app.data.network.endpoints.movie.NowPlayingEndpoint
import com.aaguamaninga.movie_app.data.network.entities.movie.DetailedMovie
import com.aaguamaninga.movie_app.data.network.entities.movie.ResultsMovies
import com.aaguamaninga.movie_app.data.network.repository.RetrofitBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetDetailedMovieByIdUsercase {

    suspend fun invoke(movie_id:Int): Flow<Result<DetailedMovie>> = flow {
        var result: Result<DetailedMovie>? = null

        val baseService = RetrofitBase.getRetrofitTmdbConnection()
        val service = baseService.create(DetailedMovieEndpoint::class.java)
        val call = service.getAllNowPlaying(movie_id,Constant.API_KEY)
        try {
            if (call.isSuccessful) {
                val movie = call.body()!!
                result = Result.success(movie)
            } else {
                val msg = "Error en el llamado a la API de Jikan"
                result = Result.failure(Exception(msg))
                Log.d("TAG", msg)
            }
        } catch (ex: Exception) {
            Log.e("TAG", ex.stackTraceToString())
            result = Result.failure(ex)
        }
        emit(result!!)
    }

}