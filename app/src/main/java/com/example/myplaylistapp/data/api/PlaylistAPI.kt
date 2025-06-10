package com.example.myplaylistapp.data.api

import com.example.myplaylistapp.data.model.PlaylistResponse
import retrofit2.http.GET

interface PlaylistAPI {
    @GET("playlists")
    suspend fun fetchAllPlaylists(): List<PlaylistResponse>
}
