package com.tasha.screens.people.data

import com.tasha.data.ApiResult
import com.tasha.data.local.dao.PPVDao
import com.tasha.data.local.dao.PeopleDao
import com.tasha.data.local.entity.People
import com.tasha.data.remote.MiscApiService
import com.tasha.screens.people.base.BaseDataSource
import javax.inject.Inject

class PeopleDataSource @Inject constructor(
    private val miscApiService: MiscApiService
) : BaseDataSource {

    override suspend fun getPeopleList(): ApiResult<List<People>> {
        val response = miscApiService.getPeoples()
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

    override suspend fun savePeopleList(list: List<People>) {
        
    }
}


class PeopleLocalDataSource @Inject constructor(private val peopleDao: PeopleDao,private val ppvDao: PPVDao) :
    BaseDataSource {
    override suspend fun getPeopleList(): ApiResult<List<People>> {
        return ApiResult.success(peopleDao.getItems())
    }

    override suspend fun savePeopleList(list: List<People>) {
        peopleDao.addAllItems(list)

        ppvDao.addToPPVTableForPeople(peopleDao.getItems())
    }
}