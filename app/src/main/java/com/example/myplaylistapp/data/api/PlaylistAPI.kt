package com.example.myplaylistapp.data.api

import com.example.myplaylistapp.data.model.Playlist

interface PlaylistAPI {
    suspend fun fetchAllPlaylists(): List<Playlist>
}
