package com.ziadsyahrul.submissionandroidexpertdicoding

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.model.Catalog
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.usecase.CatalogAppUseCase

class FavoriteViewModel(private val catalogUseCase: CatalogAppUseCase): ViewModel() {

    val movieFavorite = catalogUseCase.getFavMovie().asLiveData()

    val tvShowFavorite = catalogUseCase.getFavTvShow().asLiveData()

}