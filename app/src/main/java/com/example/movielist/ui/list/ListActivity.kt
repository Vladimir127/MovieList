package com.example.movielist.ui.list

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.MyApplication
import com.example.movielist.R
import com.example.movielist.domain.repos.MoviesRepository
import com.example.movielist.domain.repos.WebMoviesRepositoryImpl

class ListActivity : AppCompatActivity() {

    private val app: MyApplication by lazy { application as MyApplication }
    private lateinit var moviesRepository: MoviesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initToolbar()

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        moviesRepository = WebMoviesRepositoryImpl(app.retrofit)
        moviesRepository.getMovies({
            recyclerView.adapter = MovieAdapter(it.results)
        }, {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }
}