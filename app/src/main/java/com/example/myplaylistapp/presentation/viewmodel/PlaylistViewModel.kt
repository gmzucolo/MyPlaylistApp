package com.example.myplaylistapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.myplaylistapp.data.model.Playlist
import com.example.myplaylistapp.data.repository.PlaylistRepository
import kotlinx.coroutines.flow.onEach

class PlaylistViewModel(
    private val repository: PlaylistRepository
) : ViewModel() {

    val loader = MutableLiveData<Boolean>(true)

    val playlists = liveData<Result<List<Playlist>>> {
        loader.postValue(true)
        emitSource(repository.getPlaylists()
            .onEach {
                loader.postValue(false)
            }
            .asLiveData())
    }
}