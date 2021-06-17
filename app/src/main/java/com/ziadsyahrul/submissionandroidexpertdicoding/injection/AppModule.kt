package com.ziadsyahrul.submissionandroidexpertdicoding.injection

import com.ziadsyahrul.submissionandroidexpertdicoding.detail.DetailCatalogViewModel
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.usecase.CatalogAppInteractor
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.usecase.CatalogAppUseCase
import com.ziadsyahrul.submissionandroidexpertdicoding.movie.MovieViewModel
import com.ziadsyahrul.submissionandroidexpertdicoding.tvshow.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<CatalogAppUseCase> { CatalogAppInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailCatalogViewModel(get()) }

}