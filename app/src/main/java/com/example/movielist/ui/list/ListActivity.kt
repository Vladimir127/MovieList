package com.example.movielist.ui.list

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.R
import com.example.movielist.app
import com.example.movielist.domain.repos.MoviesRepository
import javax.inject.Inject

class ListActivity : AppCompatActivity() {

    @Inject
    lateinit var moviesRepository: MoviesRepository

    private lateinit var scrollListener: RecyclerViewLoadMoreScroll
    private var offset = 0

    private val adapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        app.di.inject(this)

        initToolbar()

        initRecyclerView()

        moviesRepository.getMovies(
            onSuccess = {
                adapter.setData(it.results)
            },
            onError = {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            })
    }

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = adapter

        // Настройка пагинации
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        scrollListener = RecyclerViewLoadMoreScroll(layoutManager)
        scrollListener.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore() {
                loadMoreData()
            }
        })
        recyclerView.addOnScrollListener(scrollListener)
    }

    private fun loadMoreData() {
        adapter.addLoadingView()

        offset += 20
        moviesRepository.getMovies(offset = offset,
            onSuccess = {
                adapter.removeLoadingView()
                adapter.addData(it.results)
                scrollListener.setLoaded()
            },
            onError = {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            })
    }
}