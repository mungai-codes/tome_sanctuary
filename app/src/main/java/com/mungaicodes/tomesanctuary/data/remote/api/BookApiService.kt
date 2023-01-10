package com.mungaicodes.tomesanctuary.data.remote.api

import com.mungaicodes.tomesanctuary.data.remote.dto.Item
import com.mungaicodes.tomesanctuary.data.remote.dto.Response
import com.mungaicodes.tomesanctuary.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApiService {

    @GET("/books/v1/volumes")
    suspend fun searchVolumes(
        @Query(value = "q") query: String,
        @Query(value = "download") downloadFormat: String = "epub",
        @Query(value = "filter") filter: String,
        @Query(value = "startIndex") startIndex: Int = 0,
        @Query(value = "maxResults") maxResults: Int = 12,
        @Query(value = "printType") printType: String = "books",
        @Query(value = "projection") projection: String = "lite",
        @Query(value = "orderBy") sorting: String = "relevance",
        @Query(value = "apikey") apiKey: String = API_KEY
    ): Response


    @GET("/books/v1/volumes/{volumeId}")
    suspend fun findBookByVolumeId(
        @Path("volumeId") volumeId: String,
        @Query(value = "apikey") apiKey: String = API_KEY
    ): Item


}