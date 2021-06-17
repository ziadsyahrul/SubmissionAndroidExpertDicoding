package com.ziadsyahrul.submissionandroidexpertdicoding.core.source.remote

import com.ziadsyahrul.submissionandroidexpertdicoding.core.BuildConfig
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.remote.network.ApiResponse
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.remote.network.ApiService
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.remote.response.MovieItem
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.remote.response.MovieResponse
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.remote.response.TvShowItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val api: ApiService) {

    suspend fun getMovie(): Flow<ApiResponse<List<MovieItem>>>{
        return flow {
            try {
                val respons = api.getMovie("07f21ca8526a5988e3a4202e8f3cff99")
                val movieResult = respons.results
                if (movieResult.isNotEmpty()) {
                    emit(ApiResponse.Success(respons.results))
                }else{
                    emit(ApiResponse.empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))

            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvShow(): Flow<ApiResponse<List<TvShowItem>>>{
        return flow {
            try {
                val response = api.getTvShow("07f21ca8526a5988e3a4202e8f3cff99")
                val tvResult = response.results
                if (tvResult.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                }else{
                    emit(ApiResponse.empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}