package br.com.henriquealtmayer.network.commons.binding

import android.view.View
import android.view.View.*
import androidx.databinding.BindingAdapter

@BindingAdapter("app:gone_unless")
fun View.goneUnless(
    visible: Boolean
) {
    visibility = if (visible) VISIBLE else GONE
}

@BindingAdapter("app:invisible_unless")
fun View.invisibleUnless(
    visible: Boolean
) {
    visibility = if (visible) VISIBLE else INVISIBLE
}