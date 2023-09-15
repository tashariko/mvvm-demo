package com.tasha.screens.vehicles.data

import com.tasha.data.ApiResult
import com.tasha.data.local.entity.Vehicle
import com.tasha.screens.vehicles.base.BaseDataSource
import com.tasha.screens.vehicles.base.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class VehicleRepository @Inject constructor(
    private val vehicleRemoteDataSource: BaseDataSource,
    private val vehicleLocalDataSource: BaseDataSource
) : BaseRepository {

    var showDataInAllState = true
    var saveToDb = true

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
                if(saveToDb)
                    vehicleLocalDataSource.saveVehicleList(response.data!!)

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