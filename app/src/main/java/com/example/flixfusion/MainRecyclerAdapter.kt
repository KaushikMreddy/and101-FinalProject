package com.example.flixfusion

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestHeaders
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainRecyclerAdapter () : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {
    //private val imgList: List<String>, private val textList: List<String>, private val langList: List<String>
    private val BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlNWZiZDBlYjFlNjNhNjgwYzlmZTliMmE0ZjNkMGI3OCIsInN1YiI6IjY2MWYyMmQ4MjE2MjFiMDE0YWYwMWQwNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.fvPIDllszfnxfL_pI75bCU1_stXN-I3yexaR7dKZW5E"
//    private lateinit var rvTopRated: RecyclerView
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mainImage: ImageView
        val mainTextView: TextView
        val langTextView: TextView
        val rvTopRated: RecyclerView
        val rvPop: RecyclerView

        init {
            // Find our RecyclerView item's ImageView for future use
            mainImage = view.findViewById(R.id.main_image)
            mainTextView = view.findViewById(R.id.mainTextView)
            langTextView = view.findViewById((R.id.langKeyTextView))
            rvTopRated = view.findViewById(R.id.tr_recycler_view)
            rvPop = view.findViewById(R.id.pr_recycler_view)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_recycle, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        val context = holder.itemView.context
//        val baseUrl = "https://image.tmdb.org/t/p/original"
//        val imageUrl = baseUrl + imgList[position]
//        val key = textList[position]
//        val lan = langList[position]
//
//        // Load image using Glide
//        Glide.with(context)
//            .load(imageUrl)
//            .centerCrop()
//            .into(holder.mainImage)
//
//        // Set the key value
        holder.mainTextView.text = "Movie List"
//        holder.langTextView.text = lan.toString()

        getPopularMovies1(BEARER_TOKEN, holder.rvTopRated)
        getPopularMovies2(BEARER_TOKEN, holder.rvPop)


    }

    //override fun getItemCount() = imgList.size

    override fun getItemCount(): Int {
        // Return a fixed count or 0 depending on your requirements
        return 1 // or any fixed count like 10
    }

    private fun getPopularMovies1(token: String, rvTopRated: RecyclerView){

        val client = AsyncHttpClient()
        val url = "https://api.themoviedb.org/3/discover/movie" //This is the base URL (not secure yet)
        val params = RequestParams().apply {// This is needed to add the parameters in our API URL
            put("include_adult", "false")
            put("include_video", "false")
            put("language", "en-US")
            put("page", "1")
            put("sort_by", "popularity.desc")
        }
        val headers = RequestHeaders().apply {// This is needed to add the headers for the API call
            put("Authorization", "Bearer $token")
            put("accept", "application/json")
        }
        client.get(url,  headers, params, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Popular Movies Again", "response successful$json")
                val jsonResponse = json.jsonObject.getJSONArray("results")
                val movieList = mutableListOf<String>()
                val posterPathList = mutableListOf<String>()
                val originalLanguageList = mutableListOf<String>()
                for ( i in 0 until jsonResponse.length()) {
                    movieList.add(jsonResponse.getJSONObject(i).getString("original_title"))
                    posterPathList.add(jsonResponse.getJSONObject(i).getString("poster_path"))
                    originalLanguageList.add(jsonResponse.getJSONObject(i).getString("original_language"))
                }
                val trAdapter = TopRatedRecyclerAdapter(posterPathList, movieList, originalLanguageList)
                rvTopRated.adapter = trAdapter
                rvTopRated.layoutManager = LinearLayoutManager(rvTopRated.context, LinearLayoutManager.HORIZONTAL, false)
            }
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Movies Error", errorResponse)
            }
        })
    }

    private fun getPopularMovies2(token: String, rvTopRated: RecyclerView){

        val client = AsyncHttpClient()
        val url = "https://api.themoviedb.org/3/discover/movie" //This is the base URL (not secure yet)
        val params = RequestParams().apply {// This is needed to add the parameters in our API URL
            put("include_adult", "false")
            put("include_video", "false")
            put("language", "en-US")
            put("page", "1")
            put("sort_by", "popularity.desc")
        }
        val headers = RequestHeaders().apply {// This is needed to add the headers for the API call
            put("Authorization", "Bearer $token")
            put("accept", "application/json")
        }
        client.get(url,  headers, params, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Popular Movies Again", "response successful$json")
                val jsonResponse = json.jsonObject.getJSONArray("results")
                val movieList = mutableListOf<String>()
                val posterPathList = mutableListOf<String>()
                val originalLanguageList = mutableListOf<String>()
                for ( i in 0 until jsonResponse.length()) {
                    movieList.add(jsonResponse.getJSONObject(i).getString("original_title"))
                    posterPathList.add(jsonResponse.getJSONObject(i).getString("poster_path"))
                    originalLanguageList.add(jsonResponse.getJSONObject(i).getString("original_language"))
                }
                val pAdapter = PopRecyclerViewAdapter(posterPathList, movieList, originalLanguageList)
                rvTopRated.adapter = pAdapter
                rvTopRated.layoutManager = LinearLayoutManager(rvTopRated.context, LinearLayoutManager.HORIZONTAL, false)
            }
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Movies Error", errorResponse)
            }
        })
    }




}