package com.example.myplaylistapp

import com.example.myplaylistapp.data.api.PlaylistAPI
import com.example.myplaylistapp.data.repository.PlaylistRepository
import com.example.myplaylistapp.data.service.PlaylistService
import com.example.myplaylistapp.presentation.viewmodel.PlaylistViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PlaylistModule {

    @Provides
    fun providePlayListViewModelFactory(repository: PlaylistRepository) = PlaylistViewModelFactory(repository)

    @Provides
    fun bindPlaylistRepository(service: PlaylistService) = PlaylistRepository(service)

    @Singleton
    @Provides
    fun playlistService(api: PlaylistAPI) = PlaylistService(api)

    @Singleton
    @Provides
    fun playlistAPI(retrofit: Retrofit) = retrofit.create(PlaylistAPI::class.java)

    @Singleton
    @Provides
    fun retrofit() = Retrofit.Builder()
        .baseUrl("http://192.168.15.9:8080/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
