package com.example.rickandmorty.characters

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState

import com.example.rickandmorty.data.CharactersResult
import com.example.rickandmorty.retrofit.RetroServiceInterface
import java.lang.Exception

class CharacterPaging(private val apiService: RetroServiceInterface) :
    PagingSource<Int, CharactersResult>() {
    override fun getRefreshKey(state: PagingState<Int, CharactersResult>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharactersResult> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = apiService.getCharacterList(nextPage)
            val nextPageNumber: Int?
            val uri = Uri.parse(response.info.next)
            val nextPageQuery = uri.getQueryParameter(PAGE_PARAMETER_NAME)
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
        private const val PAGE_PARAMETER_NAME = "page"
    }
}