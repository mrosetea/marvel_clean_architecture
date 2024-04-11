package com.example.marvelcleanarchitectureapp.modules.home.ui.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelcleanarchitectureapp.R
import com.example.marvelcleanarchitectureapp.databinding.HomeFragmentBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var binding: HomeFragmentBinding
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var homeAdapter: HomeAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        binding.swiper.setOnRefreshListener {
            viewModel.fetchCharacters(0)
        }
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.fetchCharacters(homeAdapter.itemCount)
                }
            }
        })
        homeAdapter = HomeAdapter {
            findNavController().navigate(
                R.id.action_from_home_to_detail, bundleOf("name" to it)
            )
        }
        binding.recyclerView.adapter = homeAdapter
        collectFlows()
    }

    private fun collectFlows() {
        viewLifecycleOwner.lifecycleScope.launch {
            with(viewModel) {
                uiStateChange.collect {
                    onUiStateChangeCollected(it)
                }
            }

        }
    }

    private fun onUiStateChangeCollected(uiState: HomeUIStateChange) {
        when (uiState) {
            is HomeUIStateChange.Loading -> onToggleLoading(uiState)
            is HomeUIStateChange.AddCharactersList -> onAddHomeCharacterListCollected(uiState)
            is HomeUIStateChange.AddHomeError -> onAddHomeErrorCollected(uiState)
            else -> {}
        }
    }

    private fun onToggleLoading(uiState: HomeUIStateChange.Loading) {
        binding.progressBar.isVisible = uiState.isLoading
        binding.swiper.isRefreshing = uiState.isLoading
    }

    private fun onAddHomeCharacterListCollected(uiState: HomeUIStateChange.AddCharactersList) {
        homeAdapter.submitList(uiState.viewData.characters)
    }

    private fun onAddHomeErrorCollected(uiState: HomeUIStateChange.AddHomeError) {
        Toast.makeText(requireContext(), uiState.error, Toast.LENGTH_LONG).show()
        homeAdapter.submitList(uiState.viewData.characters)
    }

}