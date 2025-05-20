package com.example.myplaylistapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myplaylistapp.R
import com.example.myplaylistapp.data.repository.PlaylistRepository
import com.example.myplaylistapp.presentation.viewmodel.PlaylistViewModel
import com.example.myplaylistapp.presentation.viewmodel.PlaylistViewModelFactory
import kotlinx.coroutines.launch

class PlaylistFragment : Fragment() {

    private val repository: PlaylistRepository = PlaylistRepository()
    private val viewModel: PlaylistViewModel by lazy {
        val factory = PlaylistViewModelFactory(repository)
        ViewModelProvider(this, factory)[PlaylistViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.playlists.value?.fold(
                onSuccess = {
                    with(view as RecyclerView) {
                        layoutManager = LinearLayoutManager(context)
                        adapter = MyPlaylistRecyclerViewAdapter(it)
                    }
                },
                onFailure = {

                }
            )
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = PlaylistFragment()
    }
}