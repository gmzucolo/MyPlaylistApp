package com.example.myplaylistapp.data.repository

import com.example.myplaylistapp.data.model.Playlist
import com.example.myplaylistapp.data.service.PlaylistService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlaylistRepository(
    private val service: PlaylistService
) {
    suspend fun getPlaylists() : Flow<Result<List<Playlist>>> {
        service.fetchPlaylists()

        return flow {}
    }
}