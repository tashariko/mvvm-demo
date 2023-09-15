package com.tasha.screens.base

import com.tasha.data.ApiResult
import com.tasha.data.local.entity.Vehicle
import kotlinx.coroutines.flow.Flow

interface BaseRepository {
    fun getVehicleList(): Flow<ApiResult<List<Vehicle>>>
}