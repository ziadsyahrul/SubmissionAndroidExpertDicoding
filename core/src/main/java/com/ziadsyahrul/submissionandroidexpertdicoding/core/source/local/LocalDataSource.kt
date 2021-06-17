package com.ziadsyahrul.submissionandroidexpertdicoding.core.source.local

import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.local.entity.MovieEntity
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.local.entity.TvShowEntity
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.local.room.CatalogDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mCatalogDao: CatalogDao) {

    fun getAllMovie(): Flow<List<MovieEntity>> = mCatalogDao.getAllMovie()

    fun getAllTvShow(): Flow<List<TvShowEntity>> = mCatalogDao.getAllTvShow()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = mCatalogDao.getFavoriteMovie()

    fun getFavoriteTvShow(): Flow<List<TvShowEntity>> = mCatalogDao.getFavoriteTv()

    suspend fun insertMovie(movieList: List<MovieEntity>) = mCatalogDao.insertMovie(movieList)

    suspend fun insertTvShow(tvList: List<TvShowEntity>) = mCatalogDao.inserTv(tvList)

    fun setFavMovie(movie: MovieEntity, state: Boolean){
        movie.isFavorite = state
        mCatalogDao.updateMovie(movie)
    }

    fun setFavTv(tvShow: TvShowEntity, state: Boolean){
        tvShow.isFavorite = state
        mCatalogDao.updateTvShow(tvShow)
    }

}