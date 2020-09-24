package com.example.movielist.favorite

import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielist.core.ui.MoviesAdapter
import com.example.movielist.detail.DetailActivity
import com.example.movielist.favorite.di.favoriteModule
import kotlinx.android.synthetic.main.activity_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        loadKoinModules(favoriteModule)
        initToolbar()
        setData()
    }

    private fun setData() {
        val moviesAdapter = MoviesAdapter()
        moviesAdapter.onItemClick = { selectData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectData)
            startActivity(intent)
        }
        favoriteViewModel.favoriteMovies.observe(this, Observer { dataMovies ->
            moviesAdapter.setData(dataMovies)
            if (dataMovies.isNotEmpty()) {
                txtError.visibility = View.GONE
                lottie_layer_name.visibility = View.GONE
            } else {
                txtError.visibility = View.VISIBLE
                txtError.text = getString(R.string.emptydata)
                lottie_layer_name.visibility = View.VISIBLE
            }

        })
        with(rvList) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = moviesAdapter
        }

    }

    private fun initToolbar() {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = getString(R.string.favorite)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}