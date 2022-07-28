package com.example.movielist.domain.entities

/** Класс, объекты которого возвращает API. Содержит в себе список фильмов */
data class MovieResponse (val results: List<MovieEntity>)