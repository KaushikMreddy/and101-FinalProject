package com.example.flixfusion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PopRecyclerViewAdapter (private val imgList: List<String>, private val textList: List<String>, private val langList: List<String>) : RecyclerView.Adapter<PopRecyclerViewAdapter.ViewHolder>(){


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pImage: ImageView
        val pTextView: TextView
        val plangTextView: TextView

        init {
            // Find our RecyclerView item's ImageView for future use
            pImage = view.findViewById(R.id.p_image)
            pTextView = view.findViewById(R.id.pTextView)
            plangTextView = view.findViewById((R.id.pKeyTextView))
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopRecyclerViewAdapter.ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pop_recycle, parent, false)

        return PopRecyclerViewAdapter.ViewHolder(view)
    }


    override fun onBindViewHolder(holder: PopRecyclerViewAdapter.ViewHolder, position: Int) {

        val context = holder.itemView.context
        val baseUrl = "https://image.tmdb.org/t/p/original"
        val imageUrl = baseUrl + imgList[position]
        val key = textList[position]
        val lan = langList[position]

        // Load image using Glide
        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .into(holder.pImage)

        // Set the key value
        holder.pTextView.text = key
        holder.plangTextView.text = lan.toString()
    }

    override fun getItemCount() = imgList.size


}