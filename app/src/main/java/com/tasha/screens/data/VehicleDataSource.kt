package com.tasha.screens.data

import com.tasha.data.ApiResult
import com.tasha.data.local.dao.VehicleDao
import com.tasha.data.local.entity.Vehicle
import com.tasha.data.remote.MiscApiService
import com.tasha.screens.base.BaseDataSource
import javax.inject.Inject

class VehicleDataSource @Inject constructor(
    private val miscApiService: MiscApiService
) : BaseDataSource {

    override suspend fun getVehicleList(): ApiResult<List<Vehicle>> {
        val response = miscApiService.getVehicles()
        if (response.isSuccessful) {
            response.body()?.let {
                return ApiResult.success(it.results)
            } ?: run {
                return ApiResult.error(errorMessage = "Error Occurred")
            }
        } else {
            return ApiResult.error(errorMessage = "Error Occurred")
        }

    }
}


class VehicleLocalDataSource @Inject constructor(private val vehicleDao: VehicleDao) :
    BaseDataSource {
    override suspend fun getVehicleList(): ApiResult<List<Vehicle>> {
        return ApiResult.success(vehicleDao.getItems())
    }
}