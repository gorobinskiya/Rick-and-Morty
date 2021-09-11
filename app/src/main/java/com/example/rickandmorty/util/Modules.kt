package com.example.rickandmorty.util

import com.example.rickandmorty.characters.CharactersViewModel
import com.example.rickandmorty.episodes.EpisodesViewModel
import com.example.rickandmorty.locations.LocationsViewModel
import com.example.rickandmorty.retrofit.RetroServiceInterface
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {
    val appModule = module {
        factory { provideRetrofit(provideOkHttpClient()) }
        single { provideApi(get()) }
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("https://rickandmortyapi.com/api/").client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }

    fun provideApi(retrofit: Retrofit): RetroServiceInterface = retrofit.create(
        RetroServiceInterface::class.java
    )
}

object ViewModelModules {
    val characterViewModule = module {
        viewModel { CharactersViewModel(get()) }
    }

    val episodesViewModule = module {
        viewModel { EpisodesViewModel(get()) }
    }

    val locationsViewModule = module {
        viewModel { LocationsViewModel(get()) }
    }
}