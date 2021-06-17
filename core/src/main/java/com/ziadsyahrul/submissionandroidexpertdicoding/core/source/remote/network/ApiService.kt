package com.ziadsyahrul.submissionandroidexpertdicoding.core.source.remote.network

import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.remote.response.MovieResponse
import com.ziadsyahrul.submissionandroidexpertdicoding.core.source.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie")
    suspend fun getMovie(
        @Query("api_key") apiKey: String,
    ): MovieResponse

    @GET("tv")
    suspend fun getTvShow(
        @Query("api_key") apiKey: String,
    ): TvShowResponse
}