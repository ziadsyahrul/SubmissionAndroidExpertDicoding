package com.ziadsyahrul.submissionandroidexpertdicoding.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.google.android.play.core.internal.ca
import com.ziadsyahrul.submissionandroidexpertdicoding.core.Resource
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.model.Catalog
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.usecase.CatalogAppUseCase

class MovieViewModel(private val catalogUseCase: CatalogAppUseCase): ViewModel() {
    fun getMovie(): LiveData<Resource<List<Catalog>>>{
        return catalogUseCase.getAllMovie().asLiveData()
    }
}