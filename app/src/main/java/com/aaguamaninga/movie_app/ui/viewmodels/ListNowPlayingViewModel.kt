package com.aaguamaninga.movie_app.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaguamaninga.movie_app.data.network.entities.movie.ResultMovie
import com.aaguamaninga.movie_app.logic.usercases.movie.GetAllNowPlayingUsercase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListNowPlayingViewModel : ViewModel() {

    val listItems = MutableLiveData<List<ResultMovie>>()
    val error = MutableLiveData<String>()

    fun getAllNobelPrizes() {
        viewModelScope.launch(Dispatchers.IO) {
            val userCase = GetAllNowPlayingUsercase()
            val nobelFlow = userCase.invoke()

            nobelFlow.collect{movie ->
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