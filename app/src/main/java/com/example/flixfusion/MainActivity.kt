package com.example.flixfusion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestHeaders
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    private val BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlNWZiZDBlYjFlNjNhNjgwYzlmZTliMmE0ZjNkMGI3OCIsInN1YiI6IjY2MWYyMmQ4MjE2MjFiMDE0YWYwMWQwNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.fvPIDllszfnxfL_pI75bCU1_stXN-I3yexaR7dKZW5E"

    private lateinit var rvMain: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rvMain = findViewById(R.id.main_recycler_view)

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