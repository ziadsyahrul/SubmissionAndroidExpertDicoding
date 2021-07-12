package com.ziadsyahrul.submissionandroidexpertdicoding.utils

import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.local.entity.MovieEntity
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.local.entity.TvShowEntity
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.remote.response.MovieItem
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.remote.response.TvShowItem
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.model.Catalog

object DataMapper {
    fun mapMovieResponseToEntities(input: List<MovieItem>): List<MovieEntity>{
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movies = MovieEntity(
                it.id,
                it.title,
                it.overview,
                it.releaseDate,
                it.posterPath,
                isFavorite = false,
                isTvShow = false
            )
            movieList.add(movies)
        }
        return movieList
    }

    fun mapTvResponseToEntities(input: List<TvShowItem>): List<TvShowEntity>{
        val tvList = ArrayList<TvShowEntity>()
        input.map {
            val tvshow = it.posterPath?.let { it1 ->
                TvShowEntity(
                    it.id,
                    it.name,
                    it.overview,
                    it.firstAirDate,
                    it1,
                    isFavorite = false,
                    isTvShow = true
                )
            }
            if (tvshow != null) {
                tvList.add(tvshow)
            }
        }
        return tvList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Catalog>{
        return input.map {
            Catalog(
                it.id,
                it.title,
                it.overview,
                it.posterPath,
                it.releaseDate,
                isFavorite = it.isFavorite,
                isTvShow = it.isTvShow
            )
        }
    }

    fun mapTvEntitiesToDomain(input: List<TvShowEntity>): List<Catalog>{
        return input.map {
            Catalog(
                it.id,
                it.title,
                it.overview,
                it.posterPath,
                it.releaseDate,
                isFavorite = it.isFavorite,
                isTvShow = it.isTvShow
            )
        }
    }

    fun mapDomainToEntities(input: Catalog): MovieEntity{
        return MovieEntity(
            input.id,
            input.title,
            input.description,
            input.releaseDate,
            input.posterPath,
            isFavorite = input.isFavorite,
            isTvShow = input.isTvShow
        )
    }

    fun mapTvDomainToEntities(input: Catalog): TvShowEntity{
        return TvShowEntity(
            input.id,
            input.title,
            input.description,
            input.releaseDate,
            input.posterPath,
            isFavorite = input.isFavorite,
            isTvShow = input.isTvShow
        )
    }

}