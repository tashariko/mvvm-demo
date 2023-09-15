package com.tasha.data.remote

import com.tasha.data.local.entity.People
import com.tasha.data.local.entity.Planet
import com.tasha.data.local.entity.Vehicle

data class ApiVehicleResponse (
    val count: Long,
    val next: String,
    val previous: String? = null,
    val results: List<Vehicle>
)

data class ApiPlanetResponse (
    val count: Long,
    val next: String,
    val previous: String? = null,
    val results: List<Planet>
)

data class ApiPeopleResponse (
    val count: Long,
    val next: String,
    val previous: String? = null,
    val results: List<People>
)