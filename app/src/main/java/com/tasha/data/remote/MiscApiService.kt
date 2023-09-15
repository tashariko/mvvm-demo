package com.tasha.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface MiscApiService {

    @GET("/api/starships")
    suspend fun getVehicles(): Response<ApiVehicleResponse>

    @GET("/api/people")
    suspend fun getPeoples(): Response<ApiPeopleResponse>

    @GET("/api/planets")
    suspend fun getPlanets(): Response<ApiPlanetResponse>
}