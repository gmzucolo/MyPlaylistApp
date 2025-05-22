package com.example.myplaylistapp.data.service

import com.example.myplaylistapp.data.model.Playlist
import kotlinx.coroutines.flow.Flow

class PlaylistService {
    suspend fun fetchPlaylists() : Flow<Result<List<Playlist>>> {
        TODO("Not yet implemented")
    }

}
