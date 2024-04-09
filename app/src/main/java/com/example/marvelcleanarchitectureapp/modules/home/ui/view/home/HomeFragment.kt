package com.example.marvelcleanarchitectureapp.modules.home.ui.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelcleanarchitectureapp.databinding.HomeFragmentBinding
import com.example.marvelcleanarchitectureapp.modules.home.ui.model.ViewData
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
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.swiper.setOnRefreshListener {

        }
        homeAdapter = HomeAdapter()
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
            is HomeUIStateChange.AddHomeLoading -> onToggleLoading(uiState)
            is HomeUIStateChange.RemoveHomeLoading -> onToggleLoading(uiState)
            is HomeUIStateChange.AddCharactersList -> onAddHomePokemonListCollected(uiState)
            is HomeUIStateChange.AddHomeError -> onAddHomeErrorCollected(uiState)
            else -> {}
        }
    }

    private fun toggleLoading(visible: Boolean): Int {
        return if (visible) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

    private fun onToggleLoading(uiState: HomeUIStateChange.AddHomeLoading) {
        binding.progressBar.visibility = toggleLoading(uiState.isLoading)
    }

    private fun onToggleLoading(uiState: HomeUIStateChange.RemoveHomeLoading) {
        binding.progressBar.visibility = toggleLoading(uiState.isLoading)
    }

    private fun onAddHomePokemonListCollected(uiState: HomeUIStateChange.AddCharactersList) {
        uiState.viewData.characters.let {
            Log.d("DATADEBUG", it.toString())
            val list: List<ViewData.Character> = it.map {
                ViewData.Character(
                    id = it.id,
                    name = it.name
                )
            }
            homeAdapter.updateItems(list)

        }
        //uiState.pokemons.toUIModel().let {
        //homeAdapter.updateItems(it.results)
        //}
    }

    private fun onAddHomeErrorCollected(uiState: HomeUIStateChange) {
        //TODO IMPLEMENTAR CASO DE ERROR
    }

}