package com.example.movielist.detail

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.example.movielist.R
import com.example.movielist.core.domain.model.Movies
import com.example.movielist.core.utils.isRTL
import com.example.movielist.core.utils.loadImage
import com.example.movielist.core.utils.loadImageFull
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

private val detailViewModel:DetailViewModel by viewModel()
    companion object{
        const val EXTRA_DATA = "extra_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val detailMovies = intent.getParcelableExtra<Movies>(EXTRA_DATA)
        showDetailMovies(detailMovies)
    }

    @SuppressLint("SetTextI18n")
    private fun showDetailMovies(movies: Movies?)
    {
        movies?.let {
            initToolbar(movies.title)
            txtDate.text = "Release date : ${movies.release_date}"
            loadImageFull(imageView,movies.poster_path)
            txtOverview.text = movies.overview

            var statusFavorite = movies.isFavorite
            //Timber.d(" isFavorite $statusFavorite")
            setStatusFavorite(statusFavorite)
            fabFavorite.setOnClickListener {
                statusFavorite = !statusFavorite

                detailViewModel.setFavoriteMovies(movies,statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
    private fun initToolbar(message:String) {

        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_black_24)

        if (toolbar.navigationIcon != null) {
            toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP)
        }

        toolbar.title = message

        try {
            toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.md_white_1000))
        } catch (e: Exception) {
            Log.e("TEAMPS", "Can't set color.")
        }

        try {
            setSupportActionBar(toolbar)
        } catch (e: Exception) {
            Log.e("TEAMPS", "Error in set support action bar.")
        }

        try {
            if (supportActionBar != null) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        } catch (e: Exception) {
            Log.e("TEAMPS", "Error in set display home as up enabled.")
        }


        if (isRTL) {
            collapsingToolbar.collapsedTitleGravity = Gravity.END
            collapsingToolbar.expandedTitleGravity = Gravity.END or Gravity.BOTTOM
        } else {
            collapsingToolbar.collapsedTitleGravity = Gravity.START
            collapsingToolbar.expandedTitleGravity = Gravity.START or Gravity.BOTTOM
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}