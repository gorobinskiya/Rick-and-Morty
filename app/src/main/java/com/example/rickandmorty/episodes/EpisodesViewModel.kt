package com.example.rickandmorty.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.data.EpisodesResults
import com.example.rickandmorty.retrofit.RetroServiceInterface
import kotlinx.coroutines.flow.Flow

class EpisodesViewModel(private val retroService: RetroServiceInterface) : ViewModel() {

    companion object {
        private const val PAGE_SIZE = 50
    }

    fun getListData(): Flow<PagingData<EpisodesResults>> {
        return Pager(config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { EpisodesPaging(retroService) }).flow.cachedIn(viewModelScope)
    }
}