package com.tasha.screens.planets.base

import com.tasha.data.ApiResult
import com.tasha.data.local.entity.Planet

interface BaseDataSource {
    suspend fun getPlanetList(): ApiResult<List<Planet>>
}