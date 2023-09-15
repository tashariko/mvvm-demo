package com.tasha.screens.planets.base

import com.tasha.data.ApiResult
import com.tasha.data.local.entity.Planet
import kotlinx.coroutines.flow.Flow

interface BaseRepository {
    fun getPlanetList(): Flow<ApiResult<List<Planet>>>
}