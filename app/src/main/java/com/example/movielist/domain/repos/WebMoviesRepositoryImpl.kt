package com.example.movielist.domain.repos

import com.example.movielist.BuildConfig
import com.example.movielist.domain.entities.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** Репозиторий, получающий данные из API с помощью Retrofit */
class WebMoviesRepositoryImpl(private val api: MoviesApi) : MoviesRepository {

    override fun getMovies(
        onSuccess: (MovieResponse) -> Unit,
        onError: (Throwable) -> Unit,
        offset: Int
    ) {
        api.getMovies(BuildConfig.MOVIES_API_KEY, offset).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let(onSuccess)
                } else {
                    onError(Throwable("Api error ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                onError(t)
            }
        })
    }
}