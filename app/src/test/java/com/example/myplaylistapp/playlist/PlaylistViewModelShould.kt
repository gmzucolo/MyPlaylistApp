package com.example.myplaylistapp.playlist

import com.example.myplaylistapp.data.model.Playlist
import com.example.myplaylistapp.data.repository.PlaylistRepository
import com.example.myplaylistapp.presentation.viewmodel.PlaylistViewModel
import com.example.myplaylistapp.utils.BaseUnitTest
import com.example.myplaylistapp.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PlaylistViewModelShould : BaseUnitTest() {

    private lateinit var viewModel: PlaylistViewModel
    private val repository: PlaylistRepository = mock()
    private val playlists = mock<List<Playlist>>()
    private val expected = Result.success(playlists)
    private val exception = RuntimeException("Something went wrong")

    @Before
    fun setup() {
        runTest {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        viewModel = PlaylistViewModel(repository)
    }

    @Test
    fun getPlaylistsFromRepository() = runTest {
        // Arrange & Act
        viewModel.playlists.getValueForTest()

        // Assert
        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitsErrorWhenReceiveError() {
        // Arrange
        runTest {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(Result.failure(exception))
                }
            )
        }

        // Act
        val viewModel = PlaylistViewModel(repository)

        // Assert
        assertEquals(exception, viewModel.playlists.getValueForTest()!!.exceptionOrNull())
    }

    @Test
    fun emitsPlaylistsFromRepository() = runTest {
        // Assert
        assertEquals(expected, viewModel.playlists.getValueForTest())
    }
}