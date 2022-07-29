package com.example.movielist.domain.repos

import com.example.movielist.domain.entities.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/** Интерфейс нашего API с фильмами */
interface MoviesApi {
    @GET("reviews/all.json")
    fun getMovies(
        @Query("api-key") apiKey: String,
        @Query("offset") offset: Int = 0
    ): Call<MovieResponse>
}