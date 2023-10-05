package com.tasha.screens.people.base

import com.tasha.data.ApiResult
import com.tasha.data.local.entity.People
import com.tasha.data.local.entity.Planet

interface BaseDataSource {
    suspend fun getPeopleList(): ApiResult<List<People>>

    suspend fun savePeopleList(list: List<People>)
}