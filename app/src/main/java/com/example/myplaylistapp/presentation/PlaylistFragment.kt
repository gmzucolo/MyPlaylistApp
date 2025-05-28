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
import com.example.myplaylistapp.data.api.PlaylistAPI
import com.example.myplaylistapp.data.repository.PlaylistRepository
import com.example.myplaylistapp.data.service.PlaylistService
import com.example.myplaylistapp.presentation.viewmodel.PlaylistViewModel
import com.example.myplaylistapp.presentation.viewmodel.PlaylistViewModelFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlaylistFragment : Fragment() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.15.9:8080/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: PlaylistAPI = retrofit.create(PlaylistAPI::class.java)

    private val service: PlaylistService = PlaylistService(api)
    private val repository: PlaylistRepository = PlaylistRepository(service)
    private val viewModel: PlaylistViewModel by lazy {
        val factory = PlaylistViewModelFactory(repository)
        ViewModelProvider(this, factory)[PlaylistViewModel::class.java]
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

//            viewModel.playlists.value?.fold(
//                onSuccess = {
//                    with(view as RecyclerView) {
//                        layoutManager = LinearLayoutManager(context)
//                        adapter = MyPlaylistRecyclerViewAdapter(it)
//                    }
//                },
//                onFailure = {
//
//                }
//            )
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = PlaylistFragment()
    }
}