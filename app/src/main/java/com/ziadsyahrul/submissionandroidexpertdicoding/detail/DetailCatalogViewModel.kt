package com.ziadsyahrul.submissionandroidexpertdicoding.detail

import androidx.lifecycle.ViewModel
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.model.Catalog
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.usecase.CatalogAppUseCase

class DetailCatalogViewModel(private val catalogUseCase: CatalogAppUseCase): ViewModel(){

    fun setCatalogFavorite(catalog: Catalog, newState: Boolean){
        catalogUseCase.setCatalogFavorite(catalog, newState)
    }

    fun setTvCatalogFavorite(catalog: Catalog, newState: Boolean){
        catalogUseCase.setTvCatalogFavorite(catalog, newState)
    }
}