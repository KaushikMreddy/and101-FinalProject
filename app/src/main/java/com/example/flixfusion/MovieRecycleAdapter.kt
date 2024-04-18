package com.example.flixfusion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieRecycleAdapter(private val imgList: List<String>)  : RecyclerView.Adapter<MovieRecycleAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mvImage: ImageView


        init {
            // Find our RecyclerView item's ImageView for future use
            mvImage = view.findViewById(R.id.mv_image)

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRecycleAdapter.ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_recycle, parent, false)

        return MovieRecycleAdapter.ViewHolder(view)
    }


    override fun onBindViewHolder(holder: MovieRecycleAdapter.ViewHolder, position: Int) {

        val context = holder.itemView.context
        val baseUrl = "https://image.tmdb.org/t/p/original"
        val imageUrl = baseUrl + imgList[position]

        // Load image using Glide
        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .into(holder.mvImage)

    }

    override fun getItemCount() = imgList.size

}