package com.ziadsyahrul.submissionandroidexpertdicoding.core.source.local.room

import androidx.room.*
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.local.entity.MovieEntity
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogDao {

    @Query("SELECT * FROM movieentities where isTvshow = 0")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tvshowentities where isTvshow = 1")
    fun getAllTvShow(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM movieentities where isFavorite = 1 and isTvshow = 0")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tvshowentities where isFavorite = 1 and isTvshow = 1")
    fun getFavoriteTv(): Flow<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserTv(tvShow: List<TvShowEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)

}