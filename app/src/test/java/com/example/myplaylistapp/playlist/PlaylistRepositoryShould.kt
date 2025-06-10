package com.example.myplaylistapp.playlist

import com.example.myplaylistapp.data.mapper.PlaylistMapper
import com.example.myplaylistapp.data.model.Playlist
import com.example.myplaylistapp.data.model.PlaylistResponse
import com.example.myplaylistapp.data.repository.PlaylistRepository
import com.example.myplaylistapp.data.service.PlaylistService
import com.example.myplaylistapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PlaylistRepositoryShould : BaseUnitTest() {

    private val service: PlaylistService = mock()
    private val mapper: PlaylistMapper = mock()
    private val playlists = mock<List<Playlist>>()
    private val playlistsResponse = mock<List<PlaylistResponse>>()
    private val expected = Result.success(playlists)
    private val exception = RuntimeException("Something went wrong")

    @Before
    fun setup() {
        runTest {
            whenever(service.fetchPlaylists()).thenReturn(
                flow {
                    emit(Result.success(playlistsResponse))
                }
            )
        }
    }

    @Test
    fun getPlaylistsFromService() = runTest {
        // Arrange
        val repository = mockSuccessCase()

        // Act
        repository.getPlaylists()

        // Assert
        verify(service, times(1)).fetchPlaylists()
    }

    @Test
    fun emitsPlaylistsFromService() = runTest {
        // Arrange & Act
        val repository = mockSuccessCase()

        // Assert
        assertEquals(playlists, repository.getPlaylists().first().getOrNull())
    }

    @Test
    fun propagateErrors() = runTest {
        // Arrange
        whenever(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )

        // Act
        val repository = PlaylistRepository(service)

        // Assert
        assertEquals(exception, repository.getPlaylists().first().exceptionOrNull())
    }

    @Test
    fun delegateBusinessLogicToMapper() = runTest {
        // Arrange
        val repository = mockSuccessCase()

        // Act
        repository.getPlaylists().first()

        // Assert
        verify(mapper, times(1)).invoke(playlistsResponse)
    }

    private suspend fun mockSuccessCase(): PlaylistRepository {
        whenever(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.success(playlistsResponse))
            }

        )

        whenever(mapper.invoke(playlistsResponse)).thenReturn(playlists)

        return PlaylistRepository(service, mapper)
    }
}