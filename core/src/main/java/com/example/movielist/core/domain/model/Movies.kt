package com.example.movielist.core.domain.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Movies (
    val id: Int,
    val title: String,
    val release_date: String,
    val poster_path: String,
    val overview: String,
    val isFavorite:Boolean
):Parcelable