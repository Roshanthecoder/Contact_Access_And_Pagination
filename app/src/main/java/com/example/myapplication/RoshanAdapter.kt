package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.MovieLayoutBinding
import com.squareup.picasso.Picasso

class RoshanAdapter : RecyclerView.Adapter<RoshanAdapter.ViewHolder>() {
    private var movieslist = ArrayList<Result>()
    fun setmovielis(movieslist:List<Result>){
        this.movieslist=movieslist as ArrayList<Result>
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoshanAdapter.ViewHolder {
        return ViewHolder(MovieLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RoshanAdapter.ViewHolder, position: Int) {
        holder.binding.movieName.text = movieslist[position].title
        /* Glide.with(holder.itemView)
             .load("https://image.tmdb.org/t/p/w500"+movieslist[position].poster_path)
             .into(holder.binding.movieImage)*/
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movieslist[position].poster_path)
            .into(holder.binding.movieImage)

    }

    override fun getItemCount(): Int {
        return movieslist.size
    }

    class ViewHolder(val binding: MovieLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}