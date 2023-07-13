package com.gopay.screens.data

import com.gopay.data.ApiResult
import com.gopay.data.local.dao.VehicleDao
import com.gopay.data.local.entity.Vehicle
import com.gopay.data.remote.MiscApiService
import com.gopay.screens.base.BaseDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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