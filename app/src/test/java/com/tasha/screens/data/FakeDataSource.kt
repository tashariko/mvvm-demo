package com.tasha.screens.data

import com.tasha.data.ApiResult
import com.tasha.data.local.entity.Vehicle
import com.tasha.screens.base.BaseDataSource

class FakeDataSource : BaseDataSource {
    val vehicle1 = Vehicle(111, "Vehicle1")
    val vehicle2 = Vehicle(222, "Vehicle2")
    val vehicle3 = Vehicle(333, "Vehicle3")
    val vehicle4 = Vehicle(444, "Vehicle4")
    override suspend fun getVehicleList(): ApiResult<List<Vehicle>> {
        return ApiResult.success(listOf(vehicle1, vehicle2, vehicle3, vehicle4))
    }
}