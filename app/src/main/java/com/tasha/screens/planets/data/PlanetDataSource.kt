package com.tasha.screens.planets.data

import com.tasha.data.ApiResult
import com.tasha.data.local.dao.PlanetDao
import com.tasha.data.local.entity.Planet
import com.tasha.data.remote.MiscApiService
import com.tasha.screens.planets.base.BaseDataSource
import javax.inject.Inject

class PlanetDataSource @Inject constructor(
    private val miscApiService: MiscApiService
) : BaseDataSource {

    override suspend fun getPlanetList(): ApiResult<List<Planet>> {
        val response = miscApiService.getPlanets()
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
}


class PlanetLocalDataSource @Inject constructor(private val planetDao: PlanetDao) :
    BaseDataSource {
    override suspend fun getPlanetList(): ApiResult<List<Planet>> {
        return ApiResult.success(planetDao.getItems())
    }
}