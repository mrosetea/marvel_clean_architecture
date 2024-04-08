package com.example.marvelcleanarchitectureapp.modules.home.ui.view.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.marvelcleanarchitectureapp.databinding.HomeActivityMainBinding
import com.example.marvelcleanarchitectureapp.modules.home.data.gateway.HomeGateway
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HomeActivity: AppCompatActivity() {
    lateinit var binding: HomeActivityMainBinding
    private val gateway: HomeGateway by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = HomeActivityMainBinding.inflate(layoutInflater)
        binding.swiper.setOnRefreshListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val response = gateway.getCharacters(true)
                binding.swiper.isRefreshing= false

            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            val response = gateway.getCharacters()
        }

        setContentView(binding.root)

    }

}