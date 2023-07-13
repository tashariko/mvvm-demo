package com.gopay.screens.data

import com.gopay.data.ApiResult
import com.gopay.data.local.entity.Vehicle
import com.gopay.screens.base.BaseDataSource
import com.gopay.screens.base.BaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VehicleRepository @Inject constructor(private val vehicleDataSource: BaseDataSource) : BaseRepository{

    override fun getVehicleList(): Flow<ApiResult<List<Vehicle>>> {
        return vehicleDataSource.getVehicleList()
    }
}