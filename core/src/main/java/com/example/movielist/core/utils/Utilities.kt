package com.example.movielist.core.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movielist.core.R
import java.util.*

const val key = "403a22d62769b2f5b28ce5321b91690f"
const val APIMOVIE = "https://api.themoviedb.org/3/"

fun getProgressDrawable(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}
fun ImageView.loadImage(uri:String?, progressDrawable: CircularProgressDrawable){
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.ic_baseline_error_outline_24)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}

fun loadImage(view: ImageView, url:String?){
    view.loadImage("https://image.tmdb.org/t/p/w154"+url, getProgressDrawable(view.context))
}
fun loadImageFull(view: ImageView, url:String?){
    view.loadImage("https://image.tmdb.org/t/p/original"+url, getProgressDrawable(view.context))
}
val isRTL: Boolean
    get() = isRTL(Locale.getDefault())

private fun isRTL(locale: Locale): Boolean {
    val directionality = Character.getDirectionality(locale.displayName[0]).toInt()
    return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT.toInt() || directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC.toInt()
}