package com.example.movielist

import android.app.Application
import android.content.Context
import com.example.movielist.domain.repos.MoviesApi
import com.example.movielist.domain.repos.MoviesRepository
import com.example.movielist.domain.repos.WebMoviesRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.nytimes.com/svc/movies/v2/"

class MyApplication : Application() {
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

// Чтобы к app можно было обращаться из Activity без инициализации
val Context.app: MyApplication
    get() = applicationContext as MyApplication