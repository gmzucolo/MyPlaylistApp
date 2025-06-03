package com.example.myplaylistapp

import android.app.Application
import com.example.myplaylistapp.data.api.PlaylistAPI
import com.example.myplaylistapp.data.repository.PlaylistRepository
import com.example.myplaylistapp.data.service.PlaylistService
import com.example.myplaylistapp.presentation.viewmodel.PlaylistViewModelFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {
    fun retrofit() = Retrofit.Builder()
        .baseUrl("http://192.168.15.9:8080/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val factory = PlaylistViewModelFactory(
        PlaylistRepository(
            PlaylistService(
                retrofit().create(
                    PlaylistAPI::class.java
                )
            )
        )
    )
}