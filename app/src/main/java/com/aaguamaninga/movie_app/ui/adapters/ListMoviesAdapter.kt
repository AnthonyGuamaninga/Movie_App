package com.aaguamaninga.movie_app.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aaguamaninga.movie_app.R
import com.aaguamaninga.movie_app.core.Constant
import com.aaguamaninga.movie_app.data.network.entities.movie.DetailedMovie
import com.aaguamaninga.movie_app.data.network.entities.movie.ResultsMovies
import com.aaguamaninga.movie_app.databinding.ItemsMovieBinding

class MovieAdapter (
    private var onSelectItem: (ResultsMovies) -> Unit
):
    ListAdapter<ResultsMovies, MovieAdapter.MovieVH>(DiffUtilMoviesCallback) {

    var listMovies : List<ResultsMovies> = emptyList()

    class MovieVH(view: View): RecyclerView.ViewHolder(view){

        private var binding: ItemsMovieBinding = ItemsMovieBinding.bind(view)

        fun render(item: ResultsMovies, onSelectItem: (ResultsMovies) -> Unit) {
            binding.imgPoster.load(Constant.URL_IMG+item.poster_path)
            binding.circularProgress.maxProgress = 10.0
            binding.circularProgress.setCurrentProgress(item.vote_average)

            //LISTENERS
            binding.imgPoster.setOnClickListener({
                onSelectItem(item)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val inflater = LayoutInflater.from(parent.context)
        return MovieVH(inflater.inflate(R.layout.items_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        holder.render(getItem(position), onSelectItem)
    }
}

object DiffUtilMoviesCallback : DiffUtil.ItemCallback<ResultsMovies>(){
    override fun areItemsTheSame(oldItem: ResultsMovies, newItem: ResultsMovies): Boolean {
        return (oldItem.release_date == newItem.release_date)
    }

    override fun areContentsTheSame(oldItem: ResultsMovies, newItem: ResultsMovies): Boolean {
        return (oldItem == newItem)
    }

}