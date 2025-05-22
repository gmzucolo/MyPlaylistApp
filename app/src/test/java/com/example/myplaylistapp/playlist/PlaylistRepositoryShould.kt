package com.example.myplaylistapp.playlist

import com.example.myplaylistapp.data.repository.PlaylistRepository
import com.example.myplaylistapp.data.service.PlaylistService
import com.example.myplaylistapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runTest
import org.junit.Test

class PlaylistRepositoryShould : BaseUnitTest() {

    private val service: PlaylistService = mock()

    @Test
    fun getPlaylistsFromService() = runTest {
        // Arrange
        val repository = PlaylistRepository(service)

        // Act
        repository.getPlaylists()

        // Assert
        verify(service, times(1)).fetchPlaylists()
    }
}