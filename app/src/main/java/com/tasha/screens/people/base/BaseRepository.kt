package com.tasha.screens.people.base

import com.tasha.data.ApiResult
import com.tasha.data.local.entity.People
import kotlinx.coroutines.flow.Flow

interface BaseRepository {
    fun getPeopleList(): Flow<ApiResult<List<People>>>
}