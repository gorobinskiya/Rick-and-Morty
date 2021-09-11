package com.example.rickandmorty.data

import java.io.Serializable

data class LocationsResponse(
    val info: LocationsInfo, val results: List<LocationsResults>
)


data class LocationsInfo(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String? = null
)

data class LocationsResults(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String
) : Serializable
