package com.ziadsyahrul.submissionandroidexpertdicoding.utils

import androidx.recyclerview.widget.DiffUtil
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.model.Catalog
import org.jetbrains.annotations.Nullable

class DiffUtils(private val oldList: List<Catalog>, private val newList: List<Catalog>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (overview,
            releaseDate,
            _,
            title,
            posterPath,
            isFavorite,
            isTvShow) = oldList[oldItemPosition]

        val (overview1,
            releaseDate1,
            _,
            title1,
            posterPath1,
            isFavorite1,
            isTvShow1) = oldList[oldItemPosition]

        return overview == overview1
                && releaseDate == releaseDate1
                && title == title1
                && posterPath == posterPath1
                && isFavorite == isFavorite1
                && isTvShow == isTvShow1
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}