package com.example.myplaylistapp.data.repository

import com.example.myplaylistapp.data.mapper.PlaylistMapper
import com.example.myplaylistapp.data.model.Playlist
import com.example.myplaylistapp.data.service.PlaylistService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlaylistRepository(
    private val service: PlaylistService,
    private val mapper: PlaylistMapper = PlaylistMapper()
) {
    suspend fun getPlaylists(): Flow<Result<List<Playlist>>> =
        service.fetchPlaylists().map {
            if (it.isSuccess)
                Result.success(mapper(it.getOrNull()!!))
            else
                Result.failure(it.exceptionOrNull()!!)
        }
}