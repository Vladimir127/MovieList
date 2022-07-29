package com.example.movielist.ui.list

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Обработчик прокрутки RecyclerView, отвечающий за подгрузку записей по
 * мере прокрутки
 */
class RecyclerViewLoadMoreScroll(private var layoutManager: LinearLayoutManager)
    : RecyclerView.OnScrollListener() {

    /**
     * Количество последних объектов в списке, при достижении которых
     * начнётся загрузка следующих
     */
    private var visibleThreshold = 5

    /**
     * Интерфейс с методом обратного вызова для подгрузки объектов
     */
    private lateinit var mOnLoadMoreListener: OnLoadMoreListener
    private var isLoading: Boolean = false
    private var lastVisibleItem: Int = 0
    private var totalItemCount: Int = 0

    fun setLoaded() {
        isLoading = false
    }

    fun getLoaded(): Boolean {
        return isLoading
    }

    fun setOnLoadMoreListener(mOnLoadMoreListener: OnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener
    }

    /**
     * Основной метод, который как раз и реагирует на прокрутку списка
     */
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy <= 0) return

        // Получаем общее количество элементов в списке
        totalItemCount = layoutManager.itemCount

        // Получаем последний видимый элемент в списке
        lastVisibleItem = layoutManager.findLastVisibleItemPosition()

        // Проверяем два условия:
        // 1. Проверяем, что не идёт загрузка, потому что если она уже идёт,
        // нет смысла запускать её повторно
        // 2. Берём последний видимый элемент, (например, 17), прибавляем к
        // нему порог (например, 5) и сравниваем с общим количеством
        // элементов (например, 20). Если больше или равно, как в нашем
        // случае, запускаем загрузку
        if (!isLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
            mOnLoadMoreListener.onLoadMore()
            isLoading = true
        }
    }
}