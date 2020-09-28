package com.example.movielist.home

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielist.R
import com.example.movielist.core.data.Resource
import com.example.movielist.core.ui.MoviesAdapter
import com.example.movielist.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private val moviesAdapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            moviesAdapter.onItemClick = { selectData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectData)
                startActivity(intent)
            }

            setData()
        }
    }

    private fun setData() {
        homeViewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> {
                        linearShimmer.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        txtError.visibility = View.GONE
                        linearShimmer.visibility = View.GONE
                        moviesAdapter.setData(movies.data)

                    }
                    is Resource.Error -> {
                        linearShimmer.visibility = View.GONE
                        txtError.visibility = View.VISIBLE
                        txtError.text = movies.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })

        with(rvList) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = moviesAdapter
        }
    }
}