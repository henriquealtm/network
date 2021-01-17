package br.com.henriquealtmayer.network.commons.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("load_image")
fun ImageView.loadImage(
    url: String?
) {
    if (url == null) return

    Glide
        .with(context)
        .load(url)
        .centerCrop()
//        .placeholder(R.drawable.loading_spinner)
        .into(this)
}