package com.example.flixfusion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class TopRatedRecyclerAdapter(private val imgList: List<String>, private val textList: List<String>, private val langList: List<String>) : RecyclerView.Adapter<TopRatedRecyclerAdapter.ViewHolder>()  {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val trImage: ImageView
        val trTextView: TextView
        val trlangTextView: TextView

        init {
            // Find our RecyclerView item's ImageView for future use
            trImage = view.findViewById(R.id.tr_image)
            trTextView = view.findViewById(R.id.trTextView)
            trlangTextView = view.findViewById((R.id.trKeyTextView))
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedRecyclerAdapter.ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.toprated_recycle, parent, false)

        return TopRatedRecyclerAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopRatedRecyclerAdapter.ViewHolder, position: Int) {

        val context = holder.itemView.context
        val baseUrl = "https://image.tmdb.org/t/p/original"
        val imageUrl = baseUrl + imgList[position]
        val key = textList[position]
        val lan = langList[position]

        // Load image using Glide
        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .into(holder.trImage)

        // Set the key value
        holder.trTextView.text = key
        holder.trlangTextView.text = lan.toString()
    }

    override fun getItemCount() = imgList.size
}