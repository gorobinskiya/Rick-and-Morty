package com.example.rickandmorty.data

import java.io.Serializable

data class EpisodesResponse(val info: EpisodesInfo, val results: List<EpisodesResults>)

data class EpisodesInfo(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String?
)

data class EpisodesResults(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
) : Serializable
