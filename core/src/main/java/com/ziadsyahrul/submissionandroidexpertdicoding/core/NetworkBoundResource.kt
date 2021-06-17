package com.ziadsyahrul.submissionandroidexpertdicoding.core

import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbsource = loadFromDB().first()
        if (shouldFetch(dbsource)) {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map {
                        Resource.Success(
                            it
                        )
                    })
                }
                is ApiResponse.empty -> {
                    emitAll(loadFromDB().map {
                        Resource.Success(
                            it
                        )
                    })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(
                        Resource.Error<ResultType>(
                            apiResponse.errorMessage
                        )
                    )
                }
            }
        } else {
            emitAll(loadFromDB().map {
                Resource.Success(
                    it
                )
            })
        }
    }

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    protected open fun onFetchFailed() {}

    fun asFlow(): Flow<Resource<ResultType>> = result

}