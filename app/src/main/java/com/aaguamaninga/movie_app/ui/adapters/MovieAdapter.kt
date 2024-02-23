package com.aaguamaninga.movie_app.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aaguamaninga.movie_app.R
import com.aaguamaninga.movie_app.core.Constant
import com.aaguamaninga.movie_app.data.network.entities.movie.ResultMovie
import com.aaguamaninga.movie_app.databinding.ItemsMovieBinding

class MovieAdapter ():
    ListAdapter<ResultMovie, MovieAdapter.MovieVH>(DiffUtilNobelCallback) {

    var listNobel : List<ResultMovie> = emptyList()

    class MovieVH(view: View): RecyclerView.ViewHolder(view){

        private var binding: ItemsMovieBinding = ItemsMovieBinding.bind(view)

        fun render(item: ResultMovie) {
            binding.imgPoster.load(Constant.URL_IMG+item.poster_path)
            binding.circularProgress.maxProgress = 10.0
            binding.circularProgress.setCurrentProgress(item.vote_average)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val inflater = LayoutInflater.from(parent.context)
        return MovieVH(inflater.inflate(R.layout.items_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        holder.render(getItem(position))
    }
}

object DiffUtilNobelCallback : DiffUtil.ItemCallback<ResultMovie>(){
    override fun areItemsTheSame(oldItem: ResultMovie, newItem: ResultMovie): Boolean {
        return (oldItem.release_date == newItem.release_date)
    }

    override fun areContentsTheSame(oldItem: ResultMovie, newItem: ResultMovie): Boolean {
        return (oldItem == newItem)
    }

}