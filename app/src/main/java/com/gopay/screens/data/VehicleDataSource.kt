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
    private val miscApiService: MiscApiService,
    private val vehicleDao: VehicleDao? = null
) : BaseDataSource{

    var showDataInAllState = false

    override fun getVehicleList(): Flow<ApiResult<List<Vehicle>>> {
        return flow {
            val itemsList = vehicleDao?.getItems()
            with(itemsList) {
                if (showDataInAllState) {
                    emit(ApiResult.loading<List<Vehicle>>(this))
                }else{
                    emit(ApiResult.loading<List<Vehicle>>())
                }

                val response = miscApiService.getVehicles()
                if(response.isSuccessful){
                    response.body()?.let {
                        emit(ApiResult.success(it.results))
                    }?:run {
                        if(showDataInAllState) {
                            emit(ApiResult.error(errorMessage = "Error Occurred", itemsList))
                        }else{
                            emit(ApiResult.error<List<Vehicle>>(errorMessage = "Error Occurred"))
                        }
                    }
                }else{
                    if(showDataInAllState)
                        emit(ApiResult.error<List<Vehicle>>(errorMessage = "Error Occurred",itemsList))
                    else
                        emit(ApiResult.error<List<Vehicle>>(errorMessage = "Error Occurred"))
                }
            }

        }.flowOn(Dispatchers.IO)
    }

}