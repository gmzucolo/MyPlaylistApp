package com.example.myplaylistapp.data.model

data class PlaylistResponse(
    val id: String,
    val name: String,
    val category: String,
    val image: Int
)

data class Playlist(
    val id: String,
    val name: String,
    val category: String,
    val image: Int
)