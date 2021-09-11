package com.example.rickandmorty.retrofit

import com.example.rickandmorty.data.CharacterResponse
import com.example.rickandmorty.data.EpisodesResponse
import com.example.rickandmorty.data.LocationsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceInterface {
    companion object {
        const val PAGE_PARAMETER_NAME = "page"
    }

    @GET("character")
    suspend fun getCharacterList(@Query(PAGE_PARAMETER_NAME) query: Int): CharacterResponse

    @GET("location")
    suspend fun getLocationList(@Query(PAGE_PARAMETER_NAME) query: Int): LocationsResponse

    @GET("episode")
    suspend fun getEpisodeList(@Query(PAGE_PARAMETER_NAME) query: Int): EpisodesResponse

}