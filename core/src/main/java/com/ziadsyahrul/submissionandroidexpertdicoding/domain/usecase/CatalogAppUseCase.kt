package com.ziadsyahrul.submissionandroidexpertdicoding.domain.usecase

import com.ziadsyahrul.submissionandroidexpertdicoding.core.Resource
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.model.Catalog
import kotlinx.coroutines.flow.Flow

interface CatalogAppUseCase {

    fun getAllMovie(): Flow<Resource<List<Catalog>>>

    fun getAllTvShow(): Flow<Resource<List<Catalog>>>

    fun getFavMovie(): Flow<List<Catalog>>

    fun getFavTvShow(): Flow<List<Catalog>>

    fun setCatalogFavorite(catalog: Catalog, state: Boolean)

    fun setTvCatalogFavorite(catalog: Catalog, state: Boolean)
}