package com.mungaicodes.tomesanctuary.data.remote.api

import com.mungaicodes.tomesanctuary.data.remote.dto.Response
import com.mungaicodes.tomesanctuary.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {

    @GET("/books/v1/volumes")
    suspend fun searchVolumes(
        @Query(value = "q") query: String,
        @Query(value = "download") downloadFormat: String = "epub",
        @Query(value = "filter") filter: String = "partial",
        @Query(value = "startIndex") startIndex: Int = 0,
        @Query(value = "maxResults") maxResults: Int = 12,
        @Query(value = "printType") printType: String = "books",
        @Query(value = "projection") projection: String = "lite",
        @Query(value = "orderBy") sorting: String = "relevance",
        @Query(value = "apikey") apiKey: String = API_KEY
    ): Response


}