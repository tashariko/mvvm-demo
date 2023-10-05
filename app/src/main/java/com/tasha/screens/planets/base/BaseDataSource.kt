package com.tasha.screens.planets.base

import com.tasha.data.ApiResult
import com.tasha.data.local.entity.Planet
import com.tasha.data.local.entity.Vehicle

interface BaseDataSource {
    suspend fun getPlanetList(): ApiResult<List<Planet>>

    suspend fun savePlanetList(list: List<Planet>)
}