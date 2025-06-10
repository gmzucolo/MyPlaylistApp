package com.example.myplaylistapp.data.service

import com.example.myplaylistapp.R
import com.example.myplaylistapp.data.api.PlaylistAPI
import com.example.myplaylistapp.data.model.PlaylistResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PlaylistService(private val api: PlaylistAPI) {
    suspend fun fetchPlaylists(): Flow<Result<List<PlaylistResponse>>> {
        return flow {
            emit(Result.success(playlists))
        }.catch {
            emit(Result.failure(RuntimeException("Error")))
        }
    }

    val playlists = listOf(
        PlaylistResponse(
            id = "1",
            name = "Hard Rock Cafe",
            category = "rock",
            image = R.drawable.playlist_ic // Substitua pelo seu drawable resource
        ),
        PlaylistResponse(
            id = "2",
            name = "Chilled House",
            category = "house",
            image = R.drawable.playlist_ic // Substitua pelo seu drawable resource
        ),
        PlaylistResponse(
            id = "3",
            name = "US TOP 40 HITS",
            category = "mixed",
            image = R.drawable.playlist_ic // Substitua pelo seu drawable resource
        ),
        PlaylistResponse(
            id = "4",
            name = "90's Rock",
            category = "rock",
            image = R.drawable.playlist_ic // Substitua pelo seu drawable resource
        ),
        PlaylistResponse(
            id = "5",
            name = "Purple Jazz",
            category = "jazz",
            image = R.drawable.playlist_ic // Substitua pelo seu drawable resource

        ),
        PlaylistResponse(
            id = "6",
            name = "90's flashback",
            category = "pop",
            image = R.drawable.playlist_ic // Substitua pelo seu drawable resource

        ),
        PlaylistResponse(
            id = "7",
            name = "Machine Funk",
            category = "electro",
            image = R.drawable.playlist_ic // Substitua pelo seu drawable resource

        ),
        PlaylistResponse(
            id = "8",
            name = "Let's Groove",
            category = "mixed",
            image = R.drawable.playlist_ic // Substitua pelo seu drawable resource

        ),
        PlaylistResponse(
            id = "9",
            name = "Feel The Beat",
            category = "electro",
            image = R.drawable.playlist_ic // Substitua pelo seu drawable resource

        ),
        PlaylistResponse(
            id = "10",
            name = "Best Songs 2020",
            category = "mixed",
            image = R.drawable.playlist_ic // Substitua pelo seu drawable resource

        )
    )
}
