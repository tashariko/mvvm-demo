package com.tasha.screens.repo

import com.tasha.data.ApiResult
import com.tasha.data.local.entity.Vehicle
import com.tasha.screens.base.BaseRepository
import com.tasha.screens.data.FakeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository(val fakeDataSource: FakeDataSource) : BaseRepository {
    override fun getVehicleList(): Flow<ApiResult<List<Vehicle>>> {
        return flow { emit(fakeDataSource.getVehicleList()) }
    }
}