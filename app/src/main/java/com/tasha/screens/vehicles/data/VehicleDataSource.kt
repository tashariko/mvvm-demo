package com.tasha.screens.vehicles.data

import com.tasha.data.ApiResult
import com.tasha.data.local.dao.PPVDao
import com.tasha.data.local.dao.VehicleDao
import com.tasha.data.local.entity.Vehicle
import com.tasha.data.remote.MiscApiService
import com.tasha.screens.vehicles.base.BaseDataSource
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

    override suspend fun saveVehicleList(list: List<Vehicle>) {

    }
}


class VehicleLocalDataSource @Inject constructor(private val vehicleDao: VehicleDao,private val ppvDao: PPVDao) :
    BaseDataSource {
    override suspend fun getVehicleList(): ApiResult<List<Vehicle>> {
        return ApiResult.success(vehicleDao.getItems())
    }

    override suspend fun saveVehicleList(list: List<Vehicle>) {
        vehicleDao.addAllItems(list)

        ppvDao.addToPPVTableForVehicle(vehicleDao.getItems())
    }
}