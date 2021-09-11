package com.example.rickandmorty.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.data.LocationsResults
import com.example.rickandmorty.retrofit.RetroServiceInterface
import kotlinx.coroutines.flow.Flow

class LocationsViewModel(private val retroService: RetroServiceInterface) : ViewModel() {

    companion object {
        private const val PAGE_SIZE = 50
    }

    fun getListData(): Flow<PagingData<LocationsResults>> {
        return Pager(config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { LocationsPaging(retroService) }).flow.cachedIn(viewModelScope)
    }
}