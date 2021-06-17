package com.ziadsyahrul.submissionandroidexpertdicoding.injection

import com.ziadsyahrul.submissionandroidexpertdicoding.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel {
        FavoriteViewModel(get())
    }
}