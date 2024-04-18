package com.example.flixfusion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class TvRecycleAdapter(private val imgList: List<String>)  : RecyclerView.Adapter<TvRecycleAdapter.ViewHolder>() {



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvImage: ImageView


        init {
            // Find our RecyclerView item's ImageView for future use
            tvImage = view.findViewById(R.id.tv_image)

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvRecycleAdapter.ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tv_recycle, parent, false)

        return TvRecycleAdapter.ViewHolder(view)
    }


    override fun onBindViewHolder(holder: TvRecycleAdapter.ViewHolder, position: Int) {

        val context = holder.itemView.context
        val baseUrl = "https://image.tmdb.org/t/p/original"
        val imageUrl = baseUrl + imgList[position]

        // Load image using Glide
        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .into(holder.tvImage)

    }

    override fun getItemCount() = imgList.size

}