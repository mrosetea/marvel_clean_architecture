package com.example.marvelcleanarchitectureapp.modules.home.ui.view.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelcleanarchitectureapp.R
import com.example.marvelcleanarchitectureapp.databinding.HomeCharacterItemBinding
import com.example.marvelcleanarchitectureapp.modules.home.ui.model.ViewData

class HomeAdapter(private val listener: () -> Unit) : ListAdapter<ViewData.Character, HomeAdapter.HomeViewHolder>(object :
    DiffUtil.ItemCallback<ViewData.Character>() {
    override fun areItemsTheSame(
        oldItem: ViewData.Character,
        newItem: ViewData.Character
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ViewData.Character,
        newItem: ViewData.Character
    ): Boolean {
        return oldItem == newItem
    }

}) {

    class HomeViewHolder(private val binding: HomeCharacterItemBinding, private val listener: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ViewData.Character) {
            binding.character = item
            itemView.setOnClickListener{
                listener()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.home_character_item, parent, false)
        val binding = HomeCharacterItemBinding.bind(view)
        return HomeViewHolder(binding, listener)

    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = getItem(position)
        Log.d("DEBUGGG", item.urlImage)
        holder.bind(item)
    }

}

