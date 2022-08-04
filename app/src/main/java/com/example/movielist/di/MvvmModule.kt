package com.example.movielist.di

import com.example.movielist.domain.repos.MoviesRepository
import com.example.movielist.ui.list.MoviesListViewModel
import dagger.Module
import dagger.Provides

@Module
class MvvmModule {
    @Provides
    fun provideMoviesListViewModel (moviesRepo: MoviesRepository) : MoviesListViewModel =
        MoviesListViewModel(moviesRepo)
}