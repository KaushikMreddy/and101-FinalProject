package com.example.flixfusion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ActorRecycleAdapter(private val imgList: List<String>) : RecyclerView.Adapter<ActorRecycleAdapter.ViewHolder>()  {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val trImage: ImageView

        init {
            // Find our RecyclerView item's ImageView for future use
            trImage = view.findViewById(R.id.tr_image)

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorRecycleAdapter.ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.actor_recycle, parent, false)

        return ActorRecycleAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorRecycleAdapter.ViewHolder, position: Int) {

        val context = holder.itemView.context
        val baseUrl = "https://image.tmdb.org/t/p/original"
        val imageUrl = baseUrl + imgList[position]


        // Load image using Glide
        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .into(holder.trImage)


    }

    override fun getItemCount() = imgList.size
}