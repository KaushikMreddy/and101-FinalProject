package com.example.flixfusion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainRecyclerAdapter (private val imgList: List<String>, private val textList: List<String>, private val langList: List<String>) : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mainImage: ImageView
        val mainTextView: TextView
        val langTextView: TextView

        init {
            // Find our RecyclerView item's ImageView for future use
            mainImage = view.findViewById(R.id.main_image)
            mainTextView = view.findViewById(R.id.mainTextView)
            langTextView = view.findViewById((R.id.langKeyTextView))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_recycle, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val context = holder.itemView.context
        val baseUrl = "https://image.tmdb.org/t/p/original"
        val imageUrl = baseUrl + imgList[position]
        val key = textList[position]
        val lan = langList[position]

        // Load image using Glide
        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .into(holder.mainImage)

        // Set the key value
        holder.mainTextView.text = key
        holder.langTextView.text = lan.toString()
    }

    override fun getItemCount() = imgList.size
}