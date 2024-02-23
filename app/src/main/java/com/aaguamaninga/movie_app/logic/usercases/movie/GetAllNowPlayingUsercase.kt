package com.aaguamaninga.movie_app.logic.usercases.movie

import android.util.Log
import com.aaguamaninga.movie_app.core.Constant
import com.aaguamaninga.movie_app.data.network.endpoints.movie.NowPlayingEndpoint
import com.aaguamaninga.movie_app.data.network.entities.movie.Movie
import com.aaguamaninga.movie_app.data.network.entities.movie.ResultMovie
import com.aaguamaninga.movie_app.data.network.repository.RetrofitBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllNowPlayingUsercase {

    suspend fun invoke(): Flow<Result<List<ResultMovie>>> = flow {
        var result: Result<List<ResultMovie>>? = null

        val baseService = RetrofitBase.getRetrofitTmdbConnection()
        val service = baseService.create(NowPlayingEndpoint::class.java)
        val call = service.getAllNowPlaying(Constant.API_KEY)
        try {
            if (call.isSuccessful) {
                val a = call.body()!!
                val movie = a.results
                Log.d("TAG", movie.toString())
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

