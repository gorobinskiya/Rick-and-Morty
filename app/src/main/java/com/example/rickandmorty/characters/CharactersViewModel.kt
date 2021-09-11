package com.example.rickandmorty.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.data.CharactersResult
import com.example.rickandmorty.retrofit.RetroServiceInterface
import kotlinx.coroutines.flow.Flow

class CharactersViewModel(private val rickAndMortyRetroService: RetroServiceInterface) : ViewModel() {

    companion object {
        private const val PAGE_SIZE = 50
    }

    fun getListData(): Flow<PagingData<CharactersResult>> {
        return Pager(config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { CharacterPaging(rickAndMortyRetroService) }).flow.cachedIn(viewModelScope)
    }
}