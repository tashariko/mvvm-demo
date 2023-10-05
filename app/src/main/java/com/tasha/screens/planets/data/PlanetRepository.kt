package com.tasha.screens.planets.data

import com.tasha.data.ApiResult
import com.tasha.data.local.entity.Planet
import com.tasha.screens.planets.base.BaseDataSource
import com.tasha.screens.planets.base.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PlanetRepository @Inject constructor(
    private val planetRemoteDataSource: BaseDataSource,
    private val planetLocalDataSource: BaseDataSource
) : BaseRepository {

    var showDataInAllState = true
    var saveToDb = true

    override fun getPlanetList(): Flow<ApiResult<List<Planet>>> {

        return flow {
            val itemsList = planetLocalDataSource.getPlanetList()
            if (showDataInAllState) {
                emit(ApiResult.loading<List<Planet>>(itemsList.data))
            } else {
                emit(ApiResult.loading<List<Planet>>())
            }
            val response = planetRemoteDataSource.getPlanetList()
            if (response.status == ApiResult.Status.SUCCESS) {
                if(saveToDb)
                    planetLocalDataSource.savePlanetList(response.data!!)
                emit(ApiResult.success<List<Planet>>(response.data!!))
            } else {
                if (showDataInAllState) {
                    emit(
                        ApiResult.error<List<Planet>>(
                            errorMessage = response.errorMessage,
                            response.data
                        )
                    )
                } else {
                    emit(ApiResult.error<List<Planet>>(errorMessage = response.errorMessage))
                }
            }

        }.flowOn(Dispatchers.IO)
    }
}