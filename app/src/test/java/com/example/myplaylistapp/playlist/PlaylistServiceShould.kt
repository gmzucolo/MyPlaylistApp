package com.example.myplaylistapp.playlist

import com.example.myplaylistapp.R
import com.example.myplaylistapp.data.api.PlaylistAPI
import com.example.myplaylistapp.data.model.PlaylistResponse
import com.example.myplaylistapp.data.service.PlaylistService
import com.example.myplaylistapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PlaylistServiceShould : BaseUnitTest() {

    private lateinit var service: PlaylistService
    private val api: PlaylistAPI = mock()
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

    @Test
    fun fetchPlaylistsFromAPI() = runTest {
        // Arrange
        service = PlaylistService(api)

        // Act
        service.fetchPlaylists().first()

        // Assert
        verify(api, times(1)).fetchAllPlaylists()
    }

    @Test
    fun convertValuesToFlowResultAndEmitsThem() = runTest {
        // Arrange
        whenever(api.fetchAllPlaylists()).thenReturn(playlists)

        // Act
        service = PlaylistService(api)

        // Assert
        assertEquals(Result.success(playlists), service.fetchPlaylists().first())
    }

    @Test
    fun emitsErrorResultWhenNetworkFails() = runTest {
        // Arrange
        whenever(api.fetchAllPlaylists()).thenThrow(RuntimeException("Error"))

        // Act
        service = PlaylistService(api)

        // Assert
        assertEquals(
            "Error",
            service.fetchPlaylists().first().exceptionOrNull()?.message
        )
    }
}