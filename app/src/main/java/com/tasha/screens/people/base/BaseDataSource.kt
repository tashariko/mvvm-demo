package com.tasha.screens.people.base

import com.tasha.data.ApiResult
import com.tasha.data.local.entity.People

interface BaseDataSource {
    suspend fun getPeopleList(): ApiResult<List<People>>
}