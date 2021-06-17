package com.ziadsyahrul.submissionandroidexpertdicoding.core.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tvshowentities")
data class TvShowEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "releaseDate")
    val releaseDate: String,

    @ColumnInfo(name = "posterPath")
    val posterPath: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,

    @ColumnInfo(name = "isTvshow")
    var isTvShow: Boolean = false
)