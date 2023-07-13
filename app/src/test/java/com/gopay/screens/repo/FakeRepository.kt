package com.gopay.screens.repo

import com.gopay.data.ApiResult
import com.gopay.data.local.entity.Vehicle
import com.gopay.screens.base.BaseRepository
import com.gopay.screens.data.FakeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository(val fakeDataSource: FakeDataSource) : BaseRepository {
    override fun getVehicleList(): Flow<ApiResult<List<Vehicle>>> {
        return flow { emit(fakeDataSource.getVehicleList()) }
    }
}