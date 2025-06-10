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
import com.example.myplaylistapp.MyApplication
import com.example.myplaylistapp.R
import com.example.myplaylistapp.presentation.viewmodel.PlaylistViewModel

class PlaylistFragment : Fragment() {

    private val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this, MyApplication().factory).get(PlaylistViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        viewModel.loader.observe(this as LifecycleOwner) { loading ->
            when (loading) {
                true -> view.findViewById<View>(R.id.loader).visibility = View.VISIBLE
                else -> view.findViewById<View>(R.id.loader).visibility = View.GONE
            }
        }

        viewModel.playlists.observe(this as LifecycleOwner) { playlist ->
            if (playlist.getOrNull() != null) {
                with(view.findViewById<RecyclerView>(R.id.playlist)!!) {
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