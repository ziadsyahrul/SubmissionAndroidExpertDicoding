package com.ziadsyahrul.submissionandroidexpertdicoding.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ziadsyahrul.submissionandroidexpertdicoding.core.Resource
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.model.Catalog
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.usecase.CatalogAppUseCase

class TvShowViewModel(private val catalogUseCase: CatalogAppUseCase): ViewModel() {
    fun getTvShow(): LiveData<Resource<List<Catalog>>> {
        return catalogUseCase.getAllTvShow().asLiveData()
    }
}