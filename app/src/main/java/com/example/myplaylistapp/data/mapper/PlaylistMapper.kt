package com.example.myplaylistapp.data.mapper

import com.example.myplaylistapp.data.model.Playlist
import com.example.myplaylistapp.data.model.PlaylistResponse

class PlaylistMapper : Function1<List<PlaylistResponse>, List<Playlist>> {
    override fun invoke(p1: List<PlaylistResponse>): List<Playlist> {
        TODO("Not yet implemented")
    }
}