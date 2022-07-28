package com.example.movielist.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.movielist.MyApplication
import com.example.movielist.R
import com.example.movielist.domain.MoviesRepository
import com.example.movielist.domain.WebMoviesRepositoryImpl

class ListActivity : AppCompatActivity() {

    private val app: MyApplication by lazy { application as MyApplication }
    private lateinit var moviesRepository: MoviesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initToolbar()

        moviesRepository = WebMoviesRepositoryImpl(app.retrofit)
        moviesRepository.getMovies({
            val count = it.results.count()
            Toast.makeText(this, "Количество результатов: $count", Toast.LENGTH_SHORT).show()
        }, {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }
}