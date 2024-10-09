package com.test.digiitunes.data.remote

import com.test.digiitunes.data.remote.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://itunes.apple.com/"

interface ItunesApi {
    @GET("search")
    suspend fun search(
        @Query("term") name: String,
        @Query("limit") limit: Int = 50
    ): Response<SearchResponse>
}