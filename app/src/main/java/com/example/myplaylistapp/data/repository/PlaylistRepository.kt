package com.example.myplaylistapp.data.repository

import com.example.myplaylistapp.data.model.Playlist
import kotlinx.coroutines.flow.Flow

class PlaylistRepository {
    suspend fun getPlaylists() : Flow<Result<List<Playlist>>> {
        TODO("Not yet implemented")
    }
}