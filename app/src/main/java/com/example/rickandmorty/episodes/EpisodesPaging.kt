package com.example.rickandmorty.episodes

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.EpisodesResults
import com.example.rickandmorty.retrofit.RetroServiceInterface
import java.lang.Exception

class EpisodesPaging(private val apiService: RetroServiceInterface) :
    PagingSource<Int, EpisodesResults>() {
    override fun getRefreshKey(state: PagingState<Int, EpisodesResults>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodesResults> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = apiService.getEpisodeList(nextPage)
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