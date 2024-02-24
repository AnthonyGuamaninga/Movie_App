package com.aaguamaninga.movie_app.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaguamaninga.movie_app.data.network.entities.movie.ResultsMovies
import com.aaguamaninga.movie_app.logic.usercases.movie.GetAllPopularsUsercase
import com.aaguamaninga.movie_app.logic.usercases.movie.GetAllTopRatedUsercase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListTopRatedViewModel : ViewModel() {

    val listItems = MutableLiveData<List<ResultsMovies>>()
    val error = MutableLiveData<String>()

    fun getAllTopRated() {
        viewModelScope.launch(Dispatchers.IO) {
            val userCase = GetAllTopRatedUsercase()
            val moviesFlows = userCase.invoke()

            moviesFlows.collect{ movie ->
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