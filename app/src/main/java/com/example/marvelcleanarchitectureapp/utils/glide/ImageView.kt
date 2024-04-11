package com.example.marvelcleanarchitectureapp.utils.glide

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Log.d("DEBUGIMAGE", url)
    Glide.with(view.context).load(url).into(view)
}