package com.example.myplaylistapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myplaylistapp.R
import com.example.myplaylistapp.presentation.viewmodel.PlaylistViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myplaylistapp.MyApplication

class PlaylistFragment : Fragment() {

    private val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this, MyApplication().factory).get(PlaylistViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        viewModel.playlists.observe(this as LifecycleOwner) { playlist ->
            if (playlist.getOrNull() != null) {
                with(view as RecyclerView) {
                    layoutManager = LinearLayoutManager(context)
                    adapter = MyPlaylistRecyclerViewAdapter(playlist.getOrNull()!!)
                }
            } else {
                // nothing
            }
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = PlaylistFragment()
    }
}