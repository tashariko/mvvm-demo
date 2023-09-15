package com.tasha.screens.people.data

import com.tasha.data.ApiResult
import com.tasha.data.local.entity.People
import com.tasha.screens.people.base.BaseDataSource
import com.tasha.screens.people.base.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PeopleRepository @Inject constructor(
    private val peopleRemoteDataSource: BaseDataSource,
    private val peopleLocalDataSource: BaseDataSource
) : BaseRepository {

    var showDataInAllState = true

    override fun getPeopleList(): Flow<ApiResult<List<People>>> {

        return flow {
            val itemsList = peopleLocalDataSource.getPeopleList()
            if (showDataInAllState) {
                emit(ApiResult.loading<List<People>>(itemsList.data))
            } else {
                emit(ApiResult.loading<List<People>>())
            }
            val response = peopleRemoteDataSource.getPeopleList()
            if (response.status == ApiResult.Status.SUCCESS) {
                emit(ApiResult.success<List<People>>(response.data!!))
            } else {
                if (showDataInAllState) {
                    emit(
                        ApiResult.error<List<People>>(
                            errorMessage = response.errorMessage,
                            response.data
                        )
                    )
                } else {
                    emit(ApiResult.error<List<People>>(errorMessage = response.errorMessage))
                }
            }

        }.flowOn(Dispatchers.IO)
    }
}