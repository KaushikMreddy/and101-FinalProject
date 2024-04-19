package com.example.flixfusion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestHeaders
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.android.material.bottomnavigation.BottomNavigationView
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    private val BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlNWZiZDBlYjFlNjNhNjgwYzlmZTliMmE0ZjNkMGI3OCIsInN1YiI6IjY2MWYyMmQ4MjE2MjFiMDE0YWYwMWQwNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.fvPIDllszfnxfL_pI75bCU1_stXN-I3yexaR7dKZW5E"

    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var rvMain: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavView = findViewById(R.id.bottom_nav_view)

        rvMain = findViewById(R.id.main_recycler_view)

        // Locate the ImageView for the logo in the layout
        val logoImageView = findViewById<ImageView>(R.id.ivLogo)

        // Set a click listener on the logo ImageView
        logoImageView.setOnClickListener {
            // Scroll the RecyclerView to the top position
            rvMain.smoothScrollToPosition(0)
        }


        // Set listener for bottom navigation items
        bottomNavView.setOnItemSelectedListener   { item ->
            when (item.itemId) {
                R.id.home -> {
                    // Handle home icon click
                    Toast.makeText(this@MainActivity, "Home clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.coming_soon -> {
                    // Handle coming_soon icon click
                    Toast.makeText(this@MainActivity, "Coming Soon clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.downloads -> {
                    // Handle downloads icon click
                    Toast.makeText(this@MainActivity, "Downloads clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }


        getPopularMovies(BEARER_TOKEN)

    }

    private fun getPopularMovies(token: String){

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
                Log.d("Popular Movies", "response successful$json")
                val jsonResponse = json.jsonObject.getJSONArray("results")
                val movieList = mutableListOf<String>()
                val posterPathList = mutableListOf<String>()
                val originalLanguageList = mutableListOf<String>()
                for ( i in 0 until jsonResponse.length()) {
                    movieList.add(jsonResponse.getJSONObject(i).getString("original_title"))
                    posterPathList.add(jsonResponse.getJSONObject(i).getString("poster_path"))
                    originalLanguageList.add(jsonResponse.getJSONObject(i).getString("original_language"))
                }
                //val mainAdapter = MainRecyclerAdapter(posterPathList, movieList, originalLanguageList)
                val mainAdapter = MainRecyclerAdapter()
                rvMain.adapter = mainAdapter
                rvMain.layoutManager = LinearLayoutManager(this@MainActivity)
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