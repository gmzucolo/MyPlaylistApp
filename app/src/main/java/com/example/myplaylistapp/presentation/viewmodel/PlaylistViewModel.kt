package com.example.myplaylistapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.myplaylistapp.data.model.Playlist
import com.example.myplaylistapp.data.repository.PlaylistRepository

class PlaylistViewModel(
    private val repository: PlaylistRepository
) : ViewModel() {
    val playlists = liveData<Result<List<Playlist>>> {
        emitSource(repository.getPlaylists().asLiveData())
    }

//    private val _playlists: MutableLiveData<Result<List<Playlist>>> = MutableLiveData()
//    val playlists: LiveData<Result<List<Playlist>>> = _playlists
//
//    init {
//        viewModelScope.launch {
//            repository.getPlaylists().collect {
//                _playlists.value = it
//            }
//        }
//    }
}