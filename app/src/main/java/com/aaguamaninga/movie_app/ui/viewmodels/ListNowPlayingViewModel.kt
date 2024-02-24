package com.aaguamaninga.movie_app.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaguamaninga.movie_app.data.network.entities.movie.ResultsMovies
import com.aaguamaninga.movie_app.logic.usercases.movie.GetAllNowPlayingUsercase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListNowPlayingViewModel : ViewModel() {

    val listItems = MutableLiveData<List<ResultsMovies>>()
    val error = MutableLiveData<String>()

    fun getAllNowPlaying() {
        viewModelScope.launch(Dispatchers.IO) {
            val userCase = GetAllNowPlayingUsercase()
            val movieFlow = userCase.invoke()

            movieFlow.collect{ movie ->
                movie.onSuccess {
                    listItems.postValue(it.toList())
                }
                movie.onFailure {
                    error.postValue(it.message.toString())
                }
            }

        }
    }
}