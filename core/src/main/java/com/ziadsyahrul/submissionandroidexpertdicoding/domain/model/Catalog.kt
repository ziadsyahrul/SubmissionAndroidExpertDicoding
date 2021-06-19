package com.ziadsyahrul.submissionandroidexpertdicoding.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Catalog(
    val id: Int,
    val title: String,
    val description: String,
    val posterPath: String,
    val releaseDate: String,
    val isFavorite: Boolean = false,
    val isTvShow: Boolean = false
) : Parcelable