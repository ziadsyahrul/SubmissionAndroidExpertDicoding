package com.ziadsyahrul.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.usecase.CatalogAppUseCase

class FavoriteViewModel(catalogUseCase: CatalogAppUseCase): ViewModel() {

    val movieFavorite = catalogUseCase.getFavMovie().asLiveData()

    val tvShowFavorite = catalogUseCase.getFavTvShow().asLiveData()

}