package com.example.movielist.di

import com.example.movielist.domain.repos.MoviesApi
import com.example.movielist.domain.repos.MoviesRepository
import com.example.movielist.domain.repos.WebMoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.nytimes.com/svc/movies/v2/"

@Module
class MyModule {

    @Provides
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideApi (retrofit: Retrofit) : MoviesApi =
        retrofit.create(MoviesApi::class.java)

    @Provides
    fun provideMoviesRepo(api: MoviesApi): MoviesRepository =
        WebMoviesRepositoryImpl(api)
}