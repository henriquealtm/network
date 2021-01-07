package br.com.henriquealtmayer.network.commons.extension

import androidx.recyclerview.widget.RecyclerView
import br.com.henriquealtmayer.network.commons.handleOptional

fun RecyclerView.smoothScrollToBottom() {
    smoothScrollToPosition(adapter?.itemCount.handleOptional())
}