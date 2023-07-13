package com.gopay.screens.data

import com.gopay.data.ApiResult
import com.gopay.data.local.entity.Vehicle
import com.gopay.screens.base.BaseDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDataSource : BaseDataSource {
    val vehicle1 = Vehicle(111, "Vehicle1")
    val vehicle2 = Vehicle(222, "Vehicle2")
    val vehicle3 = Vehicle(333, "Vehicle3")
    val vehicle4 = Vehicle(444, "Vehicle4")
    override fun getVehicleList(): Flow<ApiResult<List<Vehicle>>> {
        return flow { emit(ApiResult.success(listOf(vehicle1, vehicle2, vehicle3, vehicle4))) }
    }
}