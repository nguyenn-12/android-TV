package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.ViewGroup
import android.widget.ImageView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import androidx.appcompat.app.AlertDialog

class CardPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val imageView = ImageView(parent.context)
        imageView.layoutParams = ViewGroup.LayoutParams(300, 450)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return ViewHolder(imageView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val movie = item as Movie
        val imageView = viewHolder.view as ImageView
        Glide.with(imageView.context)
            .load("https://image.tmdb.org/t/p/w500" + movie.poster_path)
            .into(imageView)

        imageView.setOnClickListener {
            // Show dialog with title and overview
            //showMovieDialog(imageView.context, movie)
            val intent = Intent(imageView.context, DetailsActivity::class.java)
            intent.putExtra("movie", movie)
            imageView.context.startActivity(intent)
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {}
}


//fun showMovieDialog(context: Context, movie: Movie) {
//    AlertDialog.Builder(context)
//        .setTitle(movie.title)
//        .setMessage(movie.overview)
//        .setPositiveButton("OK", null)
//        .show()
//}
