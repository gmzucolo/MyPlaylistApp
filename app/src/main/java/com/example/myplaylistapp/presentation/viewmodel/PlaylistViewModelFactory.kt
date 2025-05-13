package com.example.myplaylistapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myplaylistapp.domain.PlaylistUseCase

class PlaylistViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlaylistViewModel(PlaylistUseCase()) as T
    }
}