package com.example.games.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games.data.RetrofitServiceFactory
import com.example.games.model.RemoteResult
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MoviesViewModel : ViewModel() {

    private val _movies = MutableStateFlow<RemoteResult?>(null)
    val movies: StateFlow<RemoteResult?> get() = _movies

    private val service = RetrofitServiceFactory.makeRetrofitService()

    fun fetchNowPlayingMovies() {
        viewModelScope.launch {
            try {
                val result = service.listPopularMovies(
                    "es-MX",
                    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzYTJiNDMxZTljNGVjMDBlZGY3NmY2MzljMmZiOGI5YiIsIm5iZiI6MTcyNDE3MjIyMS44OTM5MTEsInN1YiI6IjYzOTI4MThiZjA0ZDAxMDA4YzQxY2Y5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.55PtkJ13e9wZFcSHe3aUVIhaJLmHtz_LOTxQnfEa7DM",
                    "application/json"
                )
                _movies.value = result
            } catch (e: Exception) {
                Log.d("ErrorFetchMovies", "Error en la petición")
                _movies.value = null
            }
        }
    }
}
