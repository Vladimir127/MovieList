package com.example.movielist.ui.list


interface OnLoadMoreListener {

    /** Обработчик прокрутки будет вызывать интерфейс с этим методом,
     * а обрабатывать его будет ListActivity, в которой располагается
     * RecyclerView */
    fun onLoadMore()
}