package com.jauhar.challenge7.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jauhar.challenge7.R
import com.jauhar.challenge7.databinding.MovieItemBinding
import com.jauhar.challenge7.models.Data

class HomeAdapter(private val movies: List<Data>) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    class HomeViewHolder(private var binding: MovieItemBinding, val movies: List<Data>) :
        RecyclerView.ViewHolder(binding.root) {
        private val imageBase = "https://image.tmdb.org/t/p/w500/"
        @SuppressLint("SetTextI18n")
        fun bindMovie(movie: Data){
            binding.tvMovieTitle.text = movie.title
            binding.tvReleaseDate.text = "Released on : " + movie.releaseDate
            binding.tvRating.text = movie.rating.toString()
            Glide.with(itemView.context).load(imageBase + movie.posterPath).into(binding.ivMoviePoster)
            binding.rvMovie.setOnClickListener{
                val movieData = Bundle()
                movie.id?.let { it1 -> movieData.putInt("ID", it1) }
                it.findNavController().navigate(R.id.action_homeFragment_to_detailFragment, movieData)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding, movies)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindMovie(movies[position])
    }
}