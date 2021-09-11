package com.example.rickandmorty.data

import java.io.Serializable

data class CharacterResponse(
    val info: CharactersInfo, val results: List<CharactersResult>
)

data class CharactersInfo(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String? = null
)

data class CharactersResult(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharactersOrigin,
    val location: CharactersLocation,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
) : Serializable

data class CharactersOrigin(val name: String, val url: String)
data class CharactersLocation(val name: String, val url: String)
