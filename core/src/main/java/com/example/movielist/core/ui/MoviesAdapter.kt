package com.example.movielist.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.core.R
import com.example.movielist.core.domain.model.Movies
import com.example.movielist.core.utils.loadImage
import kotlinx.android.synthetic.main.item_list_movies.view.*
import timber.log.Timber
import java.util.ArrayList

class MoviesAdapter  : RecyclerView.Adapter<MoviesAdapter.ListViewHolder>(){
    private var listData = ArrayList<Movies>()
    var onItemClick: ((Movies) -> Unit)? = null

    fun setData(newListData: List<Movies>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_movies, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Movies) {
            with(itemView) {
                txtTitle.text = data.title
                txtDate.text = data.release_date
                txtOverview.text = data.overview
                loadImage(imageView,data.poster_path)

            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}