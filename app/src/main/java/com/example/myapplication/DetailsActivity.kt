package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val titleView: TextView = findViewById(R.id.titleView)
        val overviewView: TextView = findViewById(R.id.overviewView)
        val posterView: ImageView = findViewById(R.id.posterView)

        val movie = intent.getSerializableExtra("movie") as? Movie
        movie?.let {
            titleView.text = it.title
            overviewView.text = it.overview
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + it.poster_path)
                .into(posterView)
        }
    }
}
