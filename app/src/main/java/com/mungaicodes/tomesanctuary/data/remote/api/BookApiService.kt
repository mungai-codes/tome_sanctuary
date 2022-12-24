package com.mungaicodes.tomesanctuary.data.remote.api

import com.mungaicodes.tomesanctuary.data.remote.dto.Response
import com.mungaicodes.tomesanctuary.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {

    @GET("/books/v1/volumes")
    suspend fun searchVolumes(
        @Query(value = "q") query: String,
        @Query(value = "apikey") key: String = API_KEY
    ): Response

}