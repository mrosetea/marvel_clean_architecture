package com.example.marvelcleanarchitectureapp.modules.home.ui.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
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
        arguments?.getString("name").let {
            binding.webview.settings.javaScriptEnabled = true
            binding.webview.webViewClient = WebViewClient()
            binding.webview.loadUrl(generateWebViewUrl(it.orEmpty()))
        }
    }
    //private fun generateWebViewUrl(name: String): String = "https://www.pokemon.com/us/pokedex/${name}"
    private fun generateWebViewUrl(name: String): String = "https://www.marvel.com/comics/characters/${name}/spider-man_peter_parker"

}