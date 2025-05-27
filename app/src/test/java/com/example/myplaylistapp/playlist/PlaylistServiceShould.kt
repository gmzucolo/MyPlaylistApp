package com.example.myplaylistapp.playlist

import com.example.myplaylistapp.data.api.PlaylistAPI
import com.example.myplaylistapp.data.model.Playlist
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
    private val playlists = mock<List<Playlist>>()

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