package com.ziadsyahrul.submissionandroidexpertdicoding.injection

import androidx.room.Room
import com.ziadsyahrul.submissionandroidexpertdicoding.core.CatalogRepository
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.local.LocalDataSource
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.local.room.CatalogDatabase
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.remote.RemoteDataSource
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.remote.network.ApiService
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.repository.ICatalogRepository
import com.ziadsyahrul.submissionandroidexpertdicoding.utils.AppExecutor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val baseUrl = "https://api.themoviedb.org/3/discover/"

val databaseModule = module {
    factory { get<CatalogDatabase>().catalogDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            CatalogDatabase::class.java, "catalog.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutor() }
    single<ICatalogRepository> { CatalogRepository(get(), get(), get()) }

}


