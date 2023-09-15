package com.tasha.screens.base

import com.tasha.data.ApiResult
import com.tasha.data.local.entity.Vehicle

interface BaseDataSource {
    suspend fun getVehicleList(): ApiResult<List<Vehicle>>
}