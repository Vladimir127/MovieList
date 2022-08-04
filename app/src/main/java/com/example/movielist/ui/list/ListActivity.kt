package com.example.movielist.ui.list

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
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

    @Inject
    lateinit var viewModel: MoviesListViewModel

    private lateinit var scrollListener: RecyclerViewLoadMoreScroll
    private var offset = 0

    private val adapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        app.di.inject(this)

        initToolbar()

        initRecyclerView()

        initLiveData()

        viewModel.onLoadList(offset)
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
        scrollListener.setOnLoadMoreListener {
            offset += 20
            viewModel.onLoadList(offset)
        }
        recyclerView.addOnScrollListener(scrollListener)
    }

    private fun initLiveData() {
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)

        viewModel.moviesList.observe(this) {
            adapter.addData(it.results)
        }

        viewModel.progressLiveData.observe(this) { isLoading ->

            // Значение флага загрузки мы можем получить в двух случаях: когда
            // загружается первая страница данных и когда загружаются последующие.
            // В первом случае мы включаем/выключаем ProgressBar, находящийся
            // по центру экрана, а во втором - ProgressBar, находящийся внизу
            // списка. Какой сейчас случай, мы определяем по значению поля offset:
            // если оно равно 0, значит, загружается первая страница. Если оно
            // равно 20 или больше, значит, загружаются последующие.
            if (offset == 0) {
                progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            } else {
                if (isLoading) {
                    adapter.addLoadingView()
                } else {
                    adapter.removeLoadingView()
                    scrollListener.setLoaded()
                }
            }
        }

        viewModel.errorLiveData.observe(this) {
            Toast.makeText(this@ListActivity, it.message, Toast.LENGTH_SHORT).show()
        }
    }
}