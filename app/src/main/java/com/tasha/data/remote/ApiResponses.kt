package com.tasha.data.remote

import com.tasha.data.local.entity.Vehicle

data class APIResponse (
    val count: Long,
    val next: String,
    val previous: List<Vehicle>? = null,
    val results: List<Vehicle>
)
