package com.ziadsyahrul.submissionandroidexpertdicoding.core

import android.net.Network
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.local.LocalDataSource
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.remote.RemoteDataSource
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.remote.network.ApiResponse
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.remote.response.MovieItem
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.remote.response.TvShowItem
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.model.Catalog
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.repository.ICatalogRepository
import com.ziadsyahrul.submissionandroidexpertdicoding.utils.AppExecutor
import com.ziadsyahrul.submissionandroidexpertdicoding.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CatalogRepository(
    private val remoteData: RemoteDataSource,
    private val localData: LocalDataSource,
    private val appExecutor: AppExecutor
) : ICatalogRepository {


    override fun getAllMovie(): Flow<Resource<List<Catalog>>> =
        object : NetworkBoundResource<List<Catalog>, List<MovieItem>>() {
            override fun loadFromDB(): Flow<List<Catalog>> {
                return localData.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Catalog>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieItem>>> {
                return remoteData.getMovie()
            }

            override suspend fun saveCallResult(data: List<MovieItem>) {
                val movie = DataMapper.mapMovieResponseToEntities(data)
                localData.insertMovie(movie)
            }

        }.asFlow()

    override fun getAllTvShow(): Flow<Resource<List<Catalog>>> =
        object : NetworkBoundResource<List<Catalog>, List<TvShowItem>>() {
            override fun loadFromDB(): Flow<List<Catalog>> {
                return localData.getAllTvShow().map {
                    DataMapper.mapTvEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Catalog>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowItem>>> {
                return remoteData.getTvShow()
            }

            override suspend fun saveCallResult(data: List<TvShowItem>) {
                val tvList = DataMapper.mapTvResponseToEntities(data)
                localData.insertTvShow(tvList)
            }

        }.asFlow()

    override fun getFavMovie(): Flow<List<Catalog>> {
        return localData.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavTvShow(): Flow<List<Catalog>> {
        return localData.getFavoriteTvShow().map {
            DataMapper.mapTvEntitiesToDomain(it)
        }
    }

    override fun setCatalogFavorite(catalog: Catalog, state: Boolean) {
        val catalogEntity = DataMapper.mapDomainToEntities(catalog)
        appExecutor.diskIO().execute { localData.setFavMovie(catalogEntity, state) }
    }

    override fun setTvCatalogFavorite(catalog: Catalog, state: Boolean) {
        val tvCatalogEntity = DataMapper.mapTvDomainToEntities(catalog)
        appExecutor.diskIO().execute { localData.setFavTv(tvCatalogEntity, state) }
    }


}