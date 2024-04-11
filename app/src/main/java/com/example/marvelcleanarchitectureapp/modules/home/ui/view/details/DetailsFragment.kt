package com.example.marvelcleanarchitectureapp.modules.home.ui.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.marvelcleanarchitectureapp.databinding.DetailsFragmentBinding

class DetailsFragment: Fragment() {
    private lateinit var binding: DetailsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = arguments?.getString("name", "name" ) ?: "name"
        binding.toolbar.title = name
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}