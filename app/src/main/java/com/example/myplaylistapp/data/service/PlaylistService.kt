package com.example.myplaylistapp.data.service

import com.example.myplaylistapp.data.api.PlaylistAPI
import com.example.myplaylistapp.data.model.Playlist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PlaylistService(
    private val api: PlaylistAPI
) {
    suspend fun fetchPlaylists() : Flow<Result<List<Playlist>>> {
        return flow {
            emit(Result.success(api.fetchAllPlaylists()))
        }.catch {
            emit(Result.failure(RuntimeException("Error")))
        }
    }

}
