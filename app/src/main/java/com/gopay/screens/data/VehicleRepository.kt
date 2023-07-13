package com.gopay.screens.data

import android.annotation.SuppressLint
import com.gopay.data.ApiResult
import com.gopay.data.local.entity.Vehicle
import com.gopay.screens.base.BaseDataSource
import com.gopay.screens.base.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class VehicleRepository @Inject constructor(
    private val vehicleRemoteDataSource: BaseDataSource,
    private val vehicleLocalDataSource: BaseDataSource
) : BaseRepository {

    var showDataInAllState = false


    override fun getVehicleList(): Flow<ApiResult<List<Vehicle>>> {

        return flow {
            val itemsList = vehicleLocalDataSource.getVehicleList()
            if (showDataInAllState) {
                emit(ApiResult.loading<List<Vehicle>>(itemsList.data))
            } else {
                emit(ApiResult.loading<List<Vehicle>>())
            }
            val response = vehicleRemoteDataSource.getVehicleList()
            if (response.status == ApiResult.Status.SUCCESS) {
                emit(ApiResult.success<List<Vehicle>>(response.data!!))
            } else {
                if (showDataInAllState) {
                    emit(
                        ApiResult.error<List<Vehicle>>(
                            errorMessage = response.errorMessage,
                            response.data
                        )
                    )
                } else {
                    emit(ApiResult.error<List<Vehicle>>(errorMessage = response.errorMessage))
                }
            }

        }.flowOn(Dispatchers.IO)
    }
}