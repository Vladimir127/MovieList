package com.example.movielist.domain.entities

import com.google.gson.annotations.SerializedName

/** Класс фильма, отображаемого в списке */
data class MovieEntity(
    @field:SerializedName("display_title") val name: String,
    @field:SerializedName("summary_short") val description: String,
    val multimedia: MovieMultimedia
)