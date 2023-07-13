package com.gopay.data.remote

import com.gopay.data.local.entity.Vehicle

data class APIResponse (
    val count: Long,
    val next: String,
    val previous: List<Vehicle>? = null,
    val results: List<Vehicle>
)
