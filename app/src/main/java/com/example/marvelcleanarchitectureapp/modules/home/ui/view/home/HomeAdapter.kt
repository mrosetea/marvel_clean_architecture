package com.example.marvelcleanarchitectureapp.modules.home.ui.view.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelcleanarchitectureapp.R
import com.example.marvelcleanarchitectureapp.databinding.HomeCharacterItemBinding
import com.example.marvelcleanarchitectureapp.modules.home.ui.model.ViewData

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    val items: MutableList<ViewData.Character> = mutableListOf();

    class HomeViewHolder(private val binding: HomeCharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ViewData.Character) {
            Log.d("DEBUGBIND", item.name)
            binding.character = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.home_character_item, parent, false)
        val binding = HomeCharacterItemBinding.bind(view)
        return HomeViewHolder(binding)

    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(items: List<ViewData.Character>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}

