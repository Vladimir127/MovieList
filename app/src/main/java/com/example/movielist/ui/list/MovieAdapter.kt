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

class MovieAdapter(private val movies: List<MovieEntity>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {

        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.count()
    }

    inner class MovieViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val nameTextView = itemView.findViewById<TextView>(R.id.name_text_view)
        private val descriptionTextView =
            itemView.findViewById<TextView>(R.id.description_text_view)
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
}