package com.example.movielist.domain

import com.example.movielist.domain.entities.MovieResponse

/** Интерфейс репозитория для получения данных */
interface MoviesRepository {

    /** Возвращает список фильмов */
    fun getMovies(
        onSuccess: (MovieResponse) -> Unit,
        onError: (Throwable) -> Unit
    )
}