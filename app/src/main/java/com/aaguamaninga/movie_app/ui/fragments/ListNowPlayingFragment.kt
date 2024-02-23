package com.aaguamaninga.movie_app.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.aaguamaninga.movie_app.R
import com.aaguamaninga.movie_app.databinding.FragmentListNowPlayingBinding
import com.aaguamaninga.movie_app.ui.adapters.MovieAdapter
import com.aaguamaninga.movie_app.ui.viewmodels.ListNowPlayingViewModel


class ListNowPlayingFragment : Fragment() {

    private lateinit var binding: FragmentListNowPlayingBinding
    private val adapter = MovieAdapter()
    private val viewModel : ListNowPlayingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentListNowPlayingBinding.bind(inflater.inflate(R.layout.fragment_list_now_playing, container, false))
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObservers()
        initRecyclerView()
        viewModel.getAllNobelPrizes()
    }


    private fun initObservers() {
        viewModel.listItems.observe(requireActivity()){
            binding.animationView.visibility = View.VISIBLE
            adapter.submitList(it)
            binding.animationView.visibility = View.GONE
        }

        viewModel.error.observe(requireActivity()){
            adapter.listNobel = emptyList()
            adapter.notifyDataSetChanged()
        }
    }

    private fun initRecyclerView(){
        binding.rvUsers.adapter = adapter
        /*binding.rvUsers.layoutManager =
            LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.VERTICAL,
                false
            )*/
        binding.rvUsers.layoutManager = GridLayoutManager(requireActivity(), 2)
    }

    private fun initListeners(){
        binding.swiperv.setOnRefreshListener {
            initRecyclerView()
            binding.swiperv.isRefreshing = false
        }
    }

}