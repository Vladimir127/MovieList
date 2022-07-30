package com.example.movielist.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielist.R
import com.example.movielist.domain.entities.MovieEntity
import com.example.movielist.ui.Constant

class MovieAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var movies: ArrayList<MovieEntity?> = ArrayList()

    fun addData(data: List<MovieEntity>) {
        this.movies.addAll(data)
        notifyItemRangeInserted(movies.size - 1, data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        // Теперь у нас могут быть пункты списка двух типов: непосредственно
        // объекты и колёсико ProgressBar, появляющееся на время загрузки,
        // когда мы пролистываем список до конца. Последний не содержит
        // никаких данных, поэтому таким хитрым способом мы и определяем, что
        // это пункт именно такого типа.
        return if (viewType == Constant.VIEW_TYPE_ITEM) {
            MovieViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_movie, parent, false)
                        as View
            )
        } else {
            LoadingViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.progress_loading, parent, false)
                        as View
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Привязку выполняем только в том случае, если это обычный пункт
        // списка, так как в пункте с колёсиком привязывать нечего
        if (holder.itemViewType == Constant.VIEW_TYPE_ITEM) {
            movies[position]?.let { (holder as MovieViewHolder).bind(it) }
        }
    }

    override fun getItemCount(): Int {
        return movies.count()
    }

    override fun getItemViewType(position: Int): Int {
        return if (movies[position] == null) {
            Constant.VIEW_TYPE_LOADING
        } else {
            Constant.VIEW_TYPE_ITEM
        }
    }

    fun addLoadingView() {
        movies.add(null)
        notifyItemInserted(movies.size - 1)
    }

    fun removeLoadingView() {
        if (movies.size != 0) {
            movies.removeAt(movies.size - 1)
            notifyItemRemoved(movies.size)
        }
    }

    /**
     * ViewHolder для пункта списка, содержащего информацию о фильме
     */
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameTextView = itemView.findViewById<TextView>(R.id.name_text_view)
        private val descriptionTextView = itemView.findViewById<TextView>(R.id.description_text_view)
        private val imageView = itemView.findViewById<ImageView>(R.id.image_view)

        fun bind(movieEntity: MovieEntity) {
            nameTextView.text = movieEntity.name
            descriptionTextView.text = movieEntity.description

            Glide.with(itemView)
                .load(movieEntity.multimedia.src)
                .placeholder(R.drawable.movie_placeholder)
                .into(imageView)
        }
    }

    /**
     * ViewHolder для специального пункта списка, содержащего колёсико
     * ProgressBar, которое будет отображаться внизу списка при загрузке данных
     */
    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}