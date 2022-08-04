package com.example.movielist.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movielist.domain.entities.MovieResponse
import com.example.movielist.domain.repos.MoviesRepository

class MoviesListViewModel(private val moviesRepository: MoviesRepository) {
    private val _progressLiveData = MutableLiveData<Boolean>()
    val progressLiveData = _progressLiveData as LiveData<Boolean>

    private val _moviesListLiveData = MutableLiveData<MovieResponse>()
    val moviesList = _moviesListLiveData as LiveData<MovieResponse>

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData = _errorLiveData as LiveData<Throwable>

    fun onLoadList(offset: Int) {
        _progressLiveData.postValue(true)

        moviesRepository.getMovies(
            offset = offset,
            onSuccess = { data ->
                _progressLiveData.postValue(false)
                _moviesListLiveData.postValue(data)
            },
            onError = { throwable ->
                _progressLiveData.postValue(false)
                _errorLiveData.postValue(throwable)
            })
    }
}