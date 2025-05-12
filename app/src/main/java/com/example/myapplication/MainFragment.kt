package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : BrowseSupportFragment() {
    private val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
    private val cardPresenter = CardPresenter()
    private val apiKey = "e6d4901464f087afaf9ebf83a108a222" // Replace with your actual TMDb API Key

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        title = "Movie TV App"
        loadMovies()
        adapter = rowsAdapter
    }

    private fun loadMovies() {
        ApiClient.movieApi.getPopularMovies(apiKey).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                Log.d("MainFragment", "Response successful: ${response.isSuccessful}")
                Log.d("MainFragment", "Response code: ${response.code()}")
                Log.d("MainFragment", "Response body: ${response.body()}")

                response.body()?.results?.let { movies ->
                    Log.d("MainFragment", "Movies size: ${movies.size}")
                    val listRowAdapter = ArrayObjectAdapter(cardPresenter)
                    movies.forEach { listRowAdapter.add(it) }
                    val header = HeaderItem(0, "Popular Movies")
                    rowsAdapter.add(ListRow(header, listRowAdapter))
                }
                    ?: run {
                        Log.w("MainFragment", "Movies list is null")
                    }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // Handle error
                Log.e("MainFragment", "API call failed: ${t.message}")
            }
        })
    }
}