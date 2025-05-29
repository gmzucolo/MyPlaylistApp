package com.example.myplaylistapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myplaylistapp.R
import com.example.myplaylistapp.presentation.viewmodel.PlaylistViewModel
import com.example.myplaylistapp.presentation.viewmodel.PlaylistViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: PlaylistViewModelFactory
    lateinit var viewModel: PlaylistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        viewModel = ViewModelProvider(this, viewModelFactory)[PlaylistViewModel::class.java]

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