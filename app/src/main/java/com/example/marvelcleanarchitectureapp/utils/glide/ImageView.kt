package com.example.marvelcleanarchitectureapp.utils.glide

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Log.d("DEBUGIMG", url)
    Glide.with(view.context).load(url).apply(RequestOptions.overrideOf(300, 200)).into(view)
}