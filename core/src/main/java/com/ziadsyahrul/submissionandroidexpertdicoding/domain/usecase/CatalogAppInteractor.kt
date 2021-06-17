package com.ziadsyahrul.submissionandroidexpertdicoding.domain.usecase

import com.ziadsyahrul.submissionandroidexpertdicoding.core.Resource
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.model.Catalog
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.repository.ICatalogRepository
import kotlinx.coroutines.flow.Flow

class CatalogAppInteractor(private val iCatalogRepository: ICatalogRepository): CatalogAppUseCase {

    override fun getAllMovie(): Flow<Resource<List<Catalog>>> =
        iCatalogRepository.getAllMovie()

    override fun getAllTvShow(): Flow<Resource<List<Catalog>>> =
        iCatalogRepository.getAllTvShow()

    override fun getFavMovie(): Flow<List<Catalog>> =
        iCatalogRepository.getFavMovie()

    override fun getFavTvShow(): Flow<List<Catalog>> =
        iCatalogRepository.getFavTvShow()

    override fun setCatalogFavorite(catalog: Catalog, state: Boolean) =
        iCatalogRepository.setCatalogFavorite(catalog, state)

    override fun setTvCatalogFavorite(catalog: Catalog, state: Boolean) {
        iCatalogRepository.setTvCatalogFavorite(catalog, state)
    }


}