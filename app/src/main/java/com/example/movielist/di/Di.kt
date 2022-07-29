package com.example.movielist.di

import com.example.movielist.domain.repos.MoviesApi
import com.example.movielist.domain.repos.MoviesRepository
import com.example.movielist.domain.repos.WebMoviesRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.nytimes.com/svc/movies/v2/"

object Di {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val api: MoviesApi by lazy {
        retrofit.create(MoviesApi::class.java)
    }

    val moviesRepository: MoviesRepository by lazy {
        WebMoviesRepositoryImpl(api)
    }
}

inline fun <reified T> get(): T {
    if (T::class.java == MoviesRepository::class.java) {
        return Di.moviesRepository as T
    } else throw IllegalArgumentException("Нет такого класса в Di")
}

inline fun <reified T> inject(): Lazy<T> = lazy {
    return@lazy get<T>()
}