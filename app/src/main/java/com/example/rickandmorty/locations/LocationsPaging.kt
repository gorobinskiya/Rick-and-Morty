package com.example.rickandmorty.locations

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.LocationsResults
import com.example.rickandmorty.retrofit.RetroServiceInterface
import java.lang.Exception

class LocationsPaging(private val apiService: RetroServiceInterface) :
    PagingSource<Int, LocationsResults>() {
    override fun getRefreshKey(state: PagingState<Int, LocationsResults>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationsResults> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = apiService.getLocationList(nextPage)
            val nextPageNumber: Int?

            val uri = Uri.parse(response.info.next)
            val nextPageQuery = uri.getQueryParameter("page")
            nextPageNumber = nextPageQuery?.toInt()
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = nextPageNumber
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}