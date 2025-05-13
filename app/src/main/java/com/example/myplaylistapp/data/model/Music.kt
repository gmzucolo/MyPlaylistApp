package com.example.myplaylistapp.data.model

import com.example.myplaylistapp.R

data class Playlist(
    val id: String,
    val name: String,
    val category: String,
    val image: Int = R.drawable.playlist_ic
)