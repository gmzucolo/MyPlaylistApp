package com.example.myplaylistapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myplaylistapp.data.model.Playlist
import com.example.myplaylistapp.domain.PlaylistUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlaylistViewModel(
    val useCase: PlaylistUseCase
) : ViewModel() {

    private val _playlists: MutableStateFlow<List<Playlist>> = MutableStateFlow(emptyList())
    val playlists: StateFlow<List<Playlist>> = _playlists.asStateFlow()
}