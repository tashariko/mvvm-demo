package com.gopay.screens.base

import com.gopay.data.ApiResult
import com.gopay.data.local.entity.Vehicle
import kotlinx.coroutines.flow.Flow

interface BaseDataSource {
    fun getVehicleList(): Flow<ApiResult<List<Vehicle>>>
}