package com.mungaicodes.tomesanctuary.data.remote.api

import com.mungaicodes.tomesanctuary.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {

    @GET("v1/volumes")
    suspend fun searchVolumes(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = API_KEY
    )

}