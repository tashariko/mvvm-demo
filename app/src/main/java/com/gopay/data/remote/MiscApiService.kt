package com.gopay.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface MiscApiService {

    @GET("/api/starships")
    suspend fun getVehicles(): Response<APIResponse>
}